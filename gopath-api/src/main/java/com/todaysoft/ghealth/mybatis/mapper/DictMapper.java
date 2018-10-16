package com.todaysoft.ghealth.mybatis.mapper;

import com.todaysoft.ghealth.DTO.Dict;
import com.todaysoft.ghealth.mybatis.model.query.DictQuery;

import java.util.List;

public interface DictMapper
{
    List<Dict> findList(DictQuery searcher);
    
    Dict findByCategoryAndValue(DictQuery searcher);
}
