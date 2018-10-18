package com.todaysoft.ghealth.mybatis.mapper;

import com.todaysoft.ghealth.mybatis.model.Customer;

public interface CustomerMapper
{
    int create(Customer record);
    
    Customer get(String id);
    
    int modify(Customer record);
}