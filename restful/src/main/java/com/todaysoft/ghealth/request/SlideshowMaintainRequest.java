package com.todaysoft.ghealth.request;

import com.todaysoft.ghealth.DTO.QuestionnaireSurveyDTO;

/**
 * @Author: zyf
 * @Date: 2018/10/23 14:35
 */

public class SlideshowMaintainRequest extends MaintainRequest
{
    private String id;
    
    private String name;
    
    private QuestionnaireSurveyDTO questionnaireSurveyDTO;
    
    private String pictureUrl;
    
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
    
    public QuestionnaireSurveyDTO getQuestionnaireSurveyDTO()
    {
        return questionnaireSurveyDTO;
    }
    
    public void setQuestionnaireSurveyDTO(QuestionnaireSurveyDTO questionnaireSurveyDTO)
    {
        this.questionnaireSurveyDTO = questionnaireSurveyDTO;
    }
    
    public String getPictureUrl()
    {
        return pictureUrl;
    }
    
    public void setPictureUrl(String pictureUrl)
    {
        this.pictureUrl = pictureUrl;
    }
}
