package com.todaysoft.ghealth.mybatis.model;

import com.todaysoft.ghealth.DTO.Questionnaire;

/**
 * @Author: zyf
 * @Date: 2018/10/26 9:05
 */

public class SlideshowQuestionnaire
{
    private Slideshow slideshow;
    
    private Questionnaire questionnaire;
    
    public Slideshow getSlideshow()
    {
        return slideshow;
    }
    
    public void setSlideshow(Slideshow slideshow)
    {
        this.slideshow = slideshow;
    }
    
    public Questionnaire getQuestionnaire()
    {
        return questionnaire;
    }
    
    public void setQuestionnaire(Questionnaire questionnaire)
    {
        this.questionnaire = questionnaire;
    }
}
