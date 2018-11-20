package com.todaysoft.ghealth.model;

import java.util.Date;
import java.util.List;

public class SampleBox
{
    private String id;
    
    private String code;
    
    private String name;
    
    private String phone;
    
    private boolean binded;
    
    private String province;
    
    private String city;
    
    private String address;
    
    private Date createTime;

    private String provinceText;

    private String cityText;
    
    private String agencyId;

    private Integer type;//采样盒新增类型：0-系统下单；1-后台新增；

    private List<String> sampleBoxCode;
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getPhone()
    {
        return phone;
    }
    
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    
    public String getProvince()
    {
        return province;
    }
    
    public void setProvince(String province)
    {
        this.province = province;
    }
    
    public String getCity()
    {
        return city;
    }
    
    public void setCity(String city)
    {
        this.city = city;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public Date getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public boolean isBinded()
    {
        return binded;
    }
    
    public void setBinded(boolean binded)
    {
        this.binded = binded;
    }

    public String getProvinceText() {
        return provinceText;
    }

    public void setProvinceText(String provinceText) {
        this.provinceText = provinceText;
    }

    public String getCityText() {
        return cityText;
    }

    public void setCityText(String cityText) {
        this.cityText = cityText;
    }

    public String getAgencyId()
    {
        return agencyId;
    }

    public void setAgencyId(String agencyId)
    {
        this.agencyId = agencyId;
    }

    public List<String> getSampleBoxCode() {
        return sampleBoxCode;
    }

    public void setSampleBoxCode(List<String> sampleBoxCode) {
        this.sampleBoxCode = sampleBoxCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}