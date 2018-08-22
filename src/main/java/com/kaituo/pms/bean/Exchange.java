package com.kaituo.pms.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Exchange {
    private Integer exchangeId;

    private Integer prizeId;

    private Integer userId;
    private String userName;

    private Integer exchangeCount;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date exchangeTime;

    private Integer exchangeStatus;

    private String prizeName;

    private String prizeImage;

    public Exchange(Integer exchangeId, Integer prizeId, Integer userId, Integer exchangeCount, Date exchangeTime, Integer exchangeStatus, String prizeName, String prizeImage) {
        this.exchangeId = exchangeId;
        this.prizeId = prizeId;
        this.userId = userId;
        this.exchangeCount = exchangeCount;
        this.exchangeTime = exchangeTime;
        this.exchangeStatus = exchangeStatus;
        this.prizeName = prizeName;
        this.prizeImage = prizeImage;
    }

    public Exchange(Integer exchangeId, Integer prizeId, Integer userId, String userName, Integer exchangeCount, Date exchangeTime, Integer exchangeStatus, String prizeName, String prizeImage) {
        this.exchangeId = exchangeId;
        this.prizeId = prizeId;
        this.userId = userId;
        this.userName = userName;
        this.exchangeCount = exchangeCount;
        this.exchangeTime = exchangeTime;
        this.exchangeStatus = exchangeStatus;
        this.prizeName = prizeName;
        this.prizeImage = prizeImage;
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

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName == null ? null : prizeName.trim();
    }

    public String getPrizeImage() {
        return prizeImage;
    }

    public void setPrizeImage(String prizeImage) {
        this.prizeImage = prizeImage == null ? null : prizeImage.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}