package com.todaysoft.ghealth.mybatis.mapper;

import com.todaysoft.ghealth.mybatis.model.SampleBox;
import com.todaysoft.ghealth.mybatis.model.query.SampleBoxQuery;

import java.util.List;

public interface SampleBoxMapper
{
    int create(SampleBox record);
    
    int modify(SampleBox record);
    
    SampleBox get(String id);

    long getByCode(String code);

    long count(SampleBoxQuery query);

    List<SampleBox> query(SampleBoxQuery query);

    List<SampleBox> checkByCode(String code);

    void deleteById(String id);
}