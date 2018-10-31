package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.DTO.SampleBoxDTO;
import com.todaysoft.ghealth.gateway.Gateway;
import com.todaysoft.ghealth.model.Order;
import com.todaysoft.ghealth.model.searcher.OrderSearcher;
import com.todaysoft.ghealth.request.MainSampleBoxRequest;
import com.todaysoft.ghealth.request.MaintainOrderRequest;
import com.todaysoft.ghealth.request.OrderQueryRequest;
import com.todaysoft.ghealth.service.IOrderService;
import com.todaysoft.ghealth.service.wrapper.OrderWrapper;
import com.todaysoft.ghealth.support.Pager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author: xjw
 * @Date: 2018/10/16 14:56
 */
@Service
public class OrderService implements IOrderService
{
    @Autowired
    private Gateway gateway;
    
    @Autowired
    private OrderWrapper orderWrapper;
    
    @Override
    public Pager<Order> pager(OrderSearcher searcher, int pageNo, int pageSize)
    {
        OrderQueryRequest request = wrapper(searcher);
        request.setCount(true);
        request.setLimit(pageSize);
        request.setOffset((pageNo - 1) * pageSize);
        DataResponse<CountRecords<OrderDTO>> response =
            gateway.post("/order/pager", request, new ParameterizedTypeReference<DataResponse<CountRecords<OrderDTO>>>()
            {
            });
        if (null == response.getData() || null == response.getData().getCount())
        {
            throw new IllegalStateException();
        }
        Pager<Order> pager = new Pager<>(pageNo, pageSize, response.getData().getCount().intValue());
        pager.setRecords(orderWrapper.wrap(response.getData().getRecords()));
        return pager;
    }
    
    @Override
    public List<Order> list(OrderSearcher searcher)
    {
        OrderQueryRequest request = wrapper(searcher);
        
        DataResponse<List<OrderDTO>> response = gateway.post("/order/list", request, new ParameterizedTypeReference<DataResponse<List<OrderDTO>>>()
        {
        });
        
        return orderWrapper.wrap(response.getData());
    }
    
    @Override
    public Order get(String id)
    {
        DataResponse<OrderDTO> response = gateway.get("/order/get/{id}", new ParameterizedTypeReference<DataResponse<OrderDTO>>()
        {
        }, id);
        
        if (Objects.isNull(response.getData()))
        {
            return new Order();
        }
        
        return orderWrapper.wrap(response.getData());
    }
    
    @Override
    public void modify(Order order)
    {
        MaintainOrderRequest request = new MaintainOrderRequest();
        request.setId(order.getId());
        if (null != order.getOrderUrl())
        {
            request.setOrderUrl(order.getOrderUrl());
        }
        request.setStatus(order.getStatus());
        if (Objects.nonNull(order.getSampleBox()))
        {
            SampleBoxDTO sampleBox = new SampleBoxDTO();
            BeanUtils.copyProperties(order.getSampleBox(), sampleBox);
            request.setSampleBox(sampleBox);
        }
        
        gateway.post("/order/modify", request);
    }
    
    @Override
    public Boolean isUniqueSampleBoxCode(String sampleBoxCode)
    {
        MainSampleBoxRequest request = new MainSampleBoxRequest();
        request.setCode(sampleBoxCode);
        DataResponse<Boolean> response = gateway.post("/order/isUniqueSampleBoxCode", request, new ParameterizedTypeReference<DataResponse<Boolean>>()
        {
        });
        if (null == response.getData())
        {
            return false;
        }
        return response.getData();
    }
    
    private OrderQueryRequest wrapper(OrderSearcher searcher)
    {
        OrderQueryRequest request = new OrderQueryRequest();
        BeanUtils.copyProperties(searcher, request, "startTime", "endTime");
        if (null != searcher.getStartTime())
        {
            request.setStartTime(String.valueOf(searcher.getStartTime().getTime()));
        }
        if (null != searcher.getEndTime())
        {
            request.setEndTime(String.valueOf(searcher.getEndTime().getTime()));
        }
        return request;
    }
}
