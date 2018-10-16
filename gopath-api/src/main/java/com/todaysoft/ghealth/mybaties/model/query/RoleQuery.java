package com.todaysoft.ghealth.mybaties.model.query;

import java.util.Set;

/**
 * @Author: ljl
 * @Date: 2018/8/28 0028 9:21
 */
public class RoleQuery extends Query
{
    private String name;

    private boolean nameExactMatches;

    private Set<String> excludeKeys;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNameExactMatches() {
        return nameExactMatches;
    }

    public void setNameExactMatches(boolean nameExactMatches) {
        this.nameExactMatches = nameExactMatches;
    }

    public Set<String> getExcludeKeys() {
        return excludeKeys;
    }

    public void setExcludeKeys(Set<String> excludeKeys) {
        this.excludeKeys = excludeKeys;
    }
}
