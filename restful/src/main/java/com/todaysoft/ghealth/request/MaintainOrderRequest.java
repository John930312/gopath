package com.todaysoft.ghealth.request;

import com.todaysoft.ghealth.DTO.SampleBoxDTO;

import java.math.BigDecimal;

/**
 * @Author: xjw
 * @Date: 2018/10/4 9:08
 */
public class MaintainOrderRequest extends MaintainRequest
{
    private String id;
    
    private String code;
    
    private Boolean reportPrintRequired;
    
    private BigDecimal actualPrice;
    
    private Integer status;
    
    private String sampleType;
    
    private SampleBoxDTO sampleBox;
    
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
    
    public Boolean getReportPrintRequired()
    {
        return reportPrintRequired;
    }
    
    public void setReportPrintRequired(Boolean reportPrintRequired)
    {
        this.reportPrintRequired = reportPrintRequired;
    }
    
    public BigDecimal getActualPrice()
    {
        return actualPrice;
    }
    
    public void setActualPrice(BigDecimal actualPrice)
    {
        this.actualPrice = actualPrice;
    }
    
    public Integer getStatus()
    {
        return status;
    }
    
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    public String getSampleType()
    {
        return sampleType;
    }
    
    public void setSampleType(String sampleType)
    {
        this.sampleType = sampleType;
    }
    
    public SampleBoxDTO getSampleBox()
    {
        return sampleBox;
    }
    
    public void setSampleBox(SampleBoxDTO sampleBox)
    {
        this.sampleBox = sampleBox;
    }
}
