package com.todaysoft.ghealth.request;

import com.hsgene.restful.request.QueryRequest;

public class RoleQueryRequest extends QueryRequest
{
    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
