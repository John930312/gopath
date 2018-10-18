package com.todaysoft.ghealth.mybatis.model;

import java.util.Date;

public class AccountToken
{
    private String id;
    
    private String token;
    
    private String openid;
    
    private Date expireTime;
    
    private Date createTime;
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getToken()
    {
        return token;
    }
    
    public void setToken(String token)
    {
        this.token = token;
    }
    
    public String getOpenid()
    {
        return openid;
    }
    
    public void setOpenid(String openid)
    {
        this.openid = openid;
    }
    
    public Date getExpireTime()
    {
        return expireTime;
    }
    
    public void setExpireTime(Date expireTime)
    {
        this.expireTime = expireTime;
    }
    
    public Date getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
}