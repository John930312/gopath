package com.todaysoft.ghealth.service;


import com.todaysoft.ghealth.model.User;
import com.todaysoft.ghealth.model.searcher.UserSearcher;
import com.todaysoft.ghealth.support.Pager;

/**
 * @Author: xjw
 * @Date: 2018/8/24 17:27
 */
public interface IUserService
{
    Pager<User> pager(UserSearcher searcher, int pageNo, int pageSize);
}
