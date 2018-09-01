package com.kaituo.pms.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Dept {
    private Integer deptId;

    private String deptName;

    private String deptDescribe;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deptInductiontime;

    private Integer deptStatus;
    private List<Position> positions;

    private List<String> positionArray;

    public List<String> getPositionArray() {
        return positionArray;
    }

    public void setPositionArray(List<String> positionArray) {
        this.positionArray = positionArray;
    }

    public Dept(Integer deptId, String deptName, String deptDescribe, Date deptInductiontime, Integer deptStatus) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.deptDescribe = deptDescribe;
        this.deptInductiontime = deptInductiontime;
        this.deptStatus = deptStatus;
    }

    public Dept(Integer deptId, String deptName, String deptDescribe, Date deptInductiontime, Integer deptStatus, List<String> positionArray) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.deptDescribe = deptDescribe;
        this.deptInductiontime = deptInductiontime;
        this.deptStatus = deptStatus;
        this.positionArray = positionArray;
    }

    public Dept(Integer deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public Dept(Integer deptId, String deptName, String deptDescribe, Date deptInductiontime, Integer deptStatus, List<Position> positions, List<String> positionArray) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.deptDescribe = deptDescribe;
        this.deptInductiontime = deptInductiontime;
        this.deptStatus = deptStatus;
        this.positions = positions;
        this.positionArray = positionArray;
    }

    public Dept() {
        super();
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getDeptDescribe() {
        return deptDescribe;
    }

    public void setDeptDescribe(String deptDescribe) {
        this.deptDescribe = deptDescribe == null ? null : deptDescribe.trim();
    }

    public Date getDeptInductiontime() {
        return deptInductiontime;
    }

    public void setDeptInductiontime(Date deptInductiontime) {
        this.deptInductiontime = deptInductiontime;
    }

    public Integer getDeptStatus() {
        return deptStatus;
    }

    public void setDeptStatus(Integer deptStatus) {
        this.deptStatus = deptStatus;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}