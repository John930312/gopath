package com.todaysoft.ghealth.mybatis.model;

import java.util.Date;

public class OrderHistory
{
    private String id;
    
    private String orderId;
    
    private Integer eventType;
    
    private Date eventTime;
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getOrderId()
    {
        return orderId;
    }
    
    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    
    public Integer getEventType()
    {
        return eventType;
    }
    
    public void setEventType(Integer eventType)
    {
        this.eventType = eventType;
    }
    
    public Date getEventTime()
    {
        return eventTime;
    }
    
    public void setEventTime(Date eventTime)
    {
        this.eventTime = eventTime;
    }
}