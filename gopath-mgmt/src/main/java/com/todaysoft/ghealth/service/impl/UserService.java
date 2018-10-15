package com.todaysoft.ghealth.service.impl;

import com.todaysoft.ghealth.config.core.AccountHolder;
import com.todaysoft.ghealth.gateway.Gateway;
import com.todaysoft.ghealth.model.User;
import com.todaysoft.ghealth.model.searcher.UserSearcher;
import com.todaysoft.ghealth.DTO.UserDTO;
import com.todaysoft.ghealth.request.UserQueryRequest;
import com.todaysoft.ghealth.service.IUserService;
import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;

import com.todaysoft.ghealth.service.wrapper.UserWrapper;
import com.todaysoft.ghealth.support.Pager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @Author: xjw
 * @Date: 2018/8/24 17:37
 */
@Service
public class UserService implements IUserService
{
    @Autowired
    private Gateway gateway;
    
    @Autowired
    private UserWrapper userWrapper;
    
    @Autowired
    private AccountHolder accountHolder;
    
    @Override
    public Pager<User> pager(UserSearcher searcher, int pageNo, int pageSize)
    {
        UserQueryRequest request = new UserQueryRequest();
        BeanUtils.copyProperties(searcher, request);
        request.setCount(true);
        request.setLimit(pageSize);
        request.setOffset((pageNo - 1) * pageSize);
        
        DataResponse<CountRecords<UserDTO>> response =
            gateway.post("/user/pager", request, new ParameterizedTypeReference<DataResponse<CountRecords<UserDTO>>>()
            {
            });
        
        Pager<User> pager = new Pager<>(pageNo, pageSize, response.getData().getCount().intValue());
        pager.setRecords(userWrapper.wrap(response.getData().getRecords()));
        return pager;
    }
    
}
