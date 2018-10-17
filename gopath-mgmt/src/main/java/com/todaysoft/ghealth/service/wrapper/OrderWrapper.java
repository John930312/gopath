package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.model.Customer;
import com.todaysoft.ghealth.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: ljl
 * @Date: 2018/9/10 0010 9:15
 */
@Component
public class OrderWrapper extends Wrapper<OrderDTO, Order>
{
    @Autowired
    private CustomerWrapper customerWrapper;
    
    @Autowired
    private ProductWrapper productWrapper;
    
    @Autowired
    private SampleBoxWrapper sampleBoxWrapper;
    
    @Override
    protected String[] getCopyIgnoreProperties()
    {
        return new String[] {"createTime", "samplingTime", "product", "agency", "sampleBox", "cstomer"};
    }
    
    @Override
    protected void setCopyIgnoreProperties(OrderDTO source, Order target)
    {
        target.setCreateTime(parseDate(source.getCreateTime()));
        target.setProduct(productWrapper.wrap(source.getProduct()));
        target.setCustomer(customerWrapper.wrap(source.getCustomer()));
        target.setSampleBox(sampleBoxWrapper.wrap(source.getSampleBox()));
    }
}
