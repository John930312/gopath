package com.todaysoft.ghealth.service.impl;

import com.todaysoft.ghealth.mybatis.mapper.UserRoleMapper;
import com.todaysoft.ghealth.mybatis.model.UserRole;
import com.todaysoft.ghealth.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRoleService implements IUserRoleService
{
    @Autowired
    private UserRoleMapper userRoleMapper;
    
    @Override
    @Transactional
    public void create(UserRole data)
    {
        userRoleMapper.insert(data);
    }

    @Override
    public void deleteByuserId(String id)
    {
        userRoleMapper.deleteByuserId(id);
    }

}
