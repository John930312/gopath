package com.todaysoft.ghealth.request;

/**
 * @Author: ljl
 * @Date: 2018/10/18 0018 16:41
 */
public class QuestionnaireMaintainRequest extends MaintainRequest
{
    private String id;

    private String name;

    private Integer category;

    private Boolean deleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
