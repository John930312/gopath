package com.todaysoft.ghealth.mybatis.model;

/**
 * @Author: ljl
 * @Date: 2018/8/28 0028 11:28
 */
public class UserRole {
    private User user;

    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
