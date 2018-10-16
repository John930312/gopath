package com.todaysoft.ghealth.request;

public class RoleMaintainRequest extends MaintainRequest
{
    private String name;
    
    private String id;
    
    private String authorityIds;
    
    private String userId;

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
    
    public String getAuthorityIds()
    {
        return authorityIds;
    }
    
    public void setAuthorityIds(String authorityIds)
    {
        this.authorityIds = authorityIds;
    }
    
    public String getUserId()
    {
        return userId;
    }
    
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
}
