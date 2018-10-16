package com.todaysoft.ghealth.mvc;

import com.todaysoft.ghealth.form.FormInputView;
import com.todaysoft.ghealth.form.FormSubmitHandler;
import com.todaysoft.ghealth.model.User;
import com.todaysoft.ghealth.model.searcher.UserSearcher;
import com.todaysoft.ghealth.service.IUserService;
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

/**
 * @Author: xjw
 * @Date: 2018/10/15 16:01
 */
@Controller
@RequestMapping("/user")
public class UserAction
{
    @Autowired
    private IUserService userService;
    
    @RequestMapping(value = "/list.jsp", produces = "text/html;charset=UTF-8")
    public String pager(UserSearcher searcher, PagerArgs pageArgs, ModelMap model, HttpSession session)
    {
        Pager<User> pager = userService.pager(searcher, pageArgs.getPageNo(), pageArgs.getPageSize());
        model.addAttribute("searcher", searcher);
        model.addAttribute("pagination", pager);
        session.setAttribute("s-searcher", searcher);
        return "user/user_list";
    }

    @RequestMapping(value = "/create.jsp", method = RequestMethod.GET)
    @FormInputView
    public String create()
    {
        return "user/user_form";
    }

    @RequestMapping(value = "/create.jsp", method = RequestMethod.POST)
    @FormSubmitHandler
    public String create(User data, ModelMap model, HttpSession session)
    {
        userService.create(data);
        return redirectList(model, session, "/user/list.jsp");
    }

    @RequestMapping(value = "/modify.jsp", method = RequestMethod.GET)
    @FormInputView
    public String modify(String id, ModelMap model)
    {
        User data = userService.get(id);
        model.addAttribute("data", data);
        return "user/user_form";
    }

    @RequestMapping(value = "/modify.jsp", method = RequestMethod.POST)
    @FormSubmitHandler
    public String modify(User data, ModelMap model, HttpSession session)
    {
        userService.modify(data);
        return redirectList(model, session, "/user/list.jsp");
    }

    @RequestMapping("/delete.jsp")
    public String delete(User data, ModelMap model, HttpSession session)
    {
        userService.delete(data);
        return redirectList(model, session, "/user/list.jsp");
    }

    @RequestMapping("/change.do")
    @FormSubmitHandler
    public String change(User data, ModelMap model, HttpSession session)
    {
        userService.change(data);
        return redirectList(model, session, "/user/list.jsp");
    }

    @RequestMapping("/validate.do")
    @ResponseBody
    public boolean validate(String username,String id)
    {
        return userService.isUsernameUnique(username,id);
    }

    private String redirectList(ModelMap model, HttpSession session, String url)
    {
        model.clear();
        new ModelResolver(session.getAttribute("s-searcher"), model).resolve();
        return "redirect:" + url;
    }
}
