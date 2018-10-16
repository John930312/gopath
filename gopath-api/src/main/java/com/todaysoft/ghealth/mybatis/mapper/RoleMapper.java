package com.todaysoft.ghealth.mybatis.mapper;



import com.todaysoft.ghealth.mybatis.model.Role;
import com.todaysoft.ghealth.mybatis.model.query.RoleQuery;

import java.util.List;

public interface RoleMapper
{
    int delete(String id);
    
    int insert(Role record);
    
    Role get(String id);
    
    int update(Role record);
    
    int count(RoleQuery query);
    
    List<Role> query(RoleQuery   query);
    
}