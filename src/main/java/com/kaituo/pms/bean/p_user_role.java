package com.kaituo.pms.bean;

public class p_user_role {
    private Integer userId;

    private Integer roleId;

    public p_user_role(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public p_user_role() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}