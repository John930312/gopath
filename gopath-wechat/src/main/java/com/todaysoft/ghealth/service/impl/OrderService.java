package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.gateway.Gateway;
import com.todaysoft.ghealth.request.MaintainOrderRequest;
import com.todaysoft.ghealth.request.OrderQueryRequest;
import com.todaysoft.ghealth.service.IOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author: xjw
 * @Date: 2018/10/22 16:06
 */
@Service
public class OrderService implements IOrderService
{
    @Autowired
    private Gateway gateway;
    
    @Override
    public String create(OrderDTO data)
    {
        MaintainOrderRequest request = new MaintainOrderRequest();
        BeanUtils.copyProperties(data, request);
        DataResponse<String> response = gateway.post("/order/create", request, new ParameterizedTypeReference<DataResponse<String>>()
        {
        });
        return response.getData();
    }
    
    public List<OrderDTO> getMyOrder(String openId)
    {
        OrderQueryRequest request = new OrderQueryRequest();
        request.setOpenId(openId);
        DataResponse<List<OrderDTO>> response = gateway.post("/order/getMyOrder", request, new ParameterizedTypeReference<DataResponse<List<OrderDTO>>>()
        {
        });
        return response.getData();
    }
}
