package com.todaysoft.ghealth.wechat.dto;

public class Account
{
    private String openid;
    
    private String token;
    
    private String agencyId;
    
    private Integer livePurchase = 1;
    
    public String getOpenid()
    {
        return openid;
    }
    
    public void setOpenid(String openid)
    {
        this.openid = openid;
    }
    
    public String getToken()
    {
        return token;
    }
    
    public void setToken(String token)
    {
        this.token = token;
    }
    
    public String getAgencyId()
    {
        return agencyId;
    }
    
    public void setAgencyId(String agencyId)
    {
        this.agencyId = agencyId;
    }
    
    public Integer getLivePurchase()
    {
        return livePurchase;
    }
    
    public void setLivePurchase(Integer livePurchase)
    {
        this.livePurchase = livePurchase;
    }
}
