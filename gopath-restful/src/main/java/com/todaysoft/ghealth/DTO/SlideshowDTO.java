package com.todaysoft.ghealth.DTO;

import java.util.Date;
import java.util.List;

/**
 * @Author: zyf
 * @Date: 2018/10/23 14:47
 */

public class SlideshowDTO
{
    private String id;
    
    private String name;
    
    private String pictureUrl;
    
    private List<Questionnaire> questionnaires;
    
    private String createTime;
    
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
    
    public List<Questionnaire> getQuestionnaires()
    {
        return questionnaires;
    }
    
    public void setQuestionnaires(List<Questionnaire> questionnaires)
    {
        this.questionnaires = questionnaires;
    }
    
    public String getCreateTime()
    {
        return createTime;
    }
    
    public void setCreateTime(String createTime)
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
