package com.kaituo.pms.bean;

public class DeptPosition {
    private Integer deptPositionId;

    private Integer deptId;

    private Integer positionId;

    public DeptPosition(Integer deptPositionId, Integer deptId, Integer positionId) {
        this.deptPositionId = deptPositionId;
        this.deptId = deptId;
        this.positionId = positionId;
    }

    public DeptPosition() {
        super();
    }

    public Integer getDeptPositionId() {
        return deptPositionId;
    }

    public void setDeptPositionId(Integer deptPositionId) {
        this.deptPositionId = deptPositionId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }
}