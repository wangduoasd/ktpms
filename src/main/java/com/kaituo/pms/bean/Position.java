package com.kaituo.pms.bean;

public class Position {
    private Integer deptPositionId;

    private String positionName;

    private Boolean positionStatus;

    private Integer deptId;

    public Position(Integer deptPositionId, String positionName, Boolean positionStatus, Integer deptId) {
        this.deptPositionId = deptPositionId;
        this.positionName = positionName;
        this.positionStatus = positionStatus;
        this.deptId = deptId;
    }

    public Position() {
        super();
    }

    public Integer getDeptPositionId() {
        return deptPositionId;
    }

    public void setDeptPositionId(Integer deptPositionId) {
        this.deptPositionId = deptPositionId;
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

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}