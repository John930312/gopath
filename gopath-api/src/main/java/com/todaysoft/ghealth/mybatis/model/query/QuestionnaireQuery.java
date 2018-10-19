package com.todaysoft.ghealth.mybatis.model.query;

/**
 * @Author: ljl
 * @Date: 2018/10/18 0018 11:03
 */
public class QuestionnaireQuery extends Query {
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
