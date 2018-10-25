package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.DTO.SlideshowDTO;
import com.todaysoft.ghealth.model.Slideshow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: zyf
 * @Date: 2018/10/23 14:52
 */

@Component
public class SlideshowWrapper extends Wrapper<SlideshowDTO, Slideshow>
{
    @Autowired
    private QuestionnaireSurveyWrapper questionnaireSurveyWrapper;

    @Override
    protected String[] getCopyIgnoreProperties()
    {
        return new String[] {"createTime", "questionnaireSurvey"};
    }
    
    @Override
    protected void setCopyIgnoreProperties(SlideshowDTO source, Slideshow target)
    {
        target.setCreateTime(parseDate(source.getCreateTime()));
        target.setQuestionnaireSurvey(questionnaireSurveyWrapper.wrap(source.getQuestionnaireSurvey()));
    }
}
