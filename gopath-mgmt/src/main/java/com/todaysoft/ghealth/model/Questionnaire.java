package com.todaysoft.ghealth.model;

public class Questionnaire
{
    private String id;
    
    private String name;
    
    private Integer category;
    
    private Boolean status;
    
    private String remark;
    
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
    
    public Integer getCategory()
    {
        return category;
    }
    
    public void setCategory(Integer category)
    {
        this.category = category;
    }
    
    public Boolean getStatus()
    {
        return status;
    }
    
    public void setStatus(Boolean status)
    {
        this.status = status;
    }
    
    public String getRemark()
    {
        return remark;
    }
    
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}