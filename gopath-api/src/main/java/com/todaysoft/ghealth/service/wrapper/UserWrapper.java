package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.DTO.UserDTO;
import com.todaysoft.ghealth.mybaties.model.User;
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
    }
}
