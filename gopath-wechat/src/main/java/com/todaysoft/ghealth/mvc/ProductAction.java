package com.todaysoft.ghealth.mvc;

import com.todaysoft.ghealth.wechat.AccountContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: xjw
 * @Date: 2018/10/20 15:08
import com.todaysoft.ghealth.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: ljl
 * @Date: 2018/10/19 0019 15:18
 */
@Controller
@RequestMapping("/product")
public class ProductAction
{
    @Autowired
    private AccountContextHolder holder;

    @GetMapping("/detail.jsp")
    public String detail(String id, ModelMap model)
    {

        return "product/product_detail";
    }
    private IProductService  productService;

}
