package com.todaysoft.ghealth.mybatis.model.query;

/**
 * @Author: ljl
 * @Date: 2018/8/29 0029 9:17
 */
public class DictQuery extends Query
{
    private String dictText;
    
    private String category;
    
    private String dictValue;
    
    public String getDictText()
    {
        return dictText;
    }
    
    public void setDictText(String dictText)
    {
        this.dictText = dictText;
    }
    
    public String getCategory()
    {
        return category;
    }
    
    public void setCategory(String category)
    {
        this.category = category;
    }
    
    public String getDictValue()
    {
        return dictValue;
    }
    
    public void setDictValue(String dictValue)
    {
        this.dictValue = dictValue;
    }
}
