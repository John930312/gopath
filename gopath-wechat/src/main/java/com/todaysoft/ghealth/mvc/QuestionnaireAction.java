package com.todaysoft.ghealth.mvc;

import com.todaysoft.ghealth.DTO.Questionnaire;
import com.todaysoft.ghealth.service.IQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

/**
 * @Author: ljl
 * @Date: 2018/10/19 0019 15:17
 */
@Controller
@RequestMapping("/questionnaire")
public class QuestionnaireAction
{
    @Autowired
    private IQuestionnaireService questionnaireService;

    @RequestMapping("/list.jsp")
    public String list(String id, ModelMap model, HttpSession session)
    {
        List<Questionnaire> questionnaires = questionnaireService.list(id);
        Map<Integer, List<Questionnaire>> questionnaireMap = questionnaires.stream().collect(groupingBy(Questionnaire::getCategory));
        model.addAttribute("tumors", questionnaireMap.containsKey(1) ? questionnaireMap.get(1): null);
        model.addAttribute("nonTumors",questionnaireMap.containsKey(2) ?questionnaireMap.get(2): null);
        model.addAttribute("dailys", questionnaireMap.containsKey(3) ? questionnaireMap.get(3) :null);
        model.addAttribute("baseTypes", questionnaireMap.containsKey(4) ? questionnaireMap.get(4) :null);
        return "questionnaire/questionnaire_list";
    }
}
