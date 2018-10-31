package com.todaysoft.ghealth.DTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: xjw
 * @Date: 2018/8/28 11:00
 */
public class OrderDTO
{
    private String id;
    
    private String code;
    
    private Integer reportPrintRequired;
    
    private BigDecimal actualPrice;
    
    private Integer status;

    private String orderUrl;
    
    private String agencyName;
    
    private CustomerDTO customer;
    
    private String createTime;
    
    private String createName;
    
    private String samplingTime;
    
    private String samplingName;
    
    private Integer sampleType;
    
    private ProductDTO product;
    
    private SampleBoxDTO sampleBox;
    
    private List<OrderHistoryDTO> orderHistory;
    
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

    public String getOrderUrl() { return orderUrl; }

    public void setOrderUrl(String orderUrl) { this.orderUrl = orderUrl; }

    public String getAgencyName()
    {
        return agencyName;
    }
    
    public void setAgencyName(String agencyName)
    {
        this.agencyName = agencyName;
    }
    
    public CustomerDTO getCustomer()
    {
        return customer;
    }
    
    public void setCustomer(CustomerDTO customer)
    {
        this.customer = customer;
    }
    
    public String getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }
    
    public String getCreateName()
    {
        return createName;
    }
    
    public void setCreateName(String createName)
    {
        this.createName = createName;
    }
    
    public String getSamplingTime()
    {
        return samplingTime;
    }
    
    public void setSamplingTime(String samplingTime)
    {
        this.samplingTime = samplingTime;
    }
    
    public String getSamplingName()
    {
        return samplingName;
    }
    
    public void setSamplingName(String samplingName)
    {
        this.samplingName = samplingName;
    }
    
    public Integer getSampleType()
    {
        return sampleType;
    }
    
    public void setSampleType(Integer sampleType)
    {
        this.sampleType = sampleType;
    }
    
    public ProductDTO getProduct()
    {
        return product;
    }
    
    public void setProduct(ProductDTO product)
    {
        this.product = product;
    }
    
    public SampleBoxDTO getSampleBox()
    {
        return sampleBox;
    }
    
    public void setSampleBox(SampleBoxDTO sampleBox)
    {
        this.sampleBox = sampleBox;
    }
    
    public List<OrderHistoryDTO> getOrderHistory()
    {
        return orderHistory;
    }
    
    public void setOrderHistory(List<OrderHistoryDTO> orderHistory)
    {
        this.orderHistory = orderHistory;
    }
}
