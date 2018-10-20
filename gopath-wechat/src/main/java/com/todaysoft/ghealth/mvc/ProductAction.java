package com.todaysoft.ghealth.mvc;

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
    private IProductService  productService;

}
