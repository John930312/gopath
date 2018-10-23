package com.todaysoft.ghealth.mvc;

import com.todaysoft.ghealth.service.IProductService;
import com.todaysoft.ghealth.wechat.AccountContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todaysoft.ghealth.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductAction
{
    @Autowired
    private IProductService productService;
    
    @Autowired
    private AccountContextHolder holder;
    
    @GetMapping("/detail.jsp")
    public String detail(String id, ModelMap model)
    {
        
        return "product/product_detail";
    }
    
}
