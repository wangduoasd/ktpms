package com.kaituo.pms.domain;

import java.io.Serializable;

public class Position implements Serializable {
    private Integer positionId;

    private String positionName;

    private Boolean positionStatus;

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }

    public Boolean getPositionStatus() {
        return positionStatus;
    }

    public void setPositionStatus(Boolean positionStatus) {
        this.positionStatus = positionStatus;
    }
}