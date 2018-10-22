package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.Questionnaire;
import com.todaysoft.ghealth.gateway.Gateway;
import com.todaysoft.ghealth.request.QuestionnaireQueryRequest;
import com.todaysoft.ghealth.service.IQuestionnaireService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ljl
 * @Date: 2018/10/19 0019 17:00
 */
@Service
public class QuestionnaireService implements IQuestionnaireService
{

    @Autowired
    private Gateway gateway;

    @Override
    public List<Questionnaire> list()
    {
        QuestionnaireQueryRequest request = new QuestionnaireQueryRequest();
        request.setCount(false);
        DataResponse<List<Questionnaire>> response = gateway.post("/questionnaire/list", request, new ParameterizedTypeReference<DataResponse<List<Questionnaire>>>()
        {
        });
        return response.getData();
    }
}
