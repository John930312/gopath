package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.DTO.SampleBoxDTO;
import com.todaysoft.ghealth.mybatis.mapper.*;
import com.todaysoft.ghealth.mybatis.model.*;
import com.todaysoft.ghealth.mybatis.model.query.OrderQuery;
import com.todaysoft.ghealth.mybatis.model.query.SampleBoxQuery;
import com.todaysoft.ghealth.request.MainSampleBoxRequest;
import com.todaysoft.ghealth.request.MaintainOrderRequest;
import com.todaysoft.ghealth.request.SampleBoxMaintainRequest;
import com.todaysoft.ghealth.request.SampleBoxQueryRequest;
import com.todaysoft.ghealth.service.ISampleBoxService;
import com.todaysoft.ghealth.service.wrapper.OrderHistoryWrapper;
import com.todaysoft.ghealth.service.wrapper.OrderWrapper;
import com.todaysoft.ghealth.service.wrapper.SampleBoxWrapper;
import com.todaysoft.ghealth.utils.IdGen;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Author: xjw
 * @Date: 2018/10/17 17:28
 */
@Service
public class SampleBoxService implements ISampleBoxService
{
    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private CustomerMapper customerMapper;
    
    @Autowired
    private SampleBoxMapper sampleBoxMapper;
    
    @Autowired
    private OrderWrapper orderWrapper;
    
    @Autowired
    private OrderHistoryMapper orderHistoryMapper;
    
    @Autowired
    private OrderHistoryWrapper orderHistoryWrapper;
    
    @Autowired
    private SampleBoxWrapper sampleBoxWrapper;

    @Autowired
    private SampleBoxAgencyMapper sampleBoxAgencyMapper;
    
    @Override
    public void bind(MaintainOrderRequest request)
    {
        Order order = orderMapper.get(request.getId());
        SampleBox sampleBox = order.getSampleBox();
        
        String customerId = IdGen.uuid();
        Customer customer = new Customer();
        BeanUtils.copyProperties(request.getCustomer(), customer, "birthday");
        customer.setId(customerId);
        customer.setCreateTime(new Date());
        Date customerBirthday = null;
        Date orderSamplingTime = null;
        try
        {
            customerBirthday = DateUtils.parseDate(request.getCustomer().getBirthday(), "yyyy-MM-dd");
            orderSamplingTime = DateUtils.parseDate(request.getSamplingTime(), "yyyy-MM-dd");
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        customer.setBirthday(customerBirthday);
        customer.setSampleBoxId(sampleBox.getId());
        customerMapper.create(customer);

        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setId(IdGen.uuid());
        orderHistory.setOrderId(order.getId());
        orderHistory.setEventTime(new Date());
        orderHistory.setEventType(2);
        orderHistoryMapper.create(orderHistory);

        order.setStatus(2);
        order.setCustomer(customer);
        order.setSampleType(request.getSampleType());
        order.setSamplingTime(orderSamplingTime);
        orderMapper.modify(order);
        
        sampleBox.setBinded(true);
        sampleBoxMapper.modify(sampleBox);
    }

    @Override
    public void bindByCode(MaintainOrderRequest request)
    {
        //step1:获取订单
        String id  = request.getId();
        Order byCode = orderMapper.getByCode(id);

        //step2:删除订单生成的采样盒
        sampleBoxMapper.deleteById(byCode.getSampleBox().getId());

        //step3:查询采样盒id
        SampleBoxQuery s = new SampleBoxQuery();
        s.setCode(request.getSampleBox().getCode());
        List<SampleBox> query = sampleBoxMapper.query(s);

        //step4:更新订单内容
        Order or = new Order();
        or.setCode(query.get(0).getId());
        or.setId(byCode.getId());
        orderMapper.updateSampleboxId(or);

        Order order = orderMapper.get(byCode.getId());
        SampleBox sampleBox = order.getSampleBox();

        String customerId = IdGen.uuid();
        Customer customer = new Customer();
        BeanUtils.copyProperties(request.getCustomer(), customer, "birthday");
        customer.setId(customerId);
        customer.setCreateTime(new Date());
        Date customerBirthday = null;
        Date orderSamplingTime = null;
        try
        {
            customerBirthday = DateUtils.parseDate(request.getCustomer().getBirthday(), "yyyy-MM-dd");
            orderSamplingTime = DateUtils.parseDate(request.getSamplingTime(), "yyyy-MM-dd");
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        customer.setBirthday(customerBirthday);
        customer.setSampleBoxId(sampleBox.getId());
        customerMapper.create(customer);

        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setId(IdGen.uuid());
        orderHistory.setOrderId(order.getId());
        orderHistory.setEventTime(new Date());
        orderHistory.setEventType(2);
        orderHistoryMapper.create(orderHistory);

        order.setStatus(2);
        order.setCustomer(customer);
        order.setSampleType(request.getSampleType());
        order.setSamplingTime(orderSamplingTime);
        orderMapper.modify(order);

        sampleBox.setBinded(true);
        sampleBoxMapper.modify(sampleBox);
    }
    
    @Override
    public DataResponse<OrderDTO> sampleBoxDetails(MainSampleBoxRequest request)
    {
        OrderQuery query = new OrderQuery();
        query.setSampleBoxId(request.getId());
        List<Order> orders = orderMapper.query(query);
        if (CollectionUtils.isEmpty(orders))
        {
            return new DataResponse<OrderDTO>(null);
        }
        OrderDTO dto = orderWrapper.wrap(orders.get(0));
        dto.setOrderHistory(orderHistoryWrapper.wrap(orderHistoryMapper.getByOrderId(dto.getId())));
        return new DataResponse<OrderDTO>(dto);
    }

    @Override
    public DataResponse<OrderDTO> getOrderDTOBySampleBoxCode(MainSampleBoxRequest request)
    {
        OrderQuery query = new OrderQuery();
        query.setSampleBoxCode(request.getCode());
        List<Order> orders = orderMapper.query(query);
        if (CollectionUtils.isEmpty(orders))
        {
            return new DataResponse<OrderDTO>(null);
        }
        OrderDTO dto = orderWrapper.wrap(orders.get(0));
        dto.setOrderHistory(orderHistoryWrapper.wrap(orderHistoryMapper.getByOrderId(dto.getId())));
        return new DataResponse<OrderDTO>(dto);
    }

    @Override
    public DataResponse<SampleBoxDTO> getOrderDTOBySampleBoxCodeLocal(MainSampleBoxRequest request)
    {
        //查询是否存在 是否使用
        List<SampleBox> sampleBox = sampleBoxMapper.checkByCode(request.getCode());
        if (CollectionUtils.isEmpty(sampleBox))
        {
            return new DataResponse<SampleBoxDTO>(null);
        }
        return new DataResponse<SampleBoxDTO>(sampleBoxWrapper.wrap(sampleBox.get(0)));
    }
    
    @Override
    public DataResponse<CountRecords<SampleBoxDTO>> pager(SampleBoxQueryRequest request)
    {
        SampleBoxQuery query = new SampleBoxQuery();
        BeanUtils.copyProperties(request, query);
        CountRecords<SampleBoxDTO> data = new CountRecords<>();
        
        if (request.isCount())
        {
            long count = sampleBoxMapper.count(query);
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
        
        List<SampleBox> records = sampleBoxMapper.query(query);
        data.setRecords(sampleBoxWrapper.wrap(records));
        return new DataResponse<>(data);
    }
    
    @Override
    public DataResponse<Boolean> isUniqueSampleBoxCode(String code)
    {
        return new DataResponse<Boolean>(sampleBoxMapper.getByCode(code) != 0);
    }
    
    @Override
    @Transactional
    public void create(SampleBoxMaintainRequest request)
    {
        request.getSampleBoxCode().forEach(x->{
            SampleBox sampleBox = new SampleBox();
            sampleBox.setId(IdGen.uuid());
            sampleBox.setCode(x);
            sampleBox.setBinded(false);
            sampleBox.setCreateTime(new Date());
            sampleBoxMapper.create(sampleBox);

            SampleBoxAgency sampleBoxAgency = new SampleBoxAgency();
            sampleBoxAgency.setSampleBoxId(sampleBox.getId());
            sampleBoxAgency.setAgencyId(request.getAgencyId());
            sampleBoxAgency.setCreateTime(sampleBox.getCreateTime());
            sampleBoxAgency.setType(request.getType());
            sampleBoxAgencyMapper.create(sampleBoxAgency);
        });
    }
}
