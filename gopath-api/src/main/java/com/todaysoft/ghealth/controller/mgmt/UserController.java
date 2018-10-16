package com.todaysoft.ghealth.controller.mgmt;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.UserDTO;
import com.todaysoft.ghealth.request.UserMaintainRequest;
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


    @RequestMapping(value = "/details")
    public DataResponse<UserDTO> getDetails(@RequestBody UserMaintainRequest request)
    {
        return userService.get(request.getId());
    }

    @RequestMapping("/create")
    public void create(@RequestBody UserMaintainRequest request)
    {
        userService.create(request);
    }

    @RequestMapping("/modify")
    public void modify(@RequestBody UserMaintainRequest request)
    {
        userService.modify(request);
    }

    @RequestMapping("/delete")
    public void delete(@RequestBody UserMaintainRequest request)
    {
        userService.delete(request);
    }

    @RequestMapping("/change")
    public void change(@RequestBody UserMaintainRequest request)
    {
        userService.change(request);
    }

    @RequestMapping("/isUsernameUnique")
    public DataResponse<Boolean> isUsernameUnique(@RequestBody UserMaintainRequest request)
    {
        return userService.isUsernameUnique(request);
    }
}
