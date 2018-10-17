package com.todaysoft.ghealth.service;

import com.todaysoft.ghealth.mybatis.model.UserRole;

/**
 * @Author: ljl
 * @Date: 2018/9/6 0006 11:22
 */
public interface IUserRoleService
{
    void create(UserRole userRole);

    void deleteByuserId(String id);
}
