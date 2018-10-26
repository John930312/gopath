package com.todaysoft.ghealth.service;

import com.todaysoft.ghealth.mybatis.model.SlideshowQuestionnaire;

import java.util.List;

/**
 * @Author: zyf
 * @Date: 2018/10/26 9:10
 */

public interface ISlideshowQuestionnaireService
{
    void createSlideshowQuestionnaires(List<SlideshowQuestionnaire> questionnaires);

    void deleteBySlideshowId(String id);
}
