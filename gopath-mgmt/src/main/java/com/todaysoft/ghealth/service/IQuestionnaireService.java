package com.todaysoft.ghealth.service;

import com.todaysoft.ghealth.DTO.Questionnaire;
import com.todaysoft.ghealth.model.searcher.QuestionnaireSearcher;
import com.todaysoft.ghealth.support.Pager;

/**
 * @Author: ljl
 * @Date: 2018/10/18 0018 10:40
 */
public interface IQuestionnaireService
{
    Pager<Questionnaire> pager(QuestionnaireSearcher searcher, int pageNo, int pageSize);

    void create(Questionnaire data);

    Questionnaire get(String id);

    void modify(Questionnaire data);

    void delete(String id);
}
