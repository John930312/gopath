package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.DTO.SlideshowDTO;
import com.todaysoft.ghealth.mybatis.model.Slideshow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: zyf
 * @Date: 2018/10/23 15:44
 */

@Component
public class SlideshowWrapper extends Wrapper<Slideshow, SlideshowDTO>
{
    @Autowired
    private QuestionnaireSurveyWrapper questionnaireSurveyWrapper;
    
    @Override
    protected String[] getCopyIgnoreProperties()
    {
        return new String[] {"createTime", "questionnaireSurvey"};
    }
    
    @Override
    protected void setCopyIgnoreProperties(Slideshow source, SlideshowDTO target)
    {
        target.setCreateTime(formatDate(source.getCreateTime()));
        target.setQuestionnaireSurvey(questionnaireSurveyWrapper.wrap(source.getQuestionnaireSurvey()));
    }
}
