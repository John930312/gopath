package com.todaysoft.ghealth.mybatis.model.query;

/**
 * @Author: xjw
 * @Date: 2018/10/14 16:22
 */
public class OrderQuery extends Query
{
    private String id;
    
    private String code;
    
    private String consignee;
    
    private String samplingPhone;
    
    private String productName;
    
    private String status;
    
    private String agencyName;
    
    private String startTime;
    
    private String endTime;
    
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
    
    public String getStartTime()
    {
        return startTime;
    }
    
    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }
    
    public String getEndTime()
    {
        return endTime;
    }
    
    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }
}
