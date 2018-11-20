package com.todaysoft.ghealth.model.searcher;

import lombok.Data;

/**
 * @Author: ljl
 * @Date: 2018/11/20 0020 9:32
 */
public class SampleBoxSearcher
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

    public String getBinded() {
        return binded;
    }

    public void setBinded(String binded) {
        this.binded = binded;
    }
}
