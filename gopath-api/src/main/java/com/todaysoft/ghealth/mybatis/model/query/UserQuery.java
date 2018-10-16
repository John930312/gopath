package com.todaysoft.ghealth.mybatis.model.query;

import java.util.Set;

/**
 * @Author: xjw
 * @Date: 2018/8/26 10:04
 */
public class UserQuery extends Query
{
    private String userName;
    
    private String name;
    
    private String phone;
    
    private boolean usernameExactMatches;
    
    private Set<String> excludeKeys;
    
    private Boolean builtin;
    
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
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
    
    public boolean isUsernameExactMatches()
    {
        return usernameExactMatches;
    }
    
    public void setUsernameExactMatches(boolean usernameExactMatches)
    {
        this.usernameExactMatches = usernameExactMatches;
    }
    
    public Set<String> getExcludeKeys()
    {
        return excludeKeys;
    }
    
    public void setExcludeKeys(Set<String> excludeKeys)
    {
        this.excludeKeys = excludeKeys;
    }
    
    public Boolean getBuiltin()
    {
        return builtin;
    }
    
    public void setBuiltin(Boolean builtin)
    {
        this.builtin = builtin;
    }
}
