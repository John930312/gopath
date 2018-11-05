package com.todaysoft.ghealth.model.searcher;

/**
 * @Author: ljl
 * @Date: 2018/10/22 0022 13:44
 */
public class ProductSearcher {
    private String code;

    private String name;

    private String questionnaireIds;

    private String agencyId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestionnaireIds() {
        return questionnaireIds;
    }

    public void setQuestionnaireIds(String questionnaireIds) {
        this.questionnaireIds = questionnaireIds;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
}
