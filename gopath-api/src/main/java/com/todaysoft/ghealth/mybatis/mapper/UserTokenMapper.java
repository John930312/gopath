package com.todaysoft.ghealth.mybatis.mapper;


import com.todaysoft.ghealth.mybatis.model.UserToken;

public interface UserTokenMapper
{
    int create(UserToken record);
    
    UserToken get(String id);
    
    int modify(UserToken record);
    
    UserToken getEntityByUserId(String userId);
}