package com.todaysoft.ghealth.mybatis.model.query;

/**
 * @Author: zyf
 * @Date: 2018/10/26 9:03
 */

public class SlideshowQuestionnaireQuery extends Query
{
    private String slideshowId;
    
    private String questionnaireId;
    
    public String getSlideshowId()
    {
        return slideshowId;
    }
    
    public void setSlideshowId(String slideshowId)
    {
        this.slideshowId = slideshowId;
    }
    
    public String getQuestionnaireId()
    {
        return questionnaireId;
    }
    
    public void setQuestionnaireId(String questionnaireId)
    {
        this.questionnaireId = questionnaireId;
    }
}
