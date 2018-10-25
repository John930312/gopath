package com.todaysoft.ghealth.model;

import java.util.Date;

/**
 * @Author: zyf
 * @Date: 2018/10/25 9:16
 */

public class QuestionnaireSurvey
{
    private String id;
    
    private String name;
    
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
