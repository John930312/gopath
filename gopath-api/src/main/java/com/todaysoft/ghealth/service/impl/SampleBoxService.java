package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.mybatis.mapper.CustomerMapper;
import com.todaysoft.ghealth.mybatis.mapper.OrderHistoryMapper;
import com.todaysoft.ghealth.mybatis.mapper.OrderMapper;
import com.todaysoft.ghealth.mybatis.mapper.SampleBoxMapper;
import com.todaysoft.ghealth.mybatis.model.Customer;
import com.todaysoft.ghealth.mybatis.model.Order;
import com.todaysoft.ghealth.mybatis.model.OrderHistory;
import com.todaysoft.ghealth.mybatis.model.SampleBox;
import com.todaysoft.ghealth.mybatis.model.query.OrderQuery;
import com.todaysoft.ghealth.request.MainSampleBoxRequest;
import com.todaysoft.ghealth.request.MaintainOrderRequest;
import com.todaysoft.ghealth.service.ISampleBoxService;
import com.todaysoft.ghealth.service.wrapper.OrderHistoryWrapper;
import com.todaysoft.ghealth.service.wrapper.OrderWrapper;
import com.todaysoft.ghealth.utils.IdGen;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
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
        return new DataResponse<OrderDTO>(orderWrapper.wrap(orders.get(0)));
    }
}
