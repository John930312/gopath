package com.todaysoft.ghealth.model.searcher;

import java.util.Date;

/**
 * @Author: xjw
 * @Date: 2018/10/14
 */
public class OrderSearcher
{
    private String id;
    
    private String code;
    
    private String consignee;
    
    private String samplingPhone;
    
    private String productName;
    
    private String status;
    
    private String agencyName;
    
    private Date startTime;
    
    private Date endTime;
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public String getConsignee()
    {
        return consignee;
    }
    
    public void setConsignee(String consignee)
    {
        this.consignee = consignee;
    }
    
    public String getSamplingPhone()
    {
        return samplingPhone;
    }
    
    public void setSamplingPhone(String samplingPhone)
    {
        this.samplingPhone = samplingPhone;
    }
    
    public String getProductName()
    {
        return productName;
    }
    
    public void setProductName(String productName)
    {
        this.productName = productName;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public String getAgencyName()
    {
        return agencyName;
    }
    
    public void setAgencyName(String agencyName)
    {
        this.agencyName = agencyName;
    }
    
    public Date getStartTime()
    {
        return startTime;
    }
    
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }
    
    public Date getEndTime()
    {
        return endTime;
    }
    
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }
}
