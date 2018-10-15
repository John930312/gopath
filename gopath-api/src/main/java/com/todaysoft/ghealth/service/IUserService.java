package com.todaysoft.ghealth.service;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.UserDTO;
import com.todaysoft.ghealth.request.UserQueryRequest;

/**
 * @Author: xjw
 * @Date: 2018/10/15 16:11
 */
public interface IUserService
{
    DataResponse<CountRecords<UserDTO>> query(UserQueryRequest request);
}
