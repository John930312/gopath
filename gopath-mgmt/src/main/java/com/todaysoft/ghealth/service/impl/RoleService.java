package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.AuthorityNode;
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

    @Autowired
    private AccountHolder accountHolder;

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
    
    @Override
    public void create(Role data)
    {
        RoleMaintainRequest request = new RoleMaintainRequest();
        BeanUtils.copyProperties(data, request);
        request.setOperatorId(accountHolder.get().getId());
        request.setOperatorName(accountHolder.get().getName());
        gateway.post("/role/create", request);
    }
    
    @Override
    public void modify(Role data)
    {
        RoleMaintainRequest request = new RoleMaintainRequest();
        BeanUtils.copyProperties(data, request);
        request.setOperatorName(accountHolder.get().getName());
        gateway.post("/role/modify", request);
    }
    
    @Override
    public Role get(Role data)
    {
        RoleMaintainRequest request = new RoleMaintainRequest();
        request.setId(data.getId());
        request.setOperatorName(accountHolder.get().getName());
        DataResponse<RoleDTO> response =
            gateway.post("/role/get", request, new ParameterizedTypeReference<DataResponse<RoleDTO>>()
            {
            });
        return roleWrapper.wrap(response.getData());
    }

    @Override
    public boolean delete(String id)
    {
        RoleMaintainRequest request = new RoleMaintainRequest();
        request.setId(id);
        request.setOperatorName(accountHolder.get().getName());
        DataResponse<Boolean> response =
                gateway.post("/role/delete", request, new ParameterizedTypeReference<DataResponse<Boolean>>()
                {
                });
        return response.getData();
    }
    
    @Override
    public List<AuthorityNode> getAuthorityNodes()
    {
        RoleQueryRequest request = new RoleQueryRequest();
        DataResponse<List<AuthorityNode>> response =
            gateway.post("/authority/getAuthorityNodes", request, new ParameterizedTypeReference<DataResponse<List<AuthorityNode>>>()
            {
            });
        return  response.getData();
    }


    @Override
    public boolean isNameUnique(String id,String name)
    {
        RoleMaintainRequest request = new RoleMaintainRequest();
        request.setName(name);
        request.setId(id);
        DataResponse<Boolean> response = gateway.post("/role/isNameUnique", request, new ParameterizedTypeReference<DataResponse<Boolean>>()
        {
        });
        if (null == response.getData())
        {
            return false;
        }
        return response.getData();
    }
}
