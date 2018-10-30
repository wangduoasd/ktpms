package com.kaituo.pms.bean;

import java.io.Serializable;
import java.util.Date;

public class Attendance implements Serializable {
    private Integer id;

    private String jobnumber;

    private String name;

    private String deptname;

    private Integer isovertime;

    private Date starttimeot;

    private Date endtimeot;

    private Integer iswholenight;

    private String wholenightdate;

    private Integer deductintegral;

    private String datatime;

    private String attendancedata;


    private static final long serialVersionUID = 1L;

    public Attendance(Integer id, String jobnumber, String name, String deptname, Integer isovertime, Date starttimeot, Date endtimeot, Integer iswholenight, String wholenightdate, Integer deductintegral, String datatime, String attendancedata) {
        this.id = id;
        this.jobnumber = jobnumber;
        this.name = name;
        this.deptname = deptname;
        this.isovertime = isovertime;
        this.starttimeot = starttimeot;
        this.endtimeot = endtimeot;
        this.iswholenight = iswholenight;
        this.wholenightdate = wholenightdate;
        this.deductintegral = deductintegral;
        this.datatime = datatime;
        this.attendancedata = attendancedata;
    }

    public Attendance() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber == null ? null : jobnumber.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

    public Integer getIsovertime() {
        return isovertime;
    }

    public void setIsovertime(Integer isovertime) {
        this.isovertime = isovertime;
    }

    public Date getStarttimeot() {
        return starttimeot;
    }

    public void setStarttimeot(Date starttimeot) {
        this.starttimeot = starttimeot;
    }

    public Date getEndtimeot() {
        return endtimeot;
    }

    public void setEndtimeot(Date endtimeot) {
        this.endtimeot = endtimeot;
    }

    public Integer getIswholenight() {
        return iswholenight;
    }

    public void setIswholenight(Integer iswholenight) {
        this.iswholenight = iswholenight;
    }

    public String getWholenightdate() {
        return wholenightdate;
    }

    public void setWholenightdate(String wholenightdate) {
        this.wholenightdate = wholenightdate == null ? null : wholenightdate.trim();
    }

    public Integer getDeductintegral() {
        return deductintegral;
    }

    public void setDeductintegral(Integer deductintegral) {
        this.deductintegral = deductintegral;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime == null ? null : datatime.trim();
    }

    public String getAttendancedata() {
        return attendancedata;
    }

    public void setAttendancedata(String attendancedata) {
        this.attendancedata = attendancedata == null ? null : attendancedata.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Attendance other = (Attendance) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getJobnumber() == null ? other.getJobnumber() == null : this.getJobnumber().equals(other.getJobnumber()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getDeptname() == null ? other.getDeptname() == null : this.getDeptname().equals(other.getDeptname()))
            && (this.getIsovertime() == null ? other.getIsovertime() == null : this.getIsovertime().equals(other.getIsovertime()))
            && (this.getStarttimeot() == null ? other.getStarttimeot() == null : this.getStarttimeot().equals(other.getStarttimeot()))
            && (this.getEndtimeot() == null ? other.getEndtimeot() == null : this.getEndtimeot().equals(other.getEndtimeot()))
            && (this.getIswholenight() == null ? other.getIswholenight() == null : this.getIswholenight().equals(other.getIswholenight()))
            && (this.getWholenightdate() == null ? other.getWholenightdate() == null : this.getWholenightdate().equals(other.getWholenightdate()))
            && (this.getDeductintegral() == null ? other.getDeductintegral() == null : this.getDeductintegral().equals(other.getDeductintegral()))
            && (this.getDatatime() == null ? other.getDatatime() == null : this.getDatatime().equals(other.getDatatime()))
            && (this.getAttendancedata() == null ? other.getAttendancedata() == null : this.getAttendancedata().equals(other.getAttendancedata()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getJobnumber() == null) ? 0 : getJobnumber().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDeptname() == null) ? 0 : getDeptname().hashCode());
        result = prime * result + ((getIsovertime() == null) ? 0 : getIsovertime().hashCode());
        result = prime * result + ((getStarttimeot() == null) ? 0 : getStarttimeot().hashCode());
        result = prime * result + ((getEndtimeot() == null) ? 0 : getEndtimeot().hashCode());
        result = prime * result + ((getIswholenight() == null) ? 0 : getIswholenight().hashCode());
        result = prime * result + ((getWholenightdate() == null) ? 0 : getWholenightdate().hashCode());
        result = prime * result + ((getDeductintegral() == null) ? 0 : getDeductintegral().hashCode());
        result = prime * result + ((getDatatime() == null) ? 0 : getDatatime().hashCode());
        result = prime * result + ((getAttendancedata() == null) ? 0 : getAttendancedata().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", jobnumber='" + jobnumber + '\'' +
                ", name='" + name + '\'' +
                ", deptname='" + deptname + '\'' +
                ", isovertime=" + isovertime +
                ", starttimeot=" + starttimeot +
                ", endtimeot=" + endtimeot +
                ", iswholenight=" + iswholenight +
                ", wholenightdate='" + wholenightdate + '\'' +
                ", deductintegral=" + deductintegral +
                ", datatime='" + datatime + '\'' +
                ", attendancedata='" + attendancedata + '\'' +
                '}';
    }
}