package com.todaysoft.ghealth.mvc;

import com.alibaba.fastjson.JSON;
import com.todaysoft.ghealth.DTO.AuthorityNode;
import com.todaysoft.ghealth.form.FormInputView;
import com.todaysoft.ghealth.form.FormSubmitHandler;
import com.todaysoft.ghealth.model.Role;
import com.todaysoft.ghealth.model.searcher.RoleSearcher;
import com.todaysoft.ghealth.service.IRoleService;
import com.todaysoft.ghealth.service.IUserService;
import com.todaysoft.ghealth.support.ModelResolver;
import com.todaysoft.ghealth.support.Pager;
import com.todaysoft.ghealth.support.PagerArgs;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;;

@Controller
@RequestMapping("/role")
public class RoleAction
{
    @Autowired
    private IRoleService roleService;
    
    @Autowired
    private IUserService userService;
    
    private String url = "/role/list.jsp";
    
    @RequestMapping("/list.jsp")
    public String paginations(RoleSearcher searcher, PagerArgs pagerArgs, ModelMap model, HttpSession session)
    {
        Pager<Role> pagination = roleService.pager(searcher, pagerArgs.getPageNo(), pagerArgs.getPageSize());
        model.addAttribute("pagination", pagination);
        model.addAttribute("searcher", searcher);
        session.setAttribute("s-searcher", searcher);
        return "role/role_list";
    }
    
    @RequestMapping(value = "/create.jsp", method = RequestMethod.GET)
    @FormInputView
    public String create(Role data, ModelMap model)
    {
        if (StringUtils.isNotEmpty(data.getId()))
        {
            model.addAttribute("data", roleService.get(data));
        }
        List<AuthorityNode> authorityNodes = roleService.getAuthorityNodes();
        String jsonArray = JSON.toJSONString(authorityNodes);
        model.addAttribute("nodes", jsonArray);
        return "role/role_form";
    }

    @RequestMapping(value = "/modify.jsp", method = RequestMethod.GET)
    @FormInputView
    public String modify(Role data, ModelMap model)
    {
        if (StringUtils.isNotEmpty(data.getId()))
        {
            model.addAttribute("data", roleService.get(data));
        }
        List<AuthorityNode> authorityNodes = roleService.getAuthorityNodes();
        String jsonArray = JSON.toJSONString(authorityNodes);
        model.addAttribute("nodes", jsonArray);
        return "role/role_form";
    }

    @RequestMapping(value = "/create.jsp", method = RequestMethod.POST)
    @FormSubmitHandler
    public String create(Role data, ModelMap model, HttpSession session)
    {
        roleService.create(data);
        return redirectList(model, session, url);
    }
    
    @RequestMapping(value = "/modify.jsp", method = RequestMethod.POST)
    @FormSubmitHandler
    public String modify(Role data, ModelMap model, HttpSession session)
    {
        roleService.modify(data);
        return redirectList(model, session, url);
    }

    @RequestMapping("/delete.jsp")
    @ResponseBody
    public boolean delete(String id, ModelMap model, HttpSession session)
    {
        return roleService.delete(id);
    }

    @RequestMapping("json_list.do")
    @ResponseBody
    public List<Role> jsonList(RoleSearcher searcher)
    {
        Pager<Role> pagination = roleService.pager(searcher, 1, 10);
        if (CollectionUtils.isEmpty(pagination.getRecords()))
        {
            return Collections.emptyList();
        }
        
        List<Role> roleList = pagination.getRecords();
        return roleList;
    }

    @RequestMapping("/isNameUnique.do")
    @ResponseBody
    public boolean isNameUnique(String id,String name)
    {
        return roleService.isNameUnique(id,name);
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
        return "redirect:/role/list.jsp";
    }

    private String redirectList(ModelMap model, HttpSession session, String url)
    {
        model.clear();
        new ModelResolver(session.getAttribute("s-searcher"), model).resolve();
        return "redirect:" + url;
    }
}
