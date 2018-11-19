package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.DTO.CustomerDTO;
import com.todaysoft.ghealth.model.Customer;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import java.text.ParseException;

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
        try {
            target.setBirthday(null == (source.getBirthday()) ? null : DateUtils.parseDate(source.getBirthday(), "yyyy-MM-dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
