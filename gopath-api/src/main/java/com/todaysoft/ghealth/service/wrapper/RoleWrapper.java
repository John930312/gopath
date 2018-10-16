package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.DTO.RoleDTO;
import com.todaysoft.ghealth.mybaties.model.Role;
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
    }
}
