package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.AreaDTO;
import com.todaysoft.ghealth.gateway.Gateway;
import com.todaysoft.ghealth.request.AreaQueryRequest;
import com.todaysoft.ghealth.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: xjw
 * @Date: 2018/11/14 9:01
 */
@Component
public class AreaService implements IAreaService
{
    @Autowired
    private Gateway gateway;
    
    @Override
    public List<AreaDTO> findProvince()
    {
        AreaQueryRequest request = new AreaQueryRequest();
        DataResponse<List<AreaDTO>> response = gateway.post("/area/findProvince", request, new ParameterizedTypeReference<DataResponse<List<AreaDTO>>>()
        {
        });
        return response.getData();
    }
    
    @Override
    public List<AreaDTO> findByParentId(String parentId)
    {
        AreaQueryRequest request = new AreaQueryRequest();
        request.setParentId(parentId);
        DataResponse<List<AreaDTO>> response = gateway.post("/area/findByParentId", request, new ParameterizedTypeReference<DataResponse<List<AreaDTO>>>()
        {
        });
        return response.getData();
    }
}
