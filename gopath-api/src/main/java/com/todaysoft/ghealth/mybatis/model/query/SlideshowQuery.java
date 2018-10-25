package com.todaysoft.ghealth.mybatis.model.query;

/**
 * @Author: zyf
 * @Date: 2018/10/23 15:28
 */

public class SlideshowQuery extends Query
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
