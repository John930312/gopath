package com.todaysoft.ghealth.mybatis.mapper;

import com.todaysoft.ghealth.mybatis.model.AccountToken;

public interface AccountTokenMapper
{
    int create(AccountToken record);
    
    AccountToken getByOpenid(String id);
    
    int modify(AccountToken record);
}