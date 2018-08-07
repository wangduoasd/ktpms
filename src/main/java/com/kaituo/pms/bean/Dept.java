package com.kaituo.pms.bean;

import java.util.Date;

public class Dept {
    private Integer deptId;

    private String deptName;

    private String deptDescribe;

    private Date deptInductiontime;

    private Integer deptStatus;

    public Dept(Integer deptId, String deptName, String deptDescribe, Date deptInductiontime, Integer deptStatus) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.deptDescribe = deptDescribe;
        this.deptInductiontime = deptInductiontime;
        this.deptStatus = deptStatus;
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
}