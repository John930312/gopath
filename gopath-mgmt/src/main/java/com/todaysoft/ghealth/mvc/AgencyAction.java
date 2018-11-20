package com.todaysoft.ghealth.mvc;

import com.todaysoft.ghealth.DTO.AreaDTO;
import com.todaysoft.ghealth.form.FormInputView;
import com.todaysoft.ghealth.form.FormSubmitHandler;
import com.todaysoft.ghealth.model.Agency;
import com.todaysoft.ghealth.model.searcher.AgencySearcher;
import com.todaysoft.ghealth.service.IAgencyService;
import com.todaysoft.ghealth.service.IAreaService;
import com.todaysoft.ghealth.support.ModelResolver;
import com.todaysoft.ghealth.support.Pager;
import com.todaysoft.ghealth.support.PagerArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @Author: zyf
 * @Date: 2018/10/17 9:03
 */

@Controller
@RequestMapping("/agency")
public class AgencyAction
{
    @Autowired
    private IAgencyService agencyService;
    
    @Autowired
    private IAreaService areaService;
    
    @RequestMapping(value = "/list.jsp", produces = "text/html;charset=UTF-8")
    public String pager(AgencySearcher searcher, PagerArgs pageArgs, ModelMap model, HttpSession session)
    {
        Pager<Agency> pager = agencyService.pager(searcher, pageArgs.getPageNo(), pageArgs.getPageSize());
        model.addAttribute("searcher", searcher);
        model.addAttribute("pagination", pager);
        session.setAttribute("s-searcher", searcher);
        return "agency/agency_list";
    }
    
    @RequestMapping(value = "/detail.jsp", produces = "text/html;charset=UTF-8")
    public String getDetail(String id, ModelMap model)
    {
        Agency data = agencyService.get(id);
        
        model.addAttribute("data", data);
        return "agency/agency_detail";
    }
    
    @RequestMapping(value = "/create.jsp", method = RequestMethod.GET)
    @FormInputView
    public String create(ModelMap model)
    {
        model.addAttribute("list", areaService.findProvince());
        return "agency/agency_form";
    }
    
    @RequestMapping(value = "/create.jsp", method = RequestMethod.POST)
    @FormSubmitHandler
    public String create(Agency data, ModelMap model, HttpSession session)
    {
        agencyService.create(data);
        return redirectList(model, session, "/agency/list.jsp");
    }
    
    @GetMapping(value = "/modify.jsp")
    public String modify(String id, ModelMap model)
    {
        Agency data = agencyService.get(id);
        
        if (!StringUtils.isEmpty(data.getProvince()))
        {
            List<AreaDTO> province = areaService.findByParentId(data.getProvince());
            model.addAttribute("province", province);
        }
        model.addAttribute("data", data);
        model.addAttribute("list", areaService.findProvince());
        return "agency/agency_form";
    }
    
    @PostMapping(value = "/modify.jsp")
    public String modify(Agency data, ModelMap model, HttpSession session)
    {
        agencyService.modify(data);
        return redirectList(model, session, "/agency/list.jsp");
    }
    
    @RequestMapping("/delete.jsp")
    public String delete(Agency data, ModelMap model, HttpSession session)
    {
        agencyService.delete(data);
        return redirectList(model, session, "/agency/list.jsp");
    }
    
    @RequestMapping("/getAreas")
    @ResponseBody
    public List<AreaDTO> findByParentId(ModelMap model, String parentId)
    {
        return areaService.findByParentId(parentId);
    }
    
    @RequestMapping("/validate.do")
    @ResponseBody
    public boolean validate(String code, String id)
    {
        return agencyService.isCodeUnique(code, id);
    }

    @RequestMapping("json_list.do")
    @ResponseBody
    public List<Agency> jsonList(AgencySearcher searcher)
    {
        Pager<Agency> pagination = agencyService.pager(searcher, 1, 10);
        if (CollectionUtils.isEmpty(pagination.getRecords()))
        {
            return Collections.emptyList();
        }

        List<Agency> agencyList = pagination.getRecords();
        return agencyList;
    }
    
    private String redirectList(ModelMap model, HttpSession session, String url)
    {
        model.clear();
        new ModelResolver(session.getAttribute("s-searcher"), model).resolve();
        return "redirect:" + url;
    }
}
