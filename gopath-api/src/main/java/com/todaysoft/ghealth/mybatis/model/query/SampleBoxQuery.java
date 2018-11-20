package com.todaysoft.ghealth.mybatis.model.query;

import org.springframework.stereotype.Component;

/**
 * @Author: ljl
 * @Date: 2018/11/20 0020 9:43
 */
@Component
public class SampleBoxQuery extends Query
{
    private String id;
    
    private String code;
    
    private String binded;
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public String getBinded()
    {
        return binded;
    }
    
    public void setBinded(String binded)
    {
        this.binded = binded;
    }
}
