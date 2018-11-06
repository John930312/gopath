package com.todaysoft.ghealth.mvc;

import com.todaysoft.ghealth.DTO.Questionnaire;
import com.todaysoft.ghealth.form.FormInputView;
import com.todaysoft.ghealth.form.FormSubmitHandler;
import com.todaysoft.ghealth.model.searcher.QuestionnaireSearcher;
import com.todaysoft.ghealth.service.IQuestionnaireService;
import com.todaysoft.ghealth.support.ModelResolver;
import com.todaysoft.ghealth.support.Pager;
import com.todaysoft.ghealth.support.PagerArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * @Author: ljl
 * @Date: 2018/10/18 0018 10:08
 */
@Controller
@RequestMapping("/questionnaire")
public class QuestionnaireAction
{
    @Autowired
    private IQuestionnaireService questionnaireService;

    @RequestMapping("/list.jsp")
    public String paginations(QuestionnaireSearcher searcher, PagerArgs pagerArgs, ModelMap model, HttpSession session)
    {
        Pager<Questionnaire> pagination = questionnaireService.pager(searcher, pagerArgs.getPageNo(), pagerArgs.getPageSize());
        model.addAttribute("pagination", pagination);
        model.addAttribute("searcher", searcher);
        session.setAttribute("s-searcher", searcher);
        return "questionnaire/questionnaire_list";
    }

    @RequestMapping(value = "/create.jsp", method = RequestMethod.GET)
    @FormInputView
    public String create(ModelMap model)
    {
        return "questionnaire/questionnaire_form";
    }

    @RequestMapping(value = "/create.jsp", method = RequestMethod.POST)
    @FormSubmitHandler
    public String create(Questionnaire data, ModelMap model, HttpSession session)
    {
        questionnaireService.create(data);
        return redirectList(model, session, "/questionnaire/list.jsp");
    }

    @RequestMapping(value = "/modify.jsp", method = RequestMethod.GET)
    @FormInputView
    public String modify(String id, ModelMap model, HttpSession session)
    {
        Questionnaire data = questionnaireService.get(id);
        model.addAttribute("data", data);
        return "questionnaire/questionnaire_form";
    }

    @RequestMapping(value = "/modify.jsp", method = RequestMethod.POST)
    @FormSubmitHandler
    public String modify(Questionnaire data, ModelMap model, HttpSession session)
    {
        questionnaireService.modify(data);
        return redirectList(model, session, "/questionnaire/list.jsp");
    }

    @RequestMapping("/delete.jsp")
    public String delete(String id, ModelMap model, HttpSession session)
    {
        questionnaireService.delete(id);
        return redirectList(model, session, "/questionnaire/list.jsp");
    }

    @RequestMapping("json_list.do")
    @ResponseBody
    public List<Questionnaire> jsonList(QuestionnaireSearcher searcher)
    {
        Pager<Questionnaire> pagination = questionnaireService.pager(searcher, 1, 10);
        if (CollectionUtils.isEmpty(pagination.getRecords()))
        {
            return Collections.emptyList();
        }

        List<Questionnaire> questionnaireList = pagination.getRecords();
        return questionnaireList;
    }

    private String redirectList(ModelMap model, HttpSession session, String url)
    {
        model.clear();
        new ModelResolver(session.getAttribute("s-searcher"), model).resolve();
        return "redirect:" + url;
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
        return "redirect:/questionnaire/list.jsp";
    }
}
