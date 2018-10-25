package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.DTO.QuestionnaireSurveyDTO;
import com.todaysoft.ghealth.mybatis.model.QuestionnaireSurvey;
import org.springframework.stereotype.Component;

/**
 * @Author: zyf
 * @Date: 2018/10/25 10:18
 */

@Component
public class QuestionnaireSurveyWrapper extends Wrapper<QuestionnaireSurvey, QuestionnaireSurveyDTO>
{
    @Override
    public String[] getCopyIgnoreProperties()
    {
        return new String[] {"createTime"};
    }
    
    @Override
    public void setCopyIgnoreProperties(QuestionnaireSurvey source, QuestionnaireSurveyDTO target)
    {
        target.setCreateTime(formatDate(source.getCreateTime()));
    }
}
