package com.todaysoft.ghealth.mybatis.model.query;

/**
 * @Author: ljl
 * @Date: 2018/8/28 0028 11:10
 */
public class UserRoleQuery extends Query
{
    private String roleId;

    private String userId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
