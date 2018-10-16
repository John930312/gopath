package com.todaysoft.ghealth.controller;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.RoleDTO;
import com.todaysoft.ghealth.request.RoleQueryRequest;
import com.todaysoft.ghealth.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController
{
    @Autowired
    private IRoleService service;
    
    @RequestMapping("/pager")
    public DataResponse<CountRecords<RoleDTO>> pager(@RequestBody RoleQueryRequest request)
    {
        return service.pager(request);
    }
    
}
