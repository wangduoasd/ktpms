package com.kaituo.pms.bean;

import java.util.Date;

public class p_user {
    private Integer userId;

    private String userName;

    private String userPassword;

    private Integer userIntegral;

    private Integer userDeptPosition;

    private Integer userStatus;

    private Date userInductiontime;

    public p_user(Integer userId, String userName, String userPassword, Integer userIntegral, Integer userDeptPosition, Integer userStatus, Date userInductiontime) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userIntegral = userIntegral;
        this.userDeptPosition = userDeptPosition;
        this.userStatus = userStatus;
        this.userInductiontime = userInductiontime;
    }

    public p_user() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public Integer getUserIntegral() {
        return userIntegral;
    }

    public void setUserIntegral(Integer userIntegral) {
        this.userIntegral = userIntegral;
    }

    public Integer getUserDeptPosition() {
        return userDeptPosition;
    }

    public void setUserDeptPosition(Integer userDeptPosition) {
        this.userDeptPosition = userDeptPosition;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Date getUserInductiontime() {
        return userInductiontime;
    }

    public void setUserInductiontime(Date userInductiontime) {
        this.userInductiontime = userInductiontime;
    }
}