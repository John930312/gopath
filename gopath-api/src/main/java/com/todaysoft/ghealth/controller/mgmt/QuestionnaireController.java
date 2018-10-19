package com.todaysoft.ghealth.controller.mgmt;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.Questionnaire;
import com.todaysoft.ghealth.request.QuestionnaireMaintainRequest;
import com.todaysoft.ghealth.request.QuestionnaireQueryRequest;
import com.todaysoft.ghealth.service.IQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ljl
 * @Date: 2018/10/18 0018 10:57
 */
@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController
{
    @Autowired
    private IQuestionnaireService service;

    @RequestMapping("/pager")
    public DataResponse<CountRecords<Questionnaire>> pager(@RequestBody QuestionnaireQueryRequest request)
    {
        return service.pager(request);
    }

    @RequestMapping("/create")
    public void create(@RequestBody Questionnaire request)
    {
        service.create(request);
    }

    @RequestMapping("/get/{id}")
    public DataResponse<Questionnaire> get(@PathVariable String id)
    {
        return service.get(id);
    }

    @RequestMapping("/modify")
    public void modify(@RequestBody Questionnaire request)
    {
        service.modify(request);
    }

    @RequestMapping("/delete")
    public void delete(@RequestBody QuestionnaireMaintainRequest request)
    {
        service.delete(request);
    }
}
