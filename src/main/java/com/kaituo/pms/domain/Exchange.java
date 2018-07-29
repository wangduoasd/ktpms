package com.kaituo.pms.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Exchange implements Serializable {
    private Integer exchangeId;

    private Integer prizeId;

    private Integer userId;

    private Integer exchangeCount;

    private Integer exchangePrice;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @JsonFormat(pattern = "yyyy.MM.dd")
    private Date exchangeTime;

    private Integer exchangeStatus;

    @Override
    public String toString() {
        return "Exchange{" +
                "exchangeId=" + exchangeId +
                ", prizeId=" + prizeId +
                ", userId=" + userId +
                ", exchangeCount=" + exchangeCount +
                ", exchangePrice=" + exchangePrice +
                ", exchangeTime=" + exchangeTime +
                ", exchangeStatus=" + exchangeStatus +
                '}';
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

    public Integer getExchangeStatus() {
        return exchangeStatus;
    }

    public void setExchangeStatus(Integer exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }
}