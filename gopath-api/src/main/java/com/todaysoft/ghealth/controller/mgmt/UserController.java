package com.todaysoft.ghealth.controller.mgmt;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.UserDTO;
import com.todaysoft.ghealth.request.UserQueryRequest;
import com.todaysoft.ghealth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xjw
 * @Date: 2018/10/15 16:10
 */
@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private IUserService userService;
    
    @PostMapping("/pager")
    public DataResponse<CountRecords<UserDTO>> pager(@RequestBody UserQueryRequest request)
    {
        return userService.query(request);
    }
}
