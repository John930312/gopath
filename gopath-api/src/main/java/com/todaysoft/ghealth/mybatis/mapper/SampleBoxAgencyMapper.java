package com.todaysoft.ghealth.mybatis.mapper;

import com.todaysoft.ghealth.mybatis.model.SampleBoxAgency;
import org.apache.ibatis.annotations.Param;

public interface SampleBoxAgencyMapper
{
    int create(SampleBoxAgency record);
}