package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.mybatis.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: xjw
 * @Date: 2018/8/31 17:39
 */
@Component
public class OrderWrapper extends Wrapper<Order, OrderDTO>
{
    @Autowired
    private CustomerWrapper customerWrapper;
    
    @Autowired
    private ProductWrapper productWrapper;
    
    @Autowired
    private SampleBoxWrapper sampleBoxWrapper;
    
    @Override
    public String[] getCopyIgnoreProperties()
    {
        return new String[] {"createTime", "samplingTime", "product", "sampleBox", "customer"};
    }
    
    @Override
    public void setCopyIgnoreProperties(Order source, OrderDTO target)
    {
        target.setCreateTime(formatDate(source.getCreateTime()));
        target.setProduct(productWrapper.wrap(source.getProduct()));
        target.setCustomer(customerWrapper.wrap(source.getCustomer()));
        target.setSampleBox(sampleBoxWrapper.wrap(source.getSampleBox()));
    }
    
}
