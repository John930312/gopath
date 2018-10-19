package com.todaysoft.ghealth.service;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.Questionnaire;
import com.todaysoft.ghealth.request.QuestionnaireMaintainRequest;
import com.todaysoft.ghealth.request.QuestionnaireQueryRequest; /**
 * @Author: ljl
 * @Date: 2018/10/18 0018 11:00
 */
public interface IQuestionnaireService
{
    DataResponse<CountRecords<Questionnaire>> pager(QuestionnaireQueryRequest request);

    void create(Questionnaire request);

    DataResponse<Questionnaire> get(String id);

    void modify(Questionnaire request);

    void delete(QuestionnaireMaintainRequest request);
}
