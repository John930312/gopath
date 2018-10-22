package com.todaysoft.ghealth.service;

import com.todaysoft.ghealth.DTO.OrderDTO;

import java.util.List;

public interface IOrderService {
    List<OrderDTO> getMyOrder(String openId);
}
