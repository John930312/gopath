package com.todaysoft.ghealth.mybatis.mapper;

import com.todaysoft.ghealth.mybatis.model.Agency;
import com.todaysoft.ghealth.mybatis.model.query.AgencyQuery;

import java.util.List;

public interface AgencyMapper
{
    long count(AgencyQuery query);

    List<Agency> query(AgencyQuery query);

    Agency get(String id);

    void create(Agency record);

    int modify(Agency record);
}
