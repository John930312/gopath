package com.todaysoft.ghealth.service;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.TokenDTO;

/**
 * @Author: xjw
 * @Date: 2018/10/17 16:27
 */
public interface ITokenService
{
    DataResponse<TokenDTO> getToken(String openid);
}
