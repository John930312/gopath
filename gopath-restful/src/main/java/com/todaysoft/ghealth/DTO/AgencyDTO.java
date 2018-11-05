package com.todaysoft.ghealth.DTO;

import java.util.Date;

/**
 * @Author: zyf
 * @Date: 2018/10/17 10:12
 */

public class AgencyDTO
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
    
    private String createTime;
    
    private String creatorName;
    
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
    
    public String getContactName()
    {
        return contactName;
    }
    
    public void setContactName(String contactName)
    {
        this.contactName = contactName;
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

    public String getProvinceText() { return provinceText; }

    public void setProvinceText(String provinceText) { this.provinceText = provinceText; }

    public String getCityText() { return cityText; }

    public void setCityText(String cityText) { this.cityText = cityText; }

    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }
    
    public String getCreatorName()
    {
        return creatorName;
    }
    
    public void setCreatorName(String creatorName)
    {
        this.creatorName = creatorName;
    }
    
}
