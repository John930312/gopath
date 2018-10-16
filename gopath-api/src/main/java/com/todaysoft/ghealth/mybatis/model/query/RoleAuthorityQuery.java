package com.todaysoft.ghealth.mybatis.model.query;

/**
 * @Author: ljl
 * @Date: 2018/8/28 0028 11:06
 */
public class RoleAuthorityQuery extends Query
{
    private String roleId;

    private String authorityId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }
}
