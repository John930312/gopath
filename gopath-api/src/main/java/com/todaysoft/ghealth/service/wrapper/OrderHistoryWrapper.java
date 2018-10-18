package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.DTO.OrderHistoryDTO;
import com.todaysoft.ghealth.mybatis.model.OrderHistory;
import org.springframework.stereotype.Component;

/**
 * @Author: xjw
 * @Date: 2018/10/18 18:20
 */
@Component
public class OrderHistoryWrapper extends Wrapper<OrderHistory, OrderHistoryDTO>
{
    @Override
    public String[] getCopyIgnoreProperties()
    {
        return new String[] {"eventTime"};
    }
    
    @Override
    public void setCopyIgnoreProperties(OrderHistory source, OrderHistoryDTO target)
    {
        target.setEventTime(formatDate(source.getEventTime()));
    }
    
}
