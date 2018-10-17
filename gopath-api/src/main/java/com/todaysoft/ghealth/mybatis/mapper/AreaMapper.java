package com.todaysoft.ghealth.mybatis.mapper;

import com.todaysoft.ghealth.DTO.AreaDTO;

import java.util.List;

public interface AreaMapper
{
    AreaDTO get(String id);
    
    List<AreaDTO> findByParentId(String parentId);
    
    List<AreaDTO> findProvince();
    
}
