package com.todaysoft.ghealth.model;

import com.todaysoft.ghealth.DTO.Questionnaire;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Product
{
    private String id;
    
    private String code;
    
    private String name;

    private String imgUrl;

    private String pdfUrl;
    
    private Integer sexRestraint;
    
    private BigDecimal guidingPrice;
    
    private Date startTime;
    
    private Date endTime;
    
    private BigDecimal discountPrice;
    
    private Boolean discount;
    
    private String itemRemark;
    
    private String itemIds;
    
    private Boolean isCommonPackage;
    
    private Boolean enabled;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Boolean deleted;
    
    private Date deleteTime;

    private String questionnairePlatForm;

    private List<Questionnaire> questionnaires;
    
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

    public String getImgUrl() { return imgUrl; }

    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }

    public String getPdfUrl() { return pdfUrl; }

    public void setPdfUrl(String pdfUrl) { this.pdfUrl = pdfUrl; }

    public Integer getSexRestraint()
    {
        return sexRestraint;
    }
    
    public void setSexRestraint(Integer sexRestraint)
    {
        this.sexRestraint = sexRestraint;
    }
    
    public Boolean getCommonPackage()
    {
        return isCommonPackage;
    }
    
    public void setCommonPackage(Boolean commonPackage)
    {
        isCommonPackage = commonPackage;
    }
    
    public BigDecimal getGuidingPrice()
    {
        return guidingPrice;
    }
    
    public void setGuidingPrice(BigDecimal guidingPrice)
    {
        this.guidingPrice = guidingPrice;
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
    
    public Boolean getIsCommonPackage()
    {
        return isCommonPackage;
    }
    
    public void setIsCommonPackage(Boolean isCommonPackage)
    {
        this.isCommonPackage = isCommonPackage;
    }
    
    public Boolean getEnabled()
    {
        return enabled;
    }
    
    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
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

    public String getQuestionnairePlatForm() {
        return questionnairePlatForm;
    }

    public void setQuestionnairePlatForm(String questionnairePlatForm) {
        this.questionnairePlatForm = questionnairePlatForm;
    }

    public List<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(List<Questionnaire> questionnaires) {
        this.questionnaires = questionnaires;
    }
}