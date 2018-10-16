package com.todaysoft.ghealth.service;


import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.Authority;
import com.todaysoft.ghealth.DTO.RoleDTO;

import com.todaysoft.ghealth.mybatis.model.query.RoleAuthorityQuery;
import com.todaysoft.ghealth.mybatis.model.query.UserRoleQuery;
import com.todaysoft.ghealth.mybatis.model.User;
import com.todaysoft.ghealth.request.RoleMaintainRequest;
import com.todaysoft.ghealth.request.RoleQueryRequest;

import java.util.List;

/**
 * @Author: ljl
 * @Date: 2018/8/28 0028 9:16
 */
public interface IRoleService
{
    DataResponse<CountRecords<RoleDTO>> pager(RoleQueryRequest request);

    void create(RoleMaintainRequest request);

    void modify(RoleMaintainRequest request);

    DataResponse<Boolean> delete(RoleMaintainRequest request);

    DataResponse<RoleDTO> get(RoleMaintainRequest request);

    DataResponse<Boolean> isNameUnique(RoleMaintainRequest request);

    List<Authority> getRoleAuthorities(RoleAuthorityQuery searcher);

    List<User> getUsers(UserRoleQuery userRoleSearcher);

    List<com.todaysoft.ghealth.mybatis.model.Role> getRoles(UserRoleQuery searcher);
}
