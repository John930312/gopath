package com.todaysoft.ghealth.service;


import com.todaysoft.ghealth.wechat.dto.Token;

public interface ITokenService
{
    Token getToken(String openid);
}
