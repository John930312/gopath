package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.DTO.CustomerDTO;
import com.todaysoft.ghealth.model.Customer;
import org.springframework.stereotype.Component;

/**
 * @Author: xjw
 * @Date: 2018/10/17 11:08
 */
@Component
public class CustomerWrapper extends Wrapper<CustomerDTO, Customer>
{
    @Override
    protected String[] getCopyIgnoreProperties()
    {
        return new String[] {"createTime", "birthday"};
    }
    
    @Override
    protected void setCopyIgnoreProperties(CustomerDTO source, Customer target)
    {
        target.setCreateTime(parseDate(source.getCreateTime()));
        target.setBirthday(parseDate(source.getBirthday()));
    }
}
