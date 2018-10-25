package com.todaysoft.ghealth.mybatis.model;

import java.util.Date;

/**
 * @Author: zyf
 * @Date: 2018/10/23 15:28
 */

public class Slideshow
{
    private String id;
    
    private String name;
    
    private QuestionnaireSurvey questionnaireSurvey;
    
    private String pictureUrl;
    
    private Date createTime;
    
    private Boolean deleted;
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public QuestionnaireSurvey getQuestionnaireSurvey()
    {
        return questionnaireSurvey;
    }
    
    public void setQuestionnaireSurvey(QuestionnaireSurvey questionnaireSurvey)
    {
        this.questionnaireSurvey = questionnaireSurvey;
    }
    
    public String getPictureUrl()
    {
        return pictureUrl;
    }
    
    public void setPictureUrl(String pictureUrl)
    {
        this.pictureUrl = pictureUrl;
    }
    
    public Date getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    
    public Boolean getDeleted()
    {
        return deleted;
    }
    
    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }
}
