package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.DTO.SampleBoxDTO;
import com.todaysoft.ghealth.mybatis.mapper.OrderMapper;
import com.todaysoft.ghealth.mybatis.mapper.SampleBoxMapper;
import com.todaysoft.ghealth.mybatis.model.Order;
import com.todaysoft.ghealth.mybatis.model.SampleBox;
import com.todaysoft.ghealth.mybatis.model.query.OrderQuery;
import com.todaysoft.ghealth.request.MainSampleBoxRequest;
import com.todaysoft.ghealth.request.MaintainOrderRequest;
import com.todaysoft.ghealth.request.OrderQueryRequest;
import com.todaysoft.ghealth.service.IOrderService;
import com.todaysoft.ghealth.service.paser.OrderQueryParser;
import com.todaysoft.ghealth.service.wrapper.OrderWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
    public void modify(MaintainOrderRequest request)
    {
        Order order = orderMapper.get(request.getId());
        
        SampleBoxDTO data = request.getSampleBox();
        SampleBox sampleBox = order.getSampleBox();
        if (Objects.nonNull(sampleBox) && Objects.nonNull(data))
        {
            sampleBox.setCode(data.getCode());
            sampleBoxMapper.modify(sampleBox);
        }
        
        order.setStatus(request.getStatus());
        orderMapper.modify(order);
    }
    
    @Override
    public DataResponse<Boolean> isUniqueSampleBoxCode(MainSampleBoxRequest request)
    {
        return new DataResponse<Boolean>(sampleBoxMapper.getByCode(request.getCode()) != 0);
    }
}
