package com.todaysoft.ghealth.DTO;

import java.util.List;

public class MenuDTO
{
    private String name;
    
    private String uri;
    
    private String icon;
    
    private List<MenuDTO> submenus;
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getUri()
    {
        return uri;
    }
    
    public void setUri(String uri)
    {
        this.uri = uri;
    }
    
    public String getIcon()
    {
        return icon;
    }
    
    public void setIcon(String icon)
    {
        this.icon = icon;
    }
    
    public List<MenuDTO> getSubmenus()
    {
        return submenus;
    }
    
    public void setSubmenus(List<MenuDTO> submenus)
    {
        this.submenus = submenus;
    }
}
