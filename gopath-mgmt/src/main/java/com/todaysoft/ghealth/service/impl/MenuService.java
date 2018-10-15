package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.gateway.Gateway;
import com.todaysoft.ghealth.model.Menu;
import com.todaysoft.ghealth.DTO.MenuDTO;
import com.todaysoft.ghealth.security.ResourceAuthorizedDecision;
import com.todaysoft.ghealth.service.IMenuService;
import com.todaysoft.ghealth.service.wrapper.MenuWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xjw
 * @Date: 2018/8/23 16:00
 */
@Service
public class MenuService implements IMenuService
{
    @Autowired
    private Gateway gateway;
    
    @Autowired
    private MenuWrapper menuWrapper;
    
    @Autowired
    private ResourceAuthorizedDecision decision;
    
    @Override
    public List<Menu> getAuthorizedHierarchyMenus()
    {
        DataResponse<CountRecords<MenuDTO>> rsp = gateway.get("/menu/list", new ParameterizedTypeReference<DataResponse<CountRecords<MenuDTO>>>()
        {
        });
        
        List<MenuDTO> data = rsp.getData().getRecords();
        List<MenuDTO> authorizedMenus = authorize(data);
        
        return menuWrapper.wrap(authorizedMenus);
    }
    
    @Override
    public Menu getFirstMenu(List<Menu> menus)
    {
        if (CollectionUtils.isEmpty(menus))
        {
            return null;
        }
        
        Menu menu = menus.get(0);
        
        if (CollectionUtils.isEmpty(menu.getSubmenus()))
        {
            return menu;
        }
        else
        {
            return getFirstMenu(menu.getSubmenus());
        }
    }
    
    private List<MenuDTO> authorize(List<MenuDTO> menus)
    {
        MenuDTO clone;
        List<MenuDTO> authorizedMenus = new ArrayList<MenuDTO>();
        
        for (MenuDTO menu : menus)
        {
            clone = cloneAsAuthorized(menu);
            
            if (isVisible(clone))
            {
                authorizedMenus.add(clone);
            }
        }
        
        return authorizedMenus;
    }
    
    private MenuDTO cloneAsAuthorized(MenuDTO menu)
    {
        MenuDTO clone = new MenuDTO();
        clone.setIcon(menu.getIcon());
        clone.setName(menu.getName());
        clone.setUri(menu.getUri());
        clone.setSubmenus(new ArrayList<MenuDTO>());
        
        MenuDTO cloneSubmenu;
        
        if (!CollectionUtils.isEmpty(menu.getSubmenus()))
        {
            for (MenuDTO submenu : menu.getSubmenus())
            {
                cloneSubmenu = cloneAsAuthorized(submenu);
                
                if (isVisible(cloneSubmenu))
                {
                    clone.getSubmenus().add(cloneSubmenu);
                }
            }
        }
        
        return clone;
    }
    
    private boolean isVisible(MenuDTO menu)
    {
        if (!CollectionUtils.isEmpty(menu.getSubmenus()))
        {
            return true;
        }
        
        if (!StringUtils.isEmpty(menu.getUri()) && decision.isAuthorized(menu.getUri()))
        {
            return true;
        }
        
        return false;
    }
}
