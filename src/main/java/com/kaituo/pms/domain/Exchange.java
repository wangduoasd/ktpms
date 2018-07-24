package com.kaituo.pms.domain;

import java.io.Serializable;
import java.util.Date;

public class Exchange implements Serializable {
    private Integer exchangeId;

    private Integer prizeId;

    private Integer userId;

    private Integer exchangeCount;

    private Integer exchangePrice;

    private Date exchangeTime;

    private Boolean exchangeStatus;

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

    public Integer getExchangePrice() {
        return exchangePrice;
    }

    public void setExchangePrice(Integer exchangePrice) {
        this.exchangePrice = exchangePrice;
    }

    public Date getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(Date exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public Boolean getExchangeStatus() {
        return exchangeStatus;
    }

    public void setExchangeStatus(Boolean exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }
}