package com.kaituo.pms.bean;

import java.util.Date;

public class p_exchange {
    private Integer exchangeId;

    private Integer prizeId;

    private Integer userId;

    private Integer exchangeCount;

    private Date exchangeTime;

    private Integer exchangeStatus;

    public p_exchange(Integer exchangeId, Integer prizeId, Integer userId, Integer exchangeCount, Date exchangeTime, Integer exchangeStatus) {
        this.exchangeId = exchangeId;
        this.prizeId = prizeId;
        this.userId = userId;
        this.exchangeCount = exchangeCount;
        this.exchangeTime = exchangeTime;
        this.exchangeStatus = exchangeStatus;
    }

    public p_exchange() {
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