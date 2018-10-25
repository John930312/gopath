package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.DTO.QuestionnaireSurveyDTO;
import com.todaysoft.ghealth.model.QuestionnaireSurvey;
import org.springframework.stereotype.Component;

/**
 * @Author: zyf
 * @Date: 2018/10/25 10:26
 */

@Component
public class QuestionnaireSurveyWrapper extends Wrapper<QuestionnaireSurveyDTO, QuestionnaireSurvey>
{
    @Override
    protected String[] getCopyIgnoreProperties()
    {
        return new String[] {"createTime", "slideshow"};
    }

    @Override
    protected void setCopyIgnoreProperties(QuestionnaireSurveyDTO source, QuestionnaireSurvey target)
    {
        target.setCreateTime(parseDate(source.getCreateTime()));
    }
}