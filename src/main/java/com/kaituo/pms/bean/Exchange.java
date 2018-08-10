package com.kaituo.pms.bean;

import java.util.Date;

public class Exchange {
    private Integer exchangeId;

    private Integer prizeId;

    private Integer userId;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private Integer exchangeCount;

    private Date exchangeTime;

    private Integer exchangeStatus;
    private String prizeImage;
    private String prizeName;

    public String getPrizeImage() {
        return prizeImage;
    }

    public void setPrizeImage(String prizeImage) {
        this.prizeImage = prizeImage;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }
    public Exchange(Integer exchangeId, Integer prizeId, Integer userId, Integer exchangeCount, Date exchangeTime, Integer exchangeStatus) {
        this.exchangeId = exchangeId;
        this.prizeId = prizeId;
        this.userId = userId;
        this.exchangeCount = exchangeCount;
        this.exchangeTime = exchangeTime;
        this.exchangeStatus = exchangeStatus;
    }
    public Exchange( Integer exchangeId, Integer userId,String userName, Integer exchangeCount, Date exchangeTime, Integer exchangeStatus, String prizeImage, String prizeName) {
        this.userId = userId;
        this.exchangeId = exchangeId;
        this.userName=userName;
        this.exchangeCount = exchangeCount;
        this.exchangeTime = exchangeTime;
        this.exchangeStatus = exchangeStatus;
        this.prizeImage = prizeImage;
        this.prizeName = prizeName;
    }
    //对应 兑换记录视图
    public Exchange( Integer exchangeId, Integer userId, Integer exchangeCount, Date exchangeTime, Integer exchangeStatus, String prizeImage, String prizeName) {
        this.userId = userId;
        this.exchangeId = exchangeId;
        this.exchangeCount = exchangeCount;
        this.exchangeTime = exchangeTime;
        this.exchangeStatus = exchangeStatus;
        this.prizeImage = prizeImage;
        this.prizeName = prizeName;
    }
    public Exchange() {
        super();
    }

    public Integer getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Integer exchangeId) {
        this.exchangeId = exchangeId;
    }

    public Integer getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Integer prizeId) {
        this.prizeId = prizeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getExchangeCount() {
        return exchangeCount;
    }

    public void setExchangeCount(Integer exchangeCount) {
        this.exchangeCount = exchangeCount;
    }

    public Date getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(Date exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public Integer getExchangeStatus() {
        return exchangeStatus;
    }

    public void setExchangeStatus(Integer exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }
}