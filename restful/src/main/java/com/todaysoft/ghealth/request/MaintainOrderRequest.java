package com.todaysoft.ghealth.request;

import com.todaysoft.ghealth.DTO.CustomerDTO;
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
    
    private Integer reportPrintRequired;
    
    private BigDecimal actualPrice;
    
    private Integer status;
    
    private Integer sampleType;
    
    private SampleBoxDTO sampleBox;
    
    private CustomerDTO customer;
    
    private String samplingTime;
    
    private String productId;
    
    private String agencyId;
    
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
    
    public CustomerDTO getCustomer()
    {
        return customer;
    }
    
    public void setCustomer(CustomerDTO customer)
    {
        this.customer = customer;
    }
    
    public Integer getReportPrintRequired()
    {
        return reportPrintRequired;
    }
    
    public void setReportPrintRequired(Integer reportPrintRequired)
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
    
    public Integer getSampleType()
    {
        return sampleType;
    }
    
    public void setSampleType(Integer sampleType)
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
    
    public String getSamplingTime()
    {
        return samplingTime;
    }
    
    public void setSamplingTime(String samplingTime)
    {
        this.samplingTime = samplingTime;
    }
    
    public String getProductId()
    {
        return productId;
    }
    
    public void setProductId(String productId)
    {
        this.productId = productId;
    }
    
    public String getAgencyId()
    {
        return agencyId;
    }
    
    public void setAgencyId(String agencyId)
    {
        this.agencyId = agencyId;
    }
}
