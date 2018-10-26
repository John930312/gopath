package com.todaysoft.ghealth.request;

/**
 * @Author: zyf
 * @Date: 2018/10/23 14:35
 */

public class SlideshowMaintainRequest extends MaintainRequest
{
    private String id;
    
    private String name;
    
    private String questionnairePlatForm;
    
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
}
