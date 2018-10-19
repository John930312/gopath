package com.todaysoft.ghealth.request;

import com.hsgene.restful.request.QueryRequest;

/**
 * @Author: ljl
 * @Date: 2018/10/18 0018 10:51
 */
public class QuestionnaireQueryRequest extends QueryRequest {
    private String name;

    private Integer category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}
