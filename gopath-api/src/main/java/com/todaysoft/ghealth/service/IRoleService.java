package com.todaysoft.ghealth.service;


import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.RoleDTO;

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
}
