package com.kaituo.pms.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = -1672970955045193907L;
    private int num;
    private Integer userId;
    private String userName;
    private Integer dept_id;
    private  String deptName;
    private Integer userDeptPosition;
    private  String positionName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date userInductiontime;
    private Integer userIntegral;
    private Integer userStatus;
    private String userPassword;
    private List<String> roles;
//enheng



    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date getRoleTime;

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
    public User(Integer num,Integer userId, String userName, Integer userIntegral, String deptName, String positionName, Integer userStatus, Date userInductiontime,String userPassword) {
        this.num=num;
        this.userId = userId;
        this.userName = userName;
        this.userIntegral = userIntegral;
        this.deptName = deptName;
        this.positionName = positionName;
        this.userStatus = userStatus;
        this.userInductiontime = userInductiontime;
        this.userPassword=userPassword;
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
/*权限管理下拉列表*/

    public User(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
//权限用户列表
    public User(Integer userId, String userName, String deptName, String positionName, Date getRoleTime) {
        this.userId = userId;
        this.userName = userName;
        this.deptName = deptName;
        this.positionName = positionName;
        this.getRoleTime = getRoleTime;
    }
//员工设置
    public User(Integer userId, String userName, Integer dept_id, String deptName, Integer userDeptPosition, String positionName, Date userInductiontime, Integer userIntegral, Integer userStatus, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.dept_id = dept_id;
        this.deptName = deptName;
        this.userDeptPosition = userDeptPosition;
        this.positionName = positionName;
        this.userInductiontime = userInductiontime;
        this.userIntegral = userIntegral;
        this.userStatus = userStatus;
        this.userPassword = userPassword;
    }

    public User(int num, Integer userId, String userName, String userPassword, Integer userIntegral, Integer dept_id, String deptName, Integer userDeptPosition, String positionName, List<String> roles, Integer userStatus, Date userInductiontime, Date getRoleTime) {
        this.num = num;
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userIntegral = userIntegral;
        this.dept_id = dept_id;
        this.deptName = deptName;
        this.userDeptPosition = userDeptPosition;
        this.positionName = positionName;
        this.roles = roles;
        this.userStatus = userStatus;
        this.userInductiontime = userInductiontime;
        this.getRoleTime = getRoleTime;
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



    public void setUserInductiontime(Date userInductiontime) {
        this.userInductiontime = userInductiontime;
    }

    public Date getGetRoleTime() {
        return getRoleTime;
    }

    public void setGetRoleTime(Date getRoleTime) {
        this.getRoleTime = getRoleTime;
    }




    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}