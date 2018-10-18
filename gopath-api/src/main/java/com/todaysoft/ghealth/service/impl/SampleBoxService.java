package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.mybatis.mapper.CustomerMapper;
import com.todaysoft.ghealth.mybatis.mapper.OrderMapper;
import com.todaysoft.ghealth.mybatis.model.Customer;
import com.todaysoft.ghealth.mybatis.model.Order;
import com.todaysoft.ghealth.mybatis.model.query.OrderQuery;
import com.todaysoft.ghealth.request.MainSampleBoxRequest;
import com.todaysoft.ghealth.request.MaintainOrderRequest;
import com.todaysoft.ghealth.service.ISampleBoxService;
import com.todaysoft.ghealth.service.wrapper.OrderWrapper;
import com.todaysoft.ghealth.utils.IdGen;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    private OrderWrapper orderWrapper;
    
    @Override
    public void bind(MaintainOrderRequest request)
    {
        String customerId = IdGen.uuid();
        Customer customer = new Customer();
        BeanUtils.copyProperties(request.getCustomer(), customer);
        customer.setId(customerId);
        customer.setCreateTime(new Date());
        customerMapper.create(customer);
        
        Order order = orderMapper.get(request.getId());
        order.setCustomer(customer);
        order.setSampleType(request.getSampleType());
        orderMapper.modify(order);
        
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
        return new DataResponse<OrderDTO>(orderWrapper.wrap(orders.get(0)));
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
        return new DataResponse<OrderDTO>(orderWrapper.wrap(orders.get(0)));
    }
}
