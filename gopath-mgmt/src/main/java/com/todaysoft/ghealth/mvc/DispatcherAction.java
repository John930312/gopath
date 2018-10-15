package com.todaysoft.ghealth.mvc;



import com.todaysoft.ghealth.model.Menu;
import com.todaysoft.ghealth.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class DispatcherAction
{
    @Autowired
    private IMenuService menuService;
    
    @RequestMapping("/login.jsp")
    public String login()
    {
        return "login";
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model)
    {
        List<Menu> menus = menuService.getAuthorizedHierarchyMenus();
        model.addAttribute("menus", menus);
        model.addAttribute("defaultMenu", menuService.getFirstMenu(menus));
        return "layout";
    }

}
