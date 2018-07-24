package com.kaituo.pms.domain;

import java.io.Serializable;

public class DeptPosition implements Serializable {
    private Integer deptId;

    private Integer positionId;

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