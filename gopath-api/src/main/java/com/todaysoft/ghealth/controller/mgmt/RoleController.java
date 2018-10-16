package com.todaysoft.ghealth.controller.mgmt;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.RoleDTO;
import com.todaysoft.ghealth.request.RoleMaintainRequest;
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

    @RequestMapping("/create")
    public void create(@RequestBody RoleMaintainRequest request) {
        service.create(request);
    }

    @RequestMapping("/modify")
    public void modify(@RequestBody RoleMaintainRequest request) {
        service.modify(request);
    }

    @RequestMapping("/delete")
    public DataResponse<Boolean> delete(@RequestBody RoleMaintainRequest request) {
        return service.delete(request);
    }

    @RequestMapping("/get")
    public DataResponse<RoleDTO> get(@RequestBody RoleMaintainRequest request) {
        return service.get(request);
    }

    @RequestMapping("/isNameUnique")
    public DataResponse<Boolean> isNameUnique(@RequestBody RoleMaintainRequest request) {
        return service.isNameUnique(request);
    }
}
