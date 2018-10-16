package com.todaysoft.ghealth.request;

import com.hsgene.restful.request.QueryRequest;

/**
 * @Author: ljl
 * @Date: 2018/8/28 0028 17:29
 */
public class DictQueryRequest extends QueryRequest
{
    private String dictText;
    
    private String dictValue;
    
    private String category;
    
    public String getDictText()
    {
        return dictText;
    }
    
    public void setDictText(String dictText)
    {
        this.dictText = dictText;
    }
    
    public String getDictValue()
    {
        return dictValue;
    }
    
    public void setDictValue(String dictValue)
    {
        this.dictValue = dictValue;
    }
    
    public String getCategory()
    {
        return category;
    }
    
    public void setCategory(String category)
    {
        this.category = category;
    }
}
