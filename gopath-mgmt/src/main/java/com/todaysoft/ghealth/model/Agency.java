package com.todaysoft.ghealth.model;

import java.util.Date;

/**
 * @Author: zyf
 * @Date: 2018/10/17 9:23
 */

public class Agency
{
    private String id;

    private String code;

    private String name;

    private String contactName;

    private String phone;

    private String province;

    private String city;

    private String provinceText;

    private String cityText;

    private String address;

    private Date createTime;

    private String creatorName;

    private Boolean deleted;

    private Date deleteTime;

    private String deletorName;

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

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvinceText() { return provinceText; }

    public void setProvinceText(String provinceText) { this.provinceText = provinceText; }

    public String getCityText() { return cityText; }

    public void setCityText(String cityText) { this.cityText = cityText; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatorName() { return creatorName; }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Boolean getDeleted() { return deleted; }

    public void setDeleted(Boolean deleted) { this.deleted = deleted; }

    public Date getDeleteTime() { return deleteTime; }

    public void setDeleteTime(Date deleteTime) { this.deleteTime = deleteTime; }

    public String getDeletorName() { return deletorName; }

    public void setDeletorName(String deletorName) { this.deletorName = deletorName; }
}
