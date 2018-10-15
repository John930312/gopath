package com.todaysoft.ghealth.mvc;

import com.todaysoft.ghealth.model.User;
import com.todaysoft.ghealth.model.searcher.UserSearcher;
import com.todaysoft.ghealth.service.IUserService;
import com.todaysoft.ghealth.support.Pager;
import com.todaysoft.ghealth.support.PagerArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
