package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.DTO.Questionnaire;
import com.todaysoft.ghealth.DTO.SlideshowDTO;
import com.todaysoft.ghealth.mybatis.model.Slideshow;
import com.todaysoft.ghealth.mybatis.model.query.SlideshowQuestionnaireQuery;
import com.todaysoft.ghealth.service.impl.QuestionnaireService;
import com.todaysoft.ghealth.service.impl.SlideshowQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: zyf
 * @Date: 2018/10/23 15:44
 */

@Component
public class SlideshowWrapper extends Wrapper<Slideshow, SlideshowDTO>
{
    @Autowired
    private QuestionnaireService questionnaireService;
    
    @Override
    protected String[] getCopyIgnoreProperties()
    {
        return new String[] {"createTime", "questionnaires"};
    }
    
    @Override
    protected void setCopyIgnoreProperties(Slideshow source, SlideshowDTO target)
    {
        target.setCreateTime(formatDate(source.getCreateTime()));
        
        SlideshowQuestionnaireQuery slideshowQuestionnaireQuery = new SlideshowQuestionnaireQuery();
        slideshowQuestionnaireQuery.setSlideshowId(source.getId());
        List<Questionnaire> questionnaires = questionnaireService.getQuestionnaires(slideshowQuestionnaireQuery);
        target.setQuestionnaires(questionnaires);
    }
}
