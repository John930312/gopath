package com.todaysoft.ghealth.service;

import com.todaysoft.ghealth.model.Order;
import com.todaysoft.ghealth.model.searcher.OrderSearcher;
import com.todaysoft.ghealth.support.Pager;

import java.util.List;

/**
 * @Author: xjw
 * @Date: 2018/10/16 14:55
 */
public interface IOrderService
{
    Pager<Order> pager(OrderSearcher searcher, int pageNo, int pageSize);
    
    List<Order> list(OrderSearcher searcher);
    
    Order get(String id);
    
    void modify(Order data);
    
    Boolean isUniqueSampleBoxCode(String sampleBoxCode);
}
