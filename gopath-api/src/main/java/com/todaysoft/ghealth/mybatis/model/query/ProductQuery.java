package com.todaysoft.ghealth.mybatis.model.query;

import java.util.Set;

public class ProductQuery extends Query
{
    private String code;
    
    private String name;
    
    private String discount;
    
    private boolean codeExactMatches;
    
    private Set<String> excludeKeys;
    
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public boolean isCodeExactMatches()
    {
        return codeExactMatches;
    }
    
    public void setCodeExactMatches(boolean codeExactMatches)
    {
        this.codeExactMatches = codeExactMatches;
    }
    
    public Set<String> getExcludeKeys()
    {
        return excludeKeys;
    }
    
    public void setExcludeKeys(Set<String> excludeKeys)
    {
        this.excludeKeys = excludeKeys;
    }
    
    public String getDiscount()
    {
        return discount;
    }
    
    public void setDiscount(String discount)
    {
        this.discount = discount;
    }
}
