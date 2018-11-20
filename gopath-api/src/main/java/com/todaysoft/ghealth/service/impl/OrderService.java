package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.DTO.SampleBoxDTO;
import com.todaysoft.ghealth.mybatis.mapper.OrderHistoryMapper;
import com.todaysoft.ghealth.mybatis.mapper.OrderMapper;
import com.todaysoft.ghealth.mybatis.mapper.SampleBoxMapper;
import com.todaysoft.ghealth.mybatis.model.*;
import com.todaysoft.ghealth.mybatis.model.query.OrderQuery;
import com.todaysoft.ghealth.request.MainSampleBoxRequest;
import com.todaysoft.ghealth.request.MaintainOrderRequest;
import com.todaysoft.ghealth.request.OrderQueryRequest;
import com.todaysoft.ghealth.service.IOrderService;
import com.todaysoft.ghealth.service.paser.OrderQueryParser;
import com.todaysoft.ghealth.service.wrapper.OrderWrapper;
import com.todaysoft.ghealth.utils.IdGen;
import com.todaysoft.ghealth.utils.SerialNumber;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author: xjw
 * @Date: 2018/10/16 15:15
 */
@Service
public class OrderService implements IOrderService
{
    @Autowired
    private OrderQueryParser orderQueryParser;
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private OrderWrapper orderWrapper;
    
    @Autowired
    private SampleBoxMapper sampleBoxMapper;
    
    @Autowired
    private OrderHistoryMapper orderHistoryMapper;
    
    @Autowired
    private SerialNumber serialNumber;
    
    @Override
    public DataResponse<CountRecords<OrderDTO>> pager(OrderQueryRequest request)
    {
        OrderQuery query = orderQueryParser.parse(request);
        CountRecords<OrderDTO> data = new CountRecords<>();
        
        if (request.isCount())
        {
            long count = orderMapper.count(query);
            data.setCount(count);
            
            if (0 == count)
            {
                data.setRecords(Collections.emptyList());
                return new DataResponse<>(data);
            }
            
            if (null != request.getLimit() && null != request.getOffset() && request.getOffset().intValue() >= count)
            {
                int offset;
                int limit = request.getLimit().intValue();
                int mod = (int)count % limit;
                
                if (0 == mod)
                {
                    offset = (((int)count / limit) - 1) * limit;
                }
                else
                {
                    offset = ((int)count / limit) * limit;
                }
                
                query.setOffset(offset);
            }
        }
        
        List<Order> records = orderMapper.query(query);
        data.setRecords(orderWrapper.wrap(records));
        return new DataResponse<>(data);
    }
    
    @Override
    public DataResponse<List<OrderDTO>> list(OrderQueryRequest request)
    {
        OrderQuery query = orderQueryParser.parse(request);
        return new DataResponse<>(orderWrapper.wrap(orderMapper.query(query)));
    }
    
    @Override
    public DataResponse<OrderDTO> get(String id)
    {
        return new DataResponse<>(orderWrapper.wrap(orderMapper.get(id)));
    }
    
    @Override
    @Transactional
    public DataResponse<String> create(MaintainOrderRequest request)
    {
        Integer status = 4;
        Order order = new Order();
        String orderCode = serialNumber.getCode(Order.SAMPLE_CODE);
        
        Product product = new Product();
        product.setId(request.getProductId());
        order.setProduct(product);
        
        SampleBox sampleBox = new SampleBox();

        String sampleBoxId = IdGen.uuid();
        SampleBoxDTO sampleBoxRequest = request.getSampleBox();
        if (Objects.nonNull(request.getSampleBox())){
            BeanUtils.copyProperties(sampleBoxRequest, sampleBox);

        }
        sampleBox.setId(sampleBoxId);
        sampleBox.setCreateTime(new Date());
        sampleBox.setCode(serialNumber.getCode(SampleBox.SAMPLEBOX_CODE));
        sampleBoxMapper.create(sampleBox);

        order.setSampleBox(sampleBox);
        
        Agency agency = new Agency();
        agency.setId(request.getAgencyId());
        order.setAgency(agency);
        
        order.setCustomer(new Customer());
        
        order.setStatus(status);
        order.setCode(orderCode);
        order.setId(IdGen.uuid());
        order.setOpenId(request.getOpenId());
        order.setCreateTime(new Date());
        order.setSampleType(request.getSampleType());
        order.setActualPrice(request.getActualPrice());
        order.setReportPrintRequired(request.getReportPrintRequired());
        order.setDeleted(false);
        orderMapper.create(order);
        
        request.setStatus(status);
        request.setId(order.getId());
        createOrderHistory(request);
        
        return new DataResponse<>(orderCode);
    }
    
    @Override
    @Transactional
    public void modify(MaintainOrderRequest request)
    {
        Order order = orderMapper.get(request.getId());
        
        if (null != request.getSampleBox())
        {
            SampleBoxDTO data = request.getSampleBox();
            SampleBox sampleBox = order.getSampleBox();
            
            if (StringUtils.isNotEmpty(data.getCode()))
            {
                sampleBox.setCode(data.getCode());
            }
            else
            {
                BeanUtils.copyProperties(data, sampleBox);
            }
            sampleBoxMapper.modify(sampleBox);
        }
        if (null != request.getStatus())
        {
            createOrderHistory(request);
            order.setStatus(request.getStatus());
            order.setOrderUrl(request.getOrderUrl());
            orderMapper.modify(order);
        }
    }
    
    @Override
    public DataResponse<Boolean> isUniqueSampleBoxCode(MainSampleBoxRequest request)
    {
        return new DataResponse<Boolean>(sampleBoxMapper.getByCode(request.getCode()) != 0);
    }
    
    @Override
    public DataResponse<List<OrderDTO>> getByOpenid(String openid)
    {
        List<Order> records = orderMapper.getByOpenid(openid);
        return new DataResponse<>(orderWrapper.wrap(records));
    }
    
    @Override
    public void payed(MaintainOrderRequest request)
    {
        Integer status = 0;
        OrderQuery query = new OrderQuery();
        query.setCode(request.getCode());
        List<Order> orders = orderMapper.query(query);
        if (!CollectionUtils.isEmpty(orders))
        {
            Order order = orders.get(0);
            order.setStatus(status);
            orderMapper.modify(order);
            
            request.setStatus(status);
            createOrderHistory(request);
        }
    }

    @Override
    public void updateByCode(MaintainOrderRequest request) {
        
        Order order = new Order();
        order.setStatus(request.getStatus());
        order.setCode(request.getCode());
        Order or = orderMapper.getByCode(request.getCode());
        if (4 == or.getStatus())
        {
            orderMapper.updateByCode(order);

            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setId(IdGen.uuid());
            orderHistory.setOrderId(or.getId());
            orderHistory.setEventTime(new Date());
            orderHistory.setEventType(request.getStatus());
            orderHistoryMapper.create(orderHistory);
        }
    }

    private void createOrderHistory(MaintainOrderRequest request)
    {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrderId(request.getId());
        orderHistory.setId(IdGen.uuid());
        orderHistory.setEventTime(new Date());
        orderHistory.setEventType(request.getStatus());
        orderHistoryMapper.create(orderHistory);
    }
}
