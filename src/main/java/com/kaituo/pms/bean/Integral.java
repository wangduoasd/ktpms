package com.kaituo.pms.bean;

import java.util.Date;

public class Integral {
    private Integer integralId;

    private Integer userId;

    private Integer integralStartnum;

    private String integralChangestr;

    private Date integralTime;

    private Integer integralChangeint;

    private Integer integralOperator;

    private Integer integralEndnum;

    public Integral(Integer integralId, Integer userId, Integer integralStartnum, String integralChangestr, Date integralTime, Integer integralChangeint, Integer integralOperator, Integer integralEndnum) {
        this.integralId = integralId;
        this.userId = userId;
        this.integralStartnum = integralStartnum;
        this.integralChangestr = integralChangestr;
        this.integralTime = integralTime;
        this.integralChangeint = integralChangeint;
        this.integralOperator = integralOperator;
        this.integralEndnum = integralEndnum;
    }

    public Integral() {
        super();
    }

    public Integer getIntegralId() {
        return integralId;
    }

    public void setIntegralId(Integer integralId) {
        this.integralId = integralId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIntegralStartnum() {
        return integralStartnum;
    }

    public void setIntegralStartnum(Integer integralStartnum) {
        this.integralStartnum = integralStartnum;
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

    public Integer getIntegralOperator() {
        return integralOperator;
    }

    public void setIntegralOperator(Integer integralOperator) {
        this.integralOperator = integralOperator;
    }

    public Integer getIntegralEndnum() {
        return integralEndnum;
    }

    public void setIntegralEndnum(Integer integralEndnum) {
        this.integralEndnum = integralEndnum;
    }
}