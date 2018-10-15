package com.todaysoft.ghealth.DTO;

import java.util.Set;

public class AccountDTO
{
    private String id;
    
    private String name;
    
    private String token;
    
    private String userName;
    
    private Boolean builtin;
    
    private Set<String> authorizedResources;
    
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
    
    public String getToken()
    {
        return token;
    }
    
    public void setToken(String token)
    {
        this.token = token;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public Boolean getBuiltin()
    {
        return builtin;
    }
    
    public void setBuiltin(Boolean builtin)
    {
        this.builtin = builtin;
    }
    
    public Set<String> getAuthorizedResources()
    {
        return authorizedResources;
    }
    
    public void setAuthorizedResources(Set<String> authorizedResources)
    {
        this.authorizedResources = authorizedResources;
    }
}
