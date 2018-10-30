package com.todaysoft.ghealth.DTO;

/**
 * @Author: xjw
 * @Date: 2018/10/18 18:19
 */
public class OrderHistoryDTO
{
    private String id;
    
    private String orderId;
    
    private Integer eventType;
    
    private String eventTime;
    
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
    
    public String getEventTime()
    {
        return eventTime;
    }
    
    public void setEventTime(String eventTime)
    {
        this.eventTime = eventTime;
    }
}
