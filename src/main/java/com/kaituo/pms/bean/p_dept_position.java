package com.kaituo.pms.bean;

public class p_dept_position {
    private Integer deptPositionId;

    private Integer deptId;

    private Integer positionId;

    public p_dept_position(Integer deptPositionId, Integer deptId, Integer positionId) {
        this.deptPositionId = deptPositionId;
        this.deptId = deptId;
        this.positionId = positionId;
    }

    public p_dept_position() {
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