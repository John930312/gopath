package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.DTO.CustomerDTO;
import com.todaysoft.ghealth.mybatis.model.Customer;
import org.springframework.stereotype.Component;

/**
 * @Author: xjw
 * @Date: 2018/10/17 11:04
 */
@Component
public class CustomerWrapper extends Wrapper<Customer, CustomerDTO>
{
    @Override
    protected String[] getCopyIgnoreProperties()
    {
        return new String[] {"birthday", "createTime"};
    }
    
    @Override
    protected void setCopyIgnoreProperties(Customer source, CustomerDTO target)
    {
        target.setBirthday(formatDate(source.getBirthday()));
        target.setCreateTime(formatDate(source.getCreateTime()));
    }
}
