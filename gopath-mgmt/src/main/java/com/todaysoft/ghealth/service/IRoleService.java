package com.todaysoft.ghealth.service;

import com.todaysoft.ghealth.DTO.AuthorityNode;
import com.todaysoft.ghealth.model.Role;
import com.todaysoft.ghealth.model.searcher.RoleSearcher;
import com.todaysoft.ghealth.support.Pager;

import java.util.List;

public interface IRoleService
{
    Pager<Role> pager(RoleSearcher searcher, int pageNo, int pageSize);

    void create(Role data);

    void modify(Role data);

    Role get(Role data);

    boolean delete(String id);

    List<AuthorityNode> getAuthorityNodes();

    boolean isNameUnique(String id, String name);
}
