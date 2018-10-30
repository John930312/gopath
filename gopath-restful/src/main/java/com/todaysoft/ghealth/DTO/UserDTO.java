package com.todaysoft.ghealth.DTO;

import java.util.List;

/**
 * @Author: xjw
 * @Date: 2018/8/24 17:53
 */
public class UserDTO
{
    private String id;
    
    private String username;
    
    private String password;
    
    private Boolean enabled;
    
    private String name;
    
    private String phone;
    
    private String email;
    
    private Boolean builtin;
    
    private String createTime;
    
    private String creatorName;
    
    private String updateTime;
    
    private String updatorName;
    
    private Boolean deleted;
    
    private String deleteTime;
    
    private String deletorName;
    
    private List<RoleDTO> roles;
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public Boolean getEnabled()
    {
        return enabled;
    }
    
    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
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
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public Boolean getBuiltin()
    {
        return builtin;
    }
    
    public void setBuiltin(Boolean builtin)
    {
        this.builtin = builtin;
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
    
    public Boolean getDeleted()
    {
        return deleted;
    }
    
    public void setDeleted(Boolean deleted)
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
    
    public List<RoleDTO> getRoles()
    {
        return roles;
    }
    
    public void setRoles(List<RoleDTO> roles)
    {
        this.roles = roles;
    }
}
