package com.todaysoft.ghealth.controller.wechat;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.Questionnaire;
import com.todaysoft.ghealth.request.QuestionnaireQueryRequest;
import com.todaysoft.ghealth.service.IQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: ljl
 * @Date: 2018/10/22 0022 13:52
 */
@RestController
@RequestMapping("/wechat/questionnaire")
public class WeChatQuestionnaireController
{
    @Autowired
    private IQuestionnaireService service;

    @RequestMapping("/list/{id}")
    public DataResponse<List<Questionnaire>> list(@PathVariable String id)
    {
        return service.list(id);
    }
}
