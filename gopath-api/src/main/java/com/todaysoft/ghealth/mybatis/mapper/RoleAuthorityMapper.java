package com.todaysoft.ghealth.mybatis.mapper;


import com.todaysoft.ghealth.DTO.Authority;
import com.todaysoft.ghealth.mybatis.model.RoleAuthority;
import com.todaysoft.ghealth.mybatis.model.query.RoleAuthorityQuery;

import java.util.List;

public interface RoleAuthorityMapper
{
    int insert(RoleAuthority record);
    
    List<Authority> search(RoleAuthorityQuery searcher);
    
    void deleteRoleAuthority(String roleId);
}