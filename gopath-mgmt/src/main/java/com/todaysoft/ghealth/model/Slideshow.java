package com.todaysoft.ghealth.model;

import com.todaysoft.ghealth.DTO.Questionnaire;

import java.util.Date;
import java.util.List;

/**
 * @Author: zyf
 * @Date: 2018/10/23 10:47
 */

public class Slideshow
{
    private String id;
    
    private String name;
    
    private String pictureUrl;
    
    private String questionnairePlatForm;
    
    private List<Questionnaire> questionnaires;
    
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
    
    public String getPictureUrl()
    {
        return pictureUrl;
    }
    
    public void setPictureUrl(String pictureUrl)
    {
        this.pictureUrl = pictureUrl;
    }
    
    public String getQuestionnairePlatForm()
    {
        return questionnairePlatForm;
    }
    
    public void setQuestionnairePlatForm(String questionnairePlatForm)
    {
        this.questionnairePlatForm = questionnairePlatForm;
    }
    
    public List<Questionnaire> getQuestionnaires()
    {
        return questionnaires;
    }
    
    public void setQuestionnaires(List<Questionnaire> questionnaires)
    {
        this.questionnaires = questionnaires;
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
