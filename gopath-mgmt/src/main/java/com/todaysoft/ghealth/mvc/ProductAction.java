package com.todaysoft.ghealth.mvc;

import com.todaysoft.ghealth.form.FormInputView;
import com.todaysoft.ghealth.form.FormSubmitHandler;
import com.todaysoft.ghealth.model.Product;
import com.todaysoft.ghealth.model.searcher.ProductSearcher;
import com.todaysoft.ghealth.service.IProductService;
import com.todaysoft.ghealth.support.ModelResolver;
import com.todaysoft.ghealth.support.Pager;
import com.todaysoft.ghealth.support.PagerArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/product")
public class ProductAction
{
    @Autowired
    private IProductService productService;

    @RequestMapping("/list.jsp")
    public String paginations(ProductSearcher searcher, PagerArgs pagerArgs, ModelMap model, HttpSession session)
    {
        Pager<Product> pagination = productService.pager(searcher, pagerArgs.getPageNo(), pagerArgs.getPageSize());
        model.addAttribute("pagination", pagination);
        model.addAttribute("searcher", searcher);
        session.setAttribute("s-searcher", searcher);
        return "product/product_list";
    }

    @RequestMapping(value = "/create.jsp", method = RequestMethod.GET)
    @FormInputView
    public String create(ModelMap model)
    {
        return "product/product_form";
    }

    @RequestMapping(value = "/create.jsp", method = RequestMethod.POST)
    @FormSubmitHandler
    public String create(Product data, ModelMap model, HttpSession session)
    {
        productService.create(data);
        return redirectList(model, session, "/product/list.jsp");
    }

    @RequestMapping(value = "/modify.jsp", method = RequestMethod.GET)
    @FormInputView
    public String modify(String id, ModelMap model, HttpSession session)
    {
        Product data = productService.get(id);
        model.addAttribute("data", data);
        return "product/product_form";
    }

    @RequestMapping(value = "/modify.jsp", method = RequestMethod.POST)
    @FormSubmitHandler
    public String modify(Product data, ModelMap model, HttpSession session)
    {
        productService.modify(data);
        return redirectList(model, session, "/product/list.jsp");
    }

    @RequestMapping("/display.jsp")
    public String display(String id, ModelMap model)
    {
        Product data = productService.get(id);
        model.addAttribute("data", data);
        return "product/product_details";
    }

    @RequestMapping("/code_unique.do")
    @ResponseBody
    public boolean isCodeUnique(String id, String code, ModelMap model)
    {
        return productService.isCodeUnique(id, code);
    }

    @RequestMapping(value = "/reload.do")
    public String reload(ModelMap model, HttpSession session)
    {
        return redirectList(model, session);
    }

    private String redirectList(ModelMap model, HttpSession session)
    {
        model.clear();
        new ModelResolver(session.getAttribute("s-searcher"), model).resolve();
        return "redirect:/product/list.jsp";
    }

    private String redirectList(ModelMap model, HttpSession session, String url)
    {
        model.clear();
        new ModelResolver(session.getAttribute("s-searcher"), model).resolve();
        return "redirect:" + url;
    }
}
