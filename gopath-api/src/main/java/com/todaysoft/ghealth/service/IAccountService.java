package com.todaysoft.ghealth.service;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.AccountDTO;
import com.todaysoft.ghealth.DTO.LoginDTO;

/**
 * @Author: xjw
 * @Date: 2018/10/15 14:32
 */
public interface IAccountService
{
    DataResponse<AccountDTO> login(LoginDTO request);
}
