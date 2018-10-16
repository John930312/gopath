package com.todaysoft.ghealth.mybatis.mapper;

import com.todaysoft.ghealth.mybatis.model.User;
import com.todaysoft.ghealth.mybatis.model.query.UserQuery;

import java.util.List;

public interface UserMapper
{
    long count(UserQuery query);
    
    List<User> query(UserQuery query);
    
    int delete(String id);
    
    int create(User record);
    
    User get(String id);
    
    int modify(User record);
    
    User getUserByUserName(String userName);
    
    List<String> getUserAuthorizedResources(String userId);
}