package com.todaysoft.ghealth.service;

import com.todaysoft.ghealth.DTO.AreaDTO;

import java.util.List;

/**
 * @Author: xjw
 * @Date: 2018/10/14 9:00
 */
public interface IAreaService
{
    List<AreaDTO> findProvince();
    
    List<AreaDTO> findByParentId(String parentId);
}
