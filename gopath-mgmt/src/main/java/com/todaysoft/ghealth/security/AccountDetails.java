package com.todaysoft.ghealth.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class AccountDetails implements UserDetails
{
    private static final long serialVersionUID = -3387979682204989700L;
    
    private String id;
    
    private String name;
    
    private String token;
    
    private String userName;
    
    private boolean builtin;
    
    private Set<String> authorizedResources;
    
    public AccountDetails(String id, String name, String token, String userName, boolean builtin)
    {
        this.id = id;
        this.name = name;
        this.token = token;
        this.builtin = builtin;
        this.userName = userName;
    }
    
    @Override
    public String getUsername()
    {
        return userName;
    }
    
    @Override
    public String getPassword()
    {
        return null;
    }
    
    @Override
    public boolean isEnabled()
    {
        return true;
    }
    
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return Collections.emptySet();
    }
    
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
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public boolean isBuiltin()
    {
        return builtin;
    }
    
    public void setBuiltin(boolean builtin)
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
