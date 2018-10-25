package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.gateway.Gateway;
import com.todaysoft.ghealth.request.MaintainOrderRequest;
import com.todaysoft.ghealth.request.OrderQueryRequest;
import com.todaysoft.ghealth.service.IOrderService;
import com.todaysoft.ghealth.wechat.AccountContextHolder;
import org.apache.commons.lang3.StringUtils;
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
    
    @Autowired
    private AccountContextHolder holder;
    
    @Override
    public String create(OrderDTO data)
    {
        MaintainOrderRequest request = new MaintainOrderRequest();
        BeanUtils.copyProperties(data, request);
        request.setOpenId(holder.getAccount().getOpenid());
        request.setAgencyId(holder.getAccount().getAgencyId());
        request.setProductId(data.getProduct().getId());
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

    @Override
    public void payed(String code)
    {
        MaintainOrderRequest request = new MaintainOrderRequest();
        request.setCode(code);
        gateway.post("/order/payed", request);
    }


    @Override
    public OrderDTO get(String id)
    {
        DataResponse<OrderDTO> response = gateway.get("/order/get/{id}", new ParameterizedTypeReference<DataResponse<OrderDTO>>()
        {
        }, id);

        if (Objects.isNull(response.getData()))
        {
            return new OrderDTO();
        }

        return response.getData();
    }
}
