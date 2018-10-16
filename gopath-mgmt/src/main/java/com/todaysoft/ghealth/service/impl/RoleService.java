package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;

import com.todaysoft.ghealth.DTO.RoleDTO;
import com.todaysoft.ghealth.config.core.AccountHolder;
import com.todaysoft.ghealth.gateway.Gateway;
import com.todaysoft.ghealth.model.Role;
import com.todaysoft.ghealth.model.searcher.RoleSearcher;
import com.todaysoft.ghealth.request.RoleMaintainRequest;
import com.todaysoft.ghealth.request.RoleQueryRequest;
import com.todaysoft.ghealth.service.IRoleService;
import com.todaysoft.ghealth.service.wrapper.RoleWrapper;
import com.todaysoft.ghealth.support.Pager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService
{
    @Autowired
    private Gateway gateway;
    
    @Autowired
    private RoleWrapper roleWrapper;
    
    @Override
    public Pager<Role> pager(RoleSearcher searcher, int pageNo, int pageSize)
    {
        RoleQueryRequest request = new RoleQueryRequest();
        BeanUtils.copyProperties(searcher, request);
        request.setCount(true);
        request.setLimit(pageSize);
        request.setOffset((pageNo - 1) * pageSize);
        DataResponse<CountRecords<RoleDTO>> response =
            gateway.post("/role/pager", request, new ParameterizedTypeReference<DataResponse<CountRecords<RoleDTO>>>()
            {
            });
        if (null == response.getData() || null == response.getData().getCount())
        {
            throw new IllegalStateException();
        }
        Pager<Role> pager = new Pager<>(pageNo, pageSize, response.getData().getCount().intValue());
        pager.setRecords(roleWrapper.wrap(response.getData().getRecords()));
        return pager;
    }
}
