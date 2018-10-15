package com.todaysoft.ghealth.service;


import com.todaysoft.ghealth.model.Menu;

import java.util.List;

/**
 * @Author: xjw
 * @Date: 2018/8/23 15:59
 */
public interface IMenuService
{
    List<Menu> getAuthorizedHierarchyMenus();
    
    Menu getFirstMenu(List<Menu> menus);
}
