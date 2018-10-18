package com.todaysoft.ghealth.mybatis.model;

import java.math.BigDecimal;
import java.util.Date;

public class Order
{
    private String id;
    
    private String code;
    
    private String openId;
    
    private Product product;
    
    private Customer customer;
    
    private Agency agency;
    
    private SampleBox sampleBox;
    
    private BigDecimal actualPrice;
    
    private Integer reportPrintRequired;
    
    private Integer sampleType;
    
    private Integer status;
    
    private Date createTime;
    
    private Date samplingTime;
    
    private Boolean deleted;
    
    private Date deleteTime;
    
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
    
    public String getOpenId()
    {
        return openId;
    }
    
    public void setOpenId(String openId)
    {
        this.openId = openId;
    }
    
    public Product getProduct()
    {
        return product;
    }
    
    public void setProduct(Product product)
    {
        this.product = product;
    }
    
    public Customer getCustomer()
    {
        return customer;
    }
    
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
    
    public Agency getAgency()
    {
        return agency;
    }
    
    public void setAgency(Agency agency)
    {
        this.agency = agency;
    }
    
    public SampleBox getSampleBox()
    {
        return sampleBox;
    }
    
    public void setSampleBox(SampleBox sampleBox)
    {
        this.sampleBox = sampleBox;
    }
    
    public BigDecimal getActualPrice()
    {
        return actualPrice;
    }
    
    public void setActualPrice(BigDecimal actualPrice)
    {
        this.actualPrice = actualPrice;
    }
    
    public Integer getReportPrintRequired()
    {
        return reportPrintRequired;
    }
    
    public void setReportPrintRequired(Integer reportPrintRequired)
    {
        this.reportPrintRequired = reportPrintRequired;
    }
    
    public Integer getSampleType()
    {
        return sampleType;
    }
    
    public void setSampleType(Integer sampleType)
    {
        this.sampleType = sampleType;
    }
    
    public Integer getStatus()
    {
        return status;
    }
    
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    public Date getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    
    public Boolean getDeleted()
    {
        return deleted;
    }
    
    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }
    
    public Date getDeleteTime()
    {
        return deleteTime;
    }
    
    public void setDeleteTime(Date deleteTime)
    {
        this.deleteTime = deleteTime;
    }
    
    public Date getSamplingTime()
    {
        return samplingTime;
    }
    
    public void setSamplingTime(Date samplingTime)
    {
        this.samplingTime = samplingTime;
    }
}