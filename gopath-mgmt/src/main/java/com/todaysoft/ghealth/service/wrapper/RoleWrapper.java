package com.todaysoft.ghealth.service.wrapper;


import com.todaysoft.ghealth.model.Role;
import com.todaysoft.ghealth.DTO.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @Author: ljl
 * @Date: 2018/8/27 0027 17:13
 */
@Component
public class RoleWrapper extends Wrapper<RoleDTO, Role>
{
    @Autowired
    private UserWrapper userWrapper;

    @Override
    protected String[] getCopyIgnoreProperties()
    {
        return new String[] {"createTime", "updateTime", "deleteTime", "users"};
    }

    @Override
    protected void setCopyIgnoreProperties(RoleDTO source, Role target)
    {
        if (!CollectionUtils.isEmpty(source.getUsers()))
        {
            target.setUsers(userWrapper.wrap(source.getUsers()));
        }
        target.setCreateTime(parseDate(source.getCreateTime()));
        target.setUpdateTime(parseDate(source.getUpdateTime()));
        target.setDeleteTime(parseDate(source.getDeleteTime()));
    }

}
