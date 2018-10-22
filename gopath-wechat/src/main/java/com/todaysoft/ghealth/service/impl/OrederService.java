package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.gateway.Gateway;
import com.todaysoft.ghealth.request.OrderQueryRequest;
import com.todaysoft.ghealth.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zyf
 * @Date: 2018/10/22 14:17
 */
@Service
public class OrederService implements IOrderService {
    @Autowired
    private Gateway gateway;

    @Override
    public List<OrderDTO> getMyOrder(String openId){
        OrderQueryRequest request = new OrderQueryRequest();
        request.setOpenId(openId);
        DataResponse<List<OrderDTO>> response = gateway.post("/order/getMyOrder", request, new ParameterizedTypeReference<DataResponse<List<OrderDTO>>>()
        {
        });
        return response.getData();
    }
}
