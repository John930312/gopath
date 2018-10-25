package com.todaysoft.ghealth.mvc;

import com.todaysoft.ghealth.form.FormInputView;
import com.todaysoft.ghealth.form.FormSubmitHandler;
import com.todaysoft.ghealth.model.Slideshow;
import com.todaysoft.ghealth.model.searcher.SlideshowSearcher;
import com.todaysoft.ghealth.service.ISlideshowService;
import com.todaysoft.ghealth.support.ModelResolver;
import com.todaysoft.ghealth.support.Pager;
import com.todaysoft.ghealth.support.PagerArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * @Author: zyf
 * @Date: 2018/10/23 10:43
 */
@Controller
@RequestMapping("/slideshow")
public class SlideshowAction
{
    @Autowired
    private ISlideshowService slideshowService;
    
    @RequestMapping(value = "/list.jsp", produces = "text/html;charset=UTF-8")
    public String pager(SlideshowSearcher searcher, PagerArgs pageArgs, ModelMap model, HttpSession session)
    {
        Pager<Slideshow> pager = slideshowService.pager(searcher, pageArgs.getPageNo(), pageArgs.getPageSize());
        model.addAttribute("searcher", searcher);
        model.addAttribute("pagination", pager);
        session.setAttribute("s-searcher", searcher);
        return "slideshow/slideshow_list";
    }
    
    @RequestMapping(value = "/create.jsp", method = RequestMethod.GET)
    @FormInputView
    public String create(ModelMap model)
    {
        return "slideshow/slideshow_form";
    }
    
    @RequestMapping(value = "/create.jsp", method = RequestMethod.POST)
    @FormSubmitHandler
    public String create(Slideshow data, ModelMap model, HttpSession session)
    {
        slideshowService.create(data);
        return redirectList(model, session, "/slideshow/list.jsp");
    }
    
    private String redirectList(ModelMap model, HttpSession session, String url)
    {
        model.clear();
        new ModelResolver(session.getAttribute("s-searcher"), model).resolve();
        return "redirect:" + url;
    }
    
    @GetMapping(value = "/modify.jsp")
    public String modify(String id, ModelMap model)
    {
        Slideshow data = slideshowService.get(id);
        
        model.addAttribute("data", data);
        return "slideshow/slideshow_form";
    }
    
    @PostMapping(value = "/modify.jsp")
    public String modify(Slideshow data, ModelMap model, HttpSession session)
    {
        slideshowService.modify(data);
        return redirectList(model, session, "/slideshow/list.jsp");
    }
    
    @RequestMapping("/delete.jsp")
    public String delete(Slideshow data, ModelMap model, HttpSession session)
    {
        slideshowService.delete(data);
        return redirectList(model, session, "/slideshow/list.jsp");
    }
}