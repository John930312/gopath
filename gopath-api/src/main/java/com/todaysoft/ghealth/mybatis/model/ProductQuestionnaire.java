package com.todaysoft.ghealth.mybatis.model;

import com.todaysoft.ghealth.DTO.Questionnaire;

public class ProductQuestionnaire {
    private Product product;

    private Questionnaire questionnaire;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }
}