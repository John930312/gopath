package com.todaysoft.ghealth.mybatis.mapper;

import com.todaysoft.ghealth.DTO.Authority;

import java.util.List;

public interface AuthorityMapper
{
    int delete(String id);
    
    int insert(Authority record);
    
    Authority get(String id);
    
    int update(Authority record);
    
    List<Authority> list();
    
    List<Authority> getAuthoritiesByParentId(String id);
    
    List<Authority> getParentAuthorities();
    
}