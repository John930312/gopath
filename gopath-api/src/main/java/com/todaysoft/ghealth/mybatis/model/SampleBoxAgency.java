package com.todaysoft.ghealth.mybatis.model;

import java.util.Date;

public class SampleBoxAgency
{
    private String sampleBoxId;
    
    private String agencyId;
    
    private String orderId;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Integer type;
    
    private String remark;
    
    public String getSampleBoxId()
    {
        return sampleBoxId;
    }
    
    public void setSampleBoxId(String sampleBoxId)
    {
        this.sampleBoxId = sampleBoxId;
    }
    
    public String getAgencyId()
    {
        return agencyId;
    }
    
    public void setAgencyId(String agencyId)
    {
        this.agencyId = agencyId;
    }
    
    public String getOrderId()
    {
        return orderId;
    }
    
    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }
    
    public Date getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    
    public Date getUpdateTime()
    {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }
    
    public Integer getType()
    {
        return type;
    }
    
    public void setType(Integer type)
    {
        this.type = type;
    }
    
    public String getRemark()
    {
        return remark;
    }
    
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}