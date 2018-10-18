package com.todaysoft.ghealth.request;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: ljl
 * @Date: 2018/8/29 0029 14:01
 */
public class ProductMaintainRequest extends MaintainRequest {
    private String id;

    private String code;

    private String name;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSexRestraint() {
        return sexRestraint;
    }

    public void setSexRestraint(Integer sexRestraint) {
        this.sexRestraint = sexRestraint;
    }

    public BigDecimal getGuidingPrice() {
        return guidingPrice;
    }

    public void setGuidingPrice(BigDecimal guidingPrice) {
        this.guidingPrice = guidingPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Boolean getDiscount() {
        return discount;
    }

    public void setDiscount(Boolean discount) {
        this.discount = discount;
    }

    public String getItemRemark() {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark;
    }

    public String getItemIds() {
        return itemIds;
    }

    public void setItemIds(String itemIds) {
        this.itemIds = itemIds;
    }

    public Boolean getCommonPackage() {
        return isCommonPackage;
    }

    public void setCommonPackage(Boolean commonPackage) {
        isCommonPackage = commonPackage;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
