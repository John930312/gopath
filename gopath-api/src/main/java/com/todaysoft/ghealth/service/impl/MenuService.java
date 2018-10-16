package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;

import com.todaysoft.ghealth.mybatis.mapper.MenuMapper;
import com.todaysoft.ghealth.mybatis.model.Menu;
import com.todaysoft.ghealth.DTO.MenuDTO;
import com.todaysoft.ghealth.service.IMenuService;
import com.todaysoft.ghealth.service.wrapper.MenuWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class MenuService implements IMenuService
{
    @Autowired
    private MenuMapper menuMapper;
    
    @Autowired
    private MenuWrapper menuWrapper;
    
    @Override
    public DataResponse<CountRecords<MenuDTO>> getRootMenus()
    {
        List<Menu> records = getRootMenusCascade();
        List<MenuDTO> menus = menuWrapper.wrap(records);
        CountRecords<MenuDTO> data = new CountRecords<MenuDTO>();
        data.setRecords(menus);
        return new DataResponse<CountRecords<MenuDTO>>(data);
    }
    
    private List<Menu> getRootMenusCascade()
    {
        List<Menu> records = menuMapper.select();
        
        if (CollectionUtils.isEmpty(records))
        {
            return Collections.emptyList();
        }
        
        Map<String, Menu> context = new HashMap<String, Menu>();
        List<Menu> rootMenus = new ArrayList<Menu>();
        
        for (Menu record : records)
        {
            if (StringUtils.isEmpty(record.getParentId()))
            {
                rootMenus.add(record);
            }
            
            context.put(record.getId(), record);
        }
        
        Menu parentMenu;
        
        for (Menu record : records)
        {
            if (StringUtils.isEmpty(record.getParentId()))
            {
                continue;
            }
            
            parentMenu = context.get(record.getParentId());
            
            if (null == parentMenu)
            {
                continue;
            }
            
            if (null == parentMenu.getSubmenus())
            {
                parentMenu.setSubmenus(new ArrayList<Menu>());
            }
            
            parentMenu.getSubmenus().add(record);
        }
        
        context.clear();
        sortMenus(rootMenus);
        return rootMenus;
    }
    
    private void sortMenus(List<Menu> menus)
    {
        if (CollectionUtils.isEmpty(menus))
        {
            return;
        }
        
        Collections.sort(menus, new Comparator<Menu>()
        {
            @Override
            public int compare(Menu o1, Menu o2)
            {
                int o1s = o1.getSort() == null ? 999 : o1.getSort().intValue();
                int o2s = o2.getSort() == null ? 999 : o2.getSort().intValue();
                
                if (o1s == o2s)
                {
                    return 0;
                }
                
                return o1s > o2s ? 1 : -1;
            }
        });
        
        for (Menu menu : menus)
        {
            sortMenus(menu.getSubmenus());
        }
    }
    
}
