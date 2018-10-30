package com.todaysoft.ghealth.request;

import com.hsgene.restful.request.QueryRequest;

public class ProductQueryRequest extends QueryRequest
{
    private String code;
    
    private String name;
    
    private String discount;
    
    private Boolean enabledMatch;

    private String questionnaireIds;
    
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
    
    public String getDiscount()
    {
        return discount;
    }
    
    public void setDiscount(String discount)
    {
        this.discount = discount;
    }
    
    public Boolean getEnabledMatch()
    {
        return enabledMatch;
    }
    
    public void setEnabledMatch(Boolean enabledMatch)
    {
        this.enabledMatch = enabledMatch;
    }

    public String getQuestionnaireIds() {
        return questionnaireIds;
    }

    public void setQuestionnaireIds(String questionnaireIds) {
        this.questionnaireIds = questionnaireIds;
    }
}
