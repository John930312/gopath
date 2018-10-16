package com.todaysoft.ghealth.service;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.AuthorityNode;

import java.util.List;

/**
 * @Author: ljl
 * @Date: 2018/8/28 0028 10:34
 */
public interface IAuthorityService
{
    DataResponse<List<AuthorityNode>> getAuthorityNodes();
}
