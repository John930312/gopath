package com.todaysoft.ghealth.mybatis.mapper;

import com.todaysoft.ghealth.mybatis.model.SampleBox;

public interface SampleBoxMapper
{
    int create(SampleBox record);
    
    int modify(SampleBox record);
    
    SampleBox get(String id);

    long getByCode(String code);
}