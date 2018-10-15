package com.todaysoft.ghealth.DTO;

import java.util.List;

public class RoleDTO
{
    private String name;
    
    private String id;
    
    private String createTime;
    
    private String creatorName;
    
    private String updateTime;
    
    private String updatorName;
    
    private boolean deleted;
    
    private String deleteTime;
    
    private String deletorName;
    
    private String authorityIds;
    
    private List<AuthorityDTO> roleAuthorities;
    
    public List<AuthorityDTO> getRoleAuthorities()
    {
        return roleAuthorities;
    }
    
    public List<UserDTO> users;
    
    public List<UserDTO> getUsers()
    {
        return users;
    }
    
    public void setUsers(List<UserDTO> users)
    {
        this.users = users;
    }
    
    public void setRoleAuthorities(List<AuthorityDTO> roleAuthorities)
    {
        this.roleAuthorities = roleAuthorities;
    }
    
    public String getAuthorityIds()
    {
        return authorityIds;
    }
    
    public void setAuthorityIds(String authorityIds)
    {
        this.authorityIds = authorityIds;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getCreatorName()
    {
        return creatorName;
    }
    
    public void setCreatorName(String creatorName)
    {
        this.creatorName = creatorName;
    }
    
    public String getUpdatorName()
    {
        return updatorName;
    }
    
    public void setUpdatorName(String updatorName)
    {
        this.updatorName = updatorName;
    }
    
    public boolean isDeleted()
    {
        return deleted;
    }
    
    public void setDeleted(boolean deleted)
    {
        this.deleted = deleted;
    }
    
    public String getDeletorName()
    {
        return deletorName;
    }
    
    public void setDeletorName(String deletorName)
    {
        this.deletorName = deletorName;
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
    
    public String getDeleteTime()
    {
        return deleteTime;
    }
    
    public void setDeleteTime(String deleteTime)
    {
        this.deleteTime = deleteTime;
    }
}