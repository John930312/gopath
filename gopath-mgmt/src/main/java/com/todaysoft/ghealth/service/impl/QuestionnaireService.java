package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.gateway.Gateway;
import com.todaysoft.ghealth.DTO.Questionnaire;
import com.todaysoft.ghealth.model.searcher.QuestionnaireSearcher;
import com.todaysoft.ghealth.request.QuestionnaireMaintainRequest;
import com.todaysoft.ghealth.request.QuestionnaireQueryRequest;
import com.todaysoft.ghealth.service.IQuestionnaireService;
import com.todaysoft.ghealth.support.Pager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author: ljl
 * @Date: 2018/10/18 0018 10:40
 */
@Service
public class QuestionnaireService implements IQuestionnaireService
{
    @Autowired
    private Gateway gateway;

    @Override
    public Pager<Questionnaire> pager(QuestionnaireSearcher searcher, int pageNo, int pageSize)
    {
        QuestionnaireQueryRequest request = new QuestionnaireQueryRequest();
        BeanUtils.copyProperties(searcher, request);
        request.setCount(true);
        request.setLimit(pageSize);
        request.setOffset((pageNo - 1) * pageSize);
        DataResponse<CountRecords<Questionnaire>> response =
                gateway.post("/questionnaire/pager", request, new ParameterizedTypeReference<DataResponse<CountRecords<Questionnaire>>>()
                {
                });
        if (null == response.getData() || null == response.getData().getCount())
        {
            throw new IllegalStateException();
        }
        Pager<Questionnaire> pager = new Pager<>(pageNo, pageSize, response.getData().getCount().intValue());
        pager.setRecords(response.getData().getRecords());
        return pager;
    }

    @Override
    public void create(Questionnaire data)
    {
        gateway.post("/questionnaire/create", data);
    }

    @Override
    public Questionnaire get(String id)
    {
        DataResponse<Questionnaire> response = gateway.get("/questionnaire/get/{id}", new ParameterizedTypeReference<DataResponse<Questionnaire>>()
        {
        }, id);

        if (Objects.isNull(response.getData()))
        {
            return new Questionnaire();
        }

        return response.getData();
    }

    @Override
    public void modify(Questionnaire data)
    {
        gateway.post("/questionnaire/modify", data);
    }

    @Override
    public void delete(String id)
    {
        QuestionnaireMaintainRequest request = new QuestionnaireMaintainRequest();
        request.setId(id);
        request.setDeleted(true);
        gateway.post("/questionnaire/delete", request);
    }
}
