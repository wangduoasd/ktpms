package com.kaituo.pms.bean;

public class p_position {
    private Integer positionId;

    private String positionName;

    private Boolean positionStatus;

    public p_position(Integer positionId, String positionName, Boolean positionStatus) {
        this.positionId = positionId;
        this.positionName = positionName;
        this.positionStatus = positionStatus;
    }

    public p_position() {
        super();
    }

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