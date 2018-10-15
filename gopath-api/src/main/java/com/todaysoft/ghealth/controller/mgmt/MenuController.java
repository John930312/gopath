package com.todaysoft.ghealth.controller.mgmt;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.MenuDTO;
import com.todaysoft.ghealth.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xjw
 * @Date: 2018/8/24 11:08
 */
@RestController
@RequestMapping("/menu")
public class MenuController
{
    @Autowired
    private IMenuService menuService;
    
    @RequestMapping("/list")
    public DataResponse<CountRecords<MenuDTO>> list()
    {
        return menuService.getRootMenus();
    }
}
