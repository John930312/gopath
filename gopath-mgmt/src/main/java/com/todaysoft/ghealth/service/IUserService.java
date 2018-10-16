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

    void modify(User data);

    void create(User data);

    void delete(User data);

    User get(String id);

    boolean isUsernameUnique(String username, String id);

    void change(User data);
}
