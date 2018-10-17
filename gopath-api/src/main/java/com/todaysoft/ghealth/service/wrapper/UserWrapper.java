package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.DTO.RoleDTO;
import com.todaysoft.ghealth.DTO.UserDTO;
import com.todaysoft.ghealth.mybatis.model.Role;
import com.todaysoft.ghealth.mybatis.model.User;
import com.todaysoft.ghealth.mybatis.model.query.UserRoleQuery;
import com.todaysoft.ghealth.service.IRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xjw
 * @Date: 2018/8/26 10:20
 */
@Component
public class UserWrapper extends Wrapper<User, UserDTO>
{
    @Autowired
    private IRoleService roleService;

    @Override
    protected String[] getCopyIgnoreProperties()
    {
        return new String[] {"createTime", "updateTime", "deleteTime","roles"};
    }
    
    @Override
    protected void setCopyIgnoreProperties(User source, UserDTO target)
    {
        target.setCreateTime(formatDate(source.getCreateTime()));
        target.setUpdateTime(formatDate(source.getUpdateTime()));
        target.setDeleteTime(formatDate(source.getDeleteTime()));
        UserRoleQuery userRoleQuery = new UserRoleQuery();
        userRoleQuery.setUserId(source.getId());
        List<Role> roles = roleService.getRoles(userRoleQuery);
        List<RoleDTO> datas = new ArrayList<RoleDTO>();
        if (!CollectionUtils.isEmpty(roles))
        {
            for (Role role : roles)
            {
                RoleDTO data = new RoleDTO();
                BeanUtils.copyProperties(role, data, "createTime", "updateTime", "deleteTime");
                datas.add(data);
            }
        }
        target.setRoles(datas);
    }
}
