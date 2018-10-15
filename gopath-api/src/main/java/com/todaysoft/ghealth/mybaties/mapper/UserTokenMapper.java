package com.todaysoft.ghealth.mybaties.mapper;


import com.todaysoft.ghealth.mybaties.model.UserToken;

public interface UserTokenMapper
{
    int create(UserToken record);
    
    UserToken get(String id);
    
    int modify(UserToken record);
    
    UserToken getEntityByUserId(String userId);
}