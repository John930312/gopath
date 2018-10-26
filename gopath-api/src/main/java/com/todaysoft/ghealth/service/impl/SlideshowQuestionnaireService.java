package com.todaysoft.ghealth.service.impl;

import com.todaysoft.ghealth.mybatis.mapper.SlideshowQuestionnaireMapper;
import com.todaysoft.ghealth.mybatis.model.SlideshowQuestionnaire;
import com.todaysoft.ghealth.service.ISlideshowQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: zyf
 * @Date: 2018/10/26 9:23
 */

@Service
public class SlideshowQuestionnaireService implements ISlideshowQuestionnaireService
{
    @Autowired
    private SlideshowQuestionnaireMapper slideshowQuestionnaireMapper;

    @Override
    @Transactional
    public void createSlideshowQuestionnaires(List<SlideshowQuestionnaire> questionnaires)
    {
        slideshowQuestionnaireMapper.insertList(questionnaires);
    }

    @Override
    @Transactional
    public void deleteBySlideshowId(String id)
    {
        slideshowQuestionnaireMapper.deleteBySlideshowId(id);
    }

}
