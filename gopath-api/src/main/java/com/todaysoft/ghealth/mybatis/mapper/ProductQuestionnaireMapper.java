package com.todaysoft.ghealth.mybatis.mapper;

import com.todaysoft.ghealth.DTO.Questionnaire;
import com.todaysoft.ghealth.mybatis.model.ProductQuestionnaire;

import java.util.List;

public interface ProductQuestionnaireMapper
{
    int create(ProductQuestionnaire record);

    void deleteByProductId(String id);

    List<Questionnaire> getQuestionnairesByProductId(String id);

    void deleteByQuestionnaireId(String id);
}