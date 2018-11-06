package com.todaysoft.ghealth.mybatis.mapper;

import com.todaysoft.ghealth.DTO.Questionnaire;
import com.todaysoft.ghealth.mybatis.model.SlideshowQuestionnaire;
import com.todaysoft.ghealth.mybatis.model.query.SlideshowQuestionnaireQuery;

import java.util.List;

public interface SlideshowQuestionnaireMapper
{
    int insertList(List<SlideshowQuestionnaire> questionnaires);
    
    void deleteBySlideshowId(String id);

    void deleteByQuestionnaireId(String id);
    
    List<SlideshowQuestionnaire> getSlideshowQuestionnaireBySlideshowId(String id);

    List<Questionnaire> getQuestionnaires(SlideshowQuestionnaireQuery searcher);
}
