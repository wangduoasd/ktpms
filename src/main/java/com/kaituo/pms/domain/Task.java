package com.kaituo.pms.domain;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    private Integer taskId;

    private String taskName;

    private Integer taskDifficulty;

    private Integer taskPrice;

    private Integer taskAward;

    private Integer taskNumber;

    private Integer taskTime;

    private Date taskStarttime;

    private Date taskEndtime;

    private String taskImage;

    private String taskDescribe;

    private Integer taskStatus;

    private Integer userId;

    private String userName;

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskDifficulty=" + taskDifficulty +
                ", taskPrice=" + taskPrice +
                ", taskAward=" + taskAward +
                ", taskNumber=" + taskNumber +
                ", taskTime=" + taskTime +
                ", taskStarttime=" + taskStarttime +
                ", taskEndtime=" + taskEndtime +
                ", taskImage='" + taskImage + '\'' +
                ", taskDescribe='" + taskDescribe + '\'' +
                ", taskStatus=" + taskStatus +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public Integer getTaskDifficulty() {
        return taskDifficulty;
    }

    public void setTaskDifficulty(Integer taskDifficulty) {
        this.taskDifficulty = taskDifficulty;
    }

    public Integer getTaskPrice() {
        return taskPrice;
    }

    public void setTaskPrice(Integer taskPrice) {
        this.taskPrice = taskPrice;
    }

    public Integer getTaskAward() {
        return taskAward;
    }

    public void setTaskAward(Integer taskAward) {
        this.taskAward = taskAward;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    public Integer getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Integer taskTime) {
        this.taskTime = taskTime;
    }

    public Date getTaskStarttime() {
        return taskStarttime;
    }

    public void setTaskStarttime(Date taskStarttime) {
        this.taskStarttime = taskStarttime;
    }

    public Date getTaskEndtime() {
        return taskEndtime;
    }

    public void setTaskEndtime(Date taskEndtime) {
        this.taskEndtime = taskEndtime;
    }

    public String getTaskImage() {
        return taskImage;
    }

    public void setTaskImage(String taskImage) {
        this.taskImage = taskImage == null ? null : taskImage.trim();
    }

    public String getTaskDescribe() {
        return taskDescribe;
    }

    public void setTaskDescribe(String taskDescribe) {
        this.taskDescribe = taskDescribe == null ? null : taskDescribe.trim();
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
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
}