package com.todaysoft.ghealth.mybatis.model;

import com.todaysoft.ghealth.DTO.Authority;

/**
 * @Author: ljl
 * @Date: 2018/8/28 0028 11:17
 */
public class RoleAuthority
{
    private Role role;

    private Authority authority;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
