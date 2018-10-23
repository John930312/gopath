package com.todaysoft.ghealth.service;

import com.todaysoft.ghealth.DTO.OrderDTO;

import java.util.List;

/**
 * @Author: xjw
 * @Date: 2018/10/22 16:03
 */
public interface IOrderService
{
    String create(OrderDTO data);
    
    List<OrderDTO> getMyOrder(String openId);
}
