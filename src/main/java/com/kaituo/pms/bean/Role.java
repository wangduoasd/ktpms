package com.kaituo.pms.bean;

public class Role {
    private Integer roleId;

    private String roleName;

    private String roleAffiliation;

    private Boolean roleStatus;

    public Role(Integer roleId, String roleName, String roleAffiliation, Boolean roleStatus) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleAffiliation = roleAffiliation;
        this.roleStatus = roleStatus;
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role() {
        super();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleAffiliation() {
        return roleAffiliation;
    }

    public void setRoleAffiliation(String roleAffiliation) {
        this.roleAffiliation = roleAffiliation == null ? null : roleAffiliation.trim();
    }

    public Boolean getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(Boolean roleStatus) {
        this.roleStatus = roleStatus;
    }
}