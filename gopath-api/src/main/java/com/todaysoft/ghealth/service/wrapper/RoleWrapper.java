package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.DTO.Authority;
import com.todaysoft.ghealth.DTO.RoleDTO;
import com.todaysoft.ghealth.mybatis.model.Role;
import com.todaysoft.ghealth.mybatis.model.User;
import com.todaysoft.ghealth.mybatis.model.query.RoleAuthorityQuery;
import com.todaysoft.ghealth.mybatis.model.query.UserRoleQuery;
import com.todaysoft.ghealth.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: ljl
 * @Date: 2018/8/28 0028 9:39
 */
@Component
public class RoleWrapper extends Wrapper<Role, RoleDTO>
{
    @Autowired
    private IRoleService roleService;

    @Autowired
    private UserWrapper userWrapper;

    @Override
    protected String[] getCopyIgnoreProperties()
    {
        return new String[] {"createTime", "updateTime", "deleteTime"};
    }
    
    @Override
    protected void setCopyIgnoreProperties(Role source, RoleDTO target)
    {
        target.setCreateTime(formatDate(source.getCreateTime()));
        target.setUpdateTime(formatDate(source.getUpdateTime()));
        target.setDeleteTime(formatDate(source.getDeleteTime()));

        RoleAuthorityQuery searcher = new RoleAuthorityQuery();
        searcher.setRoleId(source.getId());
        List<Authority> roleAuthorities = roleService.getRoleAuthorities(searcher);
        target.setRoleAuthorities(roleAuthorities);
        UserRoleQuery userRoleSearcher = new UserRoleQuery();
        userRoleSearcher.setRoleId(source.getId());
        List<User> users = roleService.getUsers(userRoleSearcher);
        target.setUsers(userWrapper.wrap(users));
    }
}
