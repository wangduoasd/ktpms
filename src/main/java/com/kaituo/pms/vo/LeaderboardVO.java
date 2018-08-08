package com.kaituo.pms.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @program: ktpms
 * @description: 积分排行榜分页展示数据
 * @author: su
 * @create: 2018-08-08 11:35
 **/

public class LeaderboardVO {
    /**排名*/
    private int num;
    /**
     *员工名称
     */
    private String userName;
    /**
     *部门名称
     */
    private String deptName;
    /**
     *职位名称
     */
    private String positionName;
    /**
     *员工id
     */
    private int userid;
    /**
     *入职时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userInductionTime;
    /**
     *员工积分
     */
    private int userIntegral;
    /**
     *员工状态
     */
    private int userStatus;

    public LeaderboardVO() {
    }

    public LeaderboardVO(int num, String userName, String deptName, String positionName, int userid,
                       Date userInductionTime, int userIntegral, int userStatus) {
        this.num = num;
        this.userName = userName;
        this.deptName = deptName;
        this.positionName = positionName;
        this.userid = userid;
        this.userInductionTime = userInductionTime;
        this.userIntegral = userIntegral;
        this.userStatus = userStatus;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getUserInductionTime() {
        return userInductionTime;
    }

    public void setUserInductionTime(Date userInductionTime) {
        this.userInductionTime = userInductionTime;
    }

    public int getUserIntegral() {
        return userIntegral;
    }

    public void setUserIntegral(int userIntegral) {
        this.userIntegral = userIntegral;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "Leaderboard{" +
                "num=" + num +
                ", userName='" + userName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", positionName='" + positionName + '\'' +
                ", userid=" + userid +
                ", userInductionTime=" + userInductionTime +
                ", userIntegral=" + userIntegral +
                ", userStatus=" + userStatus +
                '}';
    }
}
