package com.todaysoft.ghealth.mybatis.mapper;

import com.todaysoft.ghealth.mybatis.model.Order;
import com.todaysoft.ghealth.mybatis.model.query.OrderQuery;

import java.util.List;

public interface OrderMapper
{
    int create(Order record);
    
    Order get(String id);
    
    int modify(Order record);
    
    long count(OrderQuery query);
    
    List<Order> query(OrderQuery query);

    List<Order> getByOpenid(String openid);
}