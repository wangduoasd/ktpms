package com.kaituo.pms.bean;

public class p_permission {
    private Integer permissionId;

    private String permissionName;

    private String permissionResulf;

    private Integer roleId;

    public p_permission(Integer permissionId, String permissionName, String permissionResulf, Integer roleId) {
        this.permissionId = permissionId;
        this.permissionName = permissionName;
        this.permissionResulf = permissionResulf;
        this.roleId = roleId;
    }

    public p_permission() {
        super();
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getPermissionResulf() {
        return permissionResulf;
    }

    public void setPermissionResulf(String permissionResulf) {
        this.permissionResulf = permissionResulf == null ? null : permissionResulf.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}