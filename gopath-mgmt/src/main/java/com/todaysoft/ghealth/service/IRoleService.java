package com.todaysoft.ghealth.service;

import com.todaysoft.ghealth.model.Role;
import com.todaysoft.ghealth.model.searcher.RoleSearcher;
import com.todaysoft.ghealth.support.Pager;

public interface IRoleService
{
    Pager<Role> pager(RoleSearcher searcher, int pageNo, int pageSize);
}
