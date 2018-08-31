package com.kaituo.pms.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserRole {
    private Integer userId;

    private Integer roleId;

    private Date inductionTime;

    public UserRole(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public UserRole(Integer userId, Integer roleId, Date inductionTime) {
        this.userId = userId;
        this.roleId = roleId;
        this.inductionTime = inductionTime;
    }

    public Date getInductionTime() {
        return inductionTime;
    }

    public void setInductionTime(Date inductiontime) {
        this.inductionTime = inductiontime;
    }

    public UserRole() {
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