package com.todaysoft.ghealth.mybatis.mapper;

import com.todaysoft.ghealth.DTO.Questionnaire;
import com.todaysoft.ghealth.mybatis.model.query.QuestionnaireQuery;

import java.util.List;

public interface QuestionnaireMapper {
    int delete(String id);

    int create(Questionnaire record);

    Questionnaire get(String id);

    int modify(Questionnaire record);

    long count(QuestionnaireQuery query);

    List<Questionnaire> query(QuestionnaireQuery query);

    List<Questionnaire> getListBySlideshowId(String id);
}