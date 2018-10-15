package com.todaysoft.ghealth.service.wrapper;


import com.todaysoft.ghealth.model.User;
import com.todaysoft.ghealth.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: xjw
 * @Date: 2018/8/26 9:41
 */
@Component
public class UserWrapper extends Wrapper<UserDTO, User>
{
    @Autowired
    private RoleWrapper roleWrapper;

    @Override
    protected String[] getCopyIgnoreProperties()
    {
        return new String[] {"createTime","roles"};
    }
    
    @Override
    protected void setCopyIgnoreProperties(UserDTO source, User target)
    {
        target.setCreateTime(parseDate(source.getCreateTime()));
        target.setRoles(roleWrapper.wrap(source.getRoles()));
    }
}
