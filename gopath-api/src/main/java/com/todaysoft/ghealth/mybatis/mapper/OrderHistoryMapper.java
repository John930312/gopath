package com.todaysoft.ghealth.mybatis.mapper;

import com.todaysoft.ghealth.mybatis.model.OrderHistory;

import java.util.List;

public interface OrderHistoryMapper
{
    int create(OrderHistory record);
    
    List<OrderHistory> getByOrderId(String orderId);
}