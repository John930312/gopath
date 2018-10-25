package com.todaysoft.ghealth.request;

import com.hsgene.restful.request.QueryRequest;

/**
 * @Author: zyf
 * @Date: 2018/10/23 14:35
 */

public class SlideshowQueryRequest extends QueryRequest
{
    private String name;
    
    private String questionnaireSurveyName;
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getQuestionnaireSurveyName()
    {
        return questionnaireSurveyName;
    }
    
    public void setQuestionnaireSurveyName(String questionnaireSurveyName)
    {
        this.questionnaireSurveyName = questionnaireSurveyName;
    }
}
