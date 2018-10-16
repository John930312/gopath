package com.todaysoft.ghealth.controller.mgmt;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.AuthorityNode;
import com.todaysoft.ghealth.service.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: ljl
 * @Date: 2018/8/28 0028 10:30
 */
@RestController
@RequestMapping("/authority")
public class AuthorityController
{
    @Autowired
    private IAuthorityService service;

    @RequestMapping("/getAuthorityNodes")
    public DataResponse<List<AuthorityNode>> getAuthorityNodes()
    {
        return service.getAuthorityNodes();
    }
}
