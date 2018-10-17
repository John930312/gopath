package com.todaysoft.ghealth.DTO;

import java.math.BigDecimal;

/**
 * @Author: ljl
 * @Date: 2018/8/29 0029 14:05
 */
public class ProductDTO
{
    private String id;
    
    private String code;
    
    private String name;
    
    private Integer sexRestraint;
    
    private BigDecimal guidingPrice;
    
    private String startTime;
    
    private String endTime;
    
    private BigDecimal discountPrice;
    
    private Boolean discount;
    
    private String itemRemark;
    
    private String itemIds;
    
    private Boolean isCommonPackage;
    
    private Boolean enabled;
    
    private String createTime;
    
    private String updateTime;
    
    private Boolean deleted;
    
    private String deleteTime;
    
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
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public Integer getSexRestraint()
    {
        return sexRestraint;
    }
    
    public void setSexRestraint(Integer sexRestraint)
    {
        this.sexRestraint = sexRestraint;
    }
    
    public BigDecimal getGuidingPrice()
    {
        return guidingPrice;
    }
    
    public void setGuidingPrice(BigDecimal guidingPrice)
    {
        this.guidingPrice = guidingPrice;
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
    
    public BigDecimal getDiscountPrice()
    {
        return discountPrice;
    }
    
    public void setDiscountPrice(BigDecimal discountPrice)
    {
        this.discountPrice = discountPrice;
    }
    
    public Boolean getDiscount()
    {
        return discount;
    }
    
    public void setDiscount(Boolean discount)
    {
        this.discount = discount;
    }
    
    public String getItemRemark()
    {
        return itemRemark;
    }
    
    public void setItemRemark(String itemRemark)
    {
        this.itemRemark = itemRemark;
    }
    
    public String getItemIds()
    {
        return itemIds;
    }
    
    public void setItemIds(String itemIds)
    {
        this.itemIds = itemIds;
    }
    
    public Boolean getCommonPackage()
    {
        return isCommonPackage;
    }
    
    public void setCommonPackage(Boolean commonPackage)
    {
        isCommonPackage = commonPackage;
    }
    
    public Boolean getEnabled()
    {
        return enabled;
    }
    
    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
    }
    
    public String getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }
    
    public String getUpdateTime()
    {
        return updateTime;
    }
    
    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }
    
    public Boolean getDeleted()
    {
        return deleted;
    }
    
    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }
    
    public String getDeleteTime()
    {
        return deleteTime;
    }
    
    public void setDeleteTime(String deleteTime)
    {
        this.deleteTime = deleteTime;
    }
}
