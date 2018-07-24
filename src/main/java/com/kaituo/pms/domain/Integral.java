package com.kaituo.pms.domain;

import java.io.Serializable;
import java.util.Date;

public class Integral implements Serializable {
    private Integer integralId;

    private Integer integralTotal;

    private Integer userId;

    private String integralChangestr;

    private Date integralTime;

    private Integer integralChangeint;

    private Integer integralStatus;

    private String integralOperator;

    public Integer getIntegralId() {
        return integralId;
    }

    public void setIntegralId(Integer integralId) {
        this.integralId = integralId;
    }

    public Integer getIntegralTotal() {
        return integralTotal;
    }

    public void setIntegralTotal(Integer integralTotal) {
        this.integralTotal = integralTotal;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIntegralChangestr() {
        return integralChangestr;
    }

    public void setIntegralChangestr(String integralChangestr) {
        this.integralChangestr = integralChangestr == null ? null : integralChangestr.trim();
    }

    public Date getIntegralTime() {
        return integralTime;
    }

    public void setIntegralTime(Date integralTime) {
        this.integralTime = integralTime;
    }

    public Integer getIntegralChangeint() {
        return integralChangeint;
    }

    public void setIntegralChangeint(Integer integralChangeint) {
        this.integralChangeint = integralChangeint;
    }

    public Integer getIntegralStatus() {
        return integralStatus;
    }

    public void setIntegralStatus(Integer integralStatus) {
        this.integralStatus = integralStatus;
    }

    public String getIntegralOperator() {
        return integralOperator;
    }

    public void setIntegralOperator(String integralOperator) {
        this.integralOperator = integralOperator == null ? null : integralOperator.trim();
    }
}