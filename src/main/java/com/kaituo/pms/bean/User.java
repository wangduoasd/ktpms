package com.kaituo.pms.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = -1672970955045193907L;
    private int num;

    private Integer userId;

private String userName;

    private String userPassword;

    private Integer userIntegral;

    private Integer userDeptPosition;
    private  String deptName;
    private  String positionName;
    private String[] roles;


    private Integer userStatus;

    private Date userInductiontime;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public User(Integer userId, String userName, String userPassword, Integer userIntegral, Integer userDeptPosition, Integer userStatus, Date userInductiontime) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userIntegral = userIntegral;
        this.userDeptPosition = userDeptPosition;
        this.userStatus = userStatus;
        this.userInductiontime = userInductiontime;
    }

    public User(Integer num,Integer userId, String userName, Integer userIntegral, String deptName, String positionName, Integer userStatus, Date userInductiontime) {
        this.num=num;
        this.userId = userId;
        this.userName = userName;
        this.userIntegral = userIntegral;
        this.deptName = deptName;
        this.positionName = positionName;
        this.userStatus = userStatus;
        this.userInductiontime = userInductiontime;
    }
//添加员工
    public User(Integer userId, String userName, String userPassword, Integer userIntegral, String deptName, String positionName, Integer userStatus, Date userInductiontime) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userIntegral = userIntegral;
        this.deptName = deptName;
        this.positionName = positionName;
        this.userStatus = userStatus;
        this.userInductiontime = userInductiontime;
    }

    public User() {
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


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public void setUserInductiontime(Date userInductiontime) {
        this.userInductiontime = userInductiontime;
    }
}