package com.todaysoft.ghealth.mybatis.mapper;

import com.todaysoft.ghealth.mybatis.model.Role;
import com.todaysoft.ghealth.mybatis.model.User;
import com.todaysoft.ghealth.mybatis.model.UserRole;
import com.todaysoft.ghealth.mybatis.model.query.UserRoleQuery;

import java.util.List;

public interface UserRoleMapper
{
    int insert(UserRole record);

    void deleteUserRoleByRoleId(String roleId);

    List<User> getUsers(UserRoleQuery searcher);

    List<Role> getRoles(UserRoleQuery searcher);

    int countByRoleId(String id);

    void deleteByuserId(String id);
}