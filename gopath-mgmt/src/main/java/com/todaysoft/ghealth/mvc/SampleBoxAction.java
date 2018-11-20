package com.todaysoft.ghealth.mvc;

import com.todaysoft.ghealth.form.FormInputView;
import com.todaysoft.ghealth.form.FormSubmitHandler;
import com.todaysoft.ghealth.model.SampleBox;
import com.todaysoft.ghealth.model.searcher.SampleBoxSearcher;
import com.todaysoft.ghealth.service.ISampleBoxService;
import com.todaysoft.ghealth.support.ModelResolver;
import com.todaysoft.ghealth.support.Pager;
import com.todaysoft.ghealth.support.PagerArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Author: ljl
 * @Date: 2018/11/20 0020 9:31
 */
@Controller
@RequestMapping("/sampleBox")
public class SampleBoxAction
{
    @Autowired
    private ISampleBoxService sampleBoxService;

    @RequestMapping("/list.jsp")
    public String paginations(SampleBoxSearcher searcher, PagerArgs pagerArgs, ModelMap model, HttpSession session)
    {
        Pager<SampleBox> pagination = sampleBoxService.pager(searcher, pagerArgs.getPageNo(), pagerArgs.getPageSize());
        model.addAttribute("pagination", pagination);
        model.addAttribute("searcher", searcher);
        session.setAttribute("s-searcher", searcher);
        return "sampleBox/sampleBox_list";
    }

    @RequestMapping(value = "/create.jsp", method = RequestMethod.GET)
    @FormInputView
    public String create()
    {
        return "sampleBox/sampleBox_form";
    }

    @RequestMapping(value = "/create.jsp", method = RequestMethod.POST)
    @FormSubmitHandler
    public String create(SampleBox data, ModelMap model, HttpSession session)
    {
        data.setType(1);
        sampleBoxService.create(data);
        return redirectList(model, session, "/sampleBox/list.jsp");
    }

    @ResponseBody
    @GetMapping("/validateSampleBoxCode.do")
    public Boolean isUniqueSampleBoxCode(String code)
    {
        return sampleBoxService.isUniqueSampleBoxCode(code);
    }

    private String redirectList(ModelMap model, HttpSession session, String url)
    {
        model.clear();
        new ModelResolver(session.getAttribute("s-searcher"), model).resolve();
        return "redirect:" + url;
    }
}
