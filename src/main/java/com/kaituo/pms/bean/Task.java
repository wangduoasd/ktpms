package com.kaituo.pms.bean;

import java.util.Date;

public class Task {
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

    private Date taskGettime;

    public Task(Integer taskId, String taskName, Integer taskDifficulty, Integer taskPrice, Integer taskAward, Integer taskNumber, Integer taskTime, Date taskStarttime, Date taskEndtime, String taskImage, String taskDescribe, Integer taskStatus, Integer userId, String userName, Date taskGettime) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDifficulty = taskDifficulty;
        this.taskPrice = taskPrice;
        this.taskAward = taskAward;
        this.taskNumber = taskNumber;
        this.taskTime = taskTime;
        this.taskStarttime = taskStarttime;
        this.taskEndtime = taskEndtime;
        this.taskImage = taskImage;
        this.taskDescribe = taskDescribe;
        this.taskStatus = taskStatus;
        this.userId = userId;
        this.userName = userName;
        this.taskGettime = taskGettime;
    }

    public Task() {
        super();
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

    public Date getTaskGettime() {
        return taskGettime;
    }

    public void setTaskGettime(Date taskGettime) {
        this.taskGettime = taskGettime;
    }
}