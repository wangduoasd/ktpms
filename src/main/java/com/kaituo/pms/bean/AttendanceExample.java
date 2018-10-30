package com.kaituo.pms.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AttendanceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AttendanceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andJobnumberIsNull() {
            addCriterion("jobnumber is null");
            return (Criteria) this;
        }

        public Criteria andJobnumberIsNotNull() {
            addCriterion("jobnumber is not null");
            return (Criteria) this;
        }

        public Criteria andJobnumberEqualTo(String value) {
            addCriterion("jobnumber =", value, "jobnumber");
            return (Criteria) this;
        }

        public Criteria andJobnumberNotEqualTo(String value) {
            addCriterion("jobnumber <>", value, "jobnumber");
            return (Criteria) this;
        }

        public Criteria andJobnumberGreaterThan(String value) {
            addCriterion("jobnumber >", value, "jobnumber");
            return (Criteria) this;
        }

        public Criteria andJobnumberGreaterThanOrEqualTo(String value) {
            addCriterion("jobnumber >=", value, "jobnumber");
            return (Criteria) this;
        }

        public Criteria andJobnumberLessThan(String value) {
            addCriterion("jobnumber <", value, "jobnumber");
            return (Criteria) this;
        }

        public Criteria andJobnumberLessThanOrEqualTo(String value) {
            addCriterion("jobnumber <=", value, "jobnumber");
            return (Criteria) this;
        }

        public Criteria andJobnumberLike(String value) {
            addCriterion("jobnumber like", value, "jobnumber");
            return (Criteria) this;
        }

        public Criteria andJobnumberNotLike(String value) {
            addCriterion("jobnumber not like", value, "jobnumber");
            return (Criteria) this;
        }

        public Criteria andJobnumberIn(List<String> values) {
            addCriterion("jobnumber in", values, "jobnumber");
            return (Criteria) this;
        }

        public Criteria andJobnumberNotIn(List<String> values) {
            addCriterion("jobnumber not in", values, "jobnumber");
            return (Criteria) this;
        }

        public Criteria andJobnumberBetween(String value1, String value2) {
            addCriterion("jobnumber between", value1, value2, "jobnumber");
            return (Criteria) this;
        }

        public Criteria andJobnumberNotBetween(String value1, String value2) {
            addCriterion("jobnumber not between", value1, value2, "jobnumber");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDeptnameIsNull() {
            addCriterion("deptname is null");
            return (Criteria) this;
        }

        public Criteria andDeptnameIsNotNull() {
            addCriterion("deptname is not null");
            return (Criteria) this;
        }

        public Criteria andDeptnameEqualTo(String value) {
            addCriterion("deptname =", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameNotEqualTo(String value) {
            addCriterion("deptname <>", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameGreaterThan(String value) {
            addCriterion("deptname >", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameGreaterThanOrEqualTo(String value) {
            addCriterion("deptname >=", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameLessThan(String value) {
            addCriterion("deptname <", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameLessThanOrEqualTo(String value) {
            addCriterion("deptname <=", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameLike(String value) {
            addCriterion("deptname like", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameNotLike(String value) {
            addCriterion("deptname not like", value, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameIn(List<String> values) {
            addCriterion("deptname in", values, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameNotIn(List<String> values) {
            addCriterion("deptname not in", values, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameBetween(String value1, String value2) {
            addCriterion("deptname between", value1, value2, "deptname");
            return (Criteria) this;
        }

        public Criteria andDeptnameNotBetween(String value1, String value2) {
            addCriterion("deptname not between", value1, value2, "deptname");
            return (Criteria) this;
        }

        public Criteria andIsovertimeIsNull() {
            addCriterion("isovertime is null");
            return (Criteria) this;
        }

        public Criteria andIsovertimeIsNotNull() {
            addCriterion("isovertime is not null");
            return (Criteria) this;
        }

        public Criteria andIsovertimeEqualTo(Integer value) {
            addCriterion("isovertime =", value, "isovertime");
            return (Criteria) this;
        }

        public Criteria andIsovertimeNotEqualTo(Integer value) {
            addCriterion("isovertime <>", value, "isovertime");
            return (Criteria) this;
        }

        public Criteria andIsovertimeGreaterThan(Integer value) {
            addCriterion("isovertime >", value, "isovertime");
            return (Criteria) this;
        }

        public Criteria andIsovertimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("isovertime >=", value, "isovertime");
            return (Criteria) this;
        }

        public Criteria andIsovertimeLessThan(Integer value) {
            addCriterion("isovertime <", value, "isovertime");
            return (Criteria) this;
        }

        public Criteria andIsovertimeLessThanOrEqualTo(Integer value) {
            addCriterion("isovertime <=", value, "isovertime");
            return (Criteria) this;
        }

        public Criteria andIsovertimeIn(List<Integer> values) {
            addCriterion("isovertime in", values, "isovertime");
            return (Criteria) this;
        }

        public Criteria andIsovertimeNotIn(List<Integer> values) {
            addCriterion("isovertime not in", values, "isovertime");
            return (Criteria) this;
        }

        public Criteria andIsovertimeBetween(Integer value1, Integer value2) {
            addCriterion("isovertime between", value1, value2, "isovertime");
            return (Criteria) this;
        }

        public Criteria andIsovertimeNotBetween(Integer value1, Integer value2) {
            addCriterion("isovertime not between", value1, value2, "isovertime");
            return (Criteria) this;
        }

        public Criteria andStarttimeotIsNull() {
            addCriterion("startTimeOT is null");
            return (Criteria) this;
        }

        public Criteria andStarttimeotIsNotNull() {
            addCriterion("startTimeOT is not null");
            return (Criteria) this;
        }

        public Criteria andStarttimeotEqualTo(Date value) {
            addCriterion("startTimeOT =", value, "starttimeot");
            return (Criteria) this;
        }

        public Criteria andStarttimeotNotEqualTo(Date value) {
            addCriterion("startTimeOT <>", value, "starttimeot");
            return (Criteria) this;
        }

        public Criteria andStarttimeotGreaterThan(Date value) {
            addCriterion("startTimeOT >", value, "starttimeot");
            return (Criteria) this;
        }

        public Criteria andStarttimeotGreaterThanOrEqualTo(Date value) {
            addCriterion("startTimeOT >=", value, "starttimeot");
            return (Criteria) this;
        }

        public Criteria andStarttimeotLessThan(Date value) {
            addCriterion("startTimeOT <", value, "starttimeot");
            return (Criteria) this;
        }

        public Criteria andStarttimeotLessThanOrEqualTo(Date value) {
            addCriterion("startTimeOT <=", value, "starttimeot");
            return (Criteria) this;
        }

        public Criteria andStarttimeotIn(List<Date> values) {
            addCriterion("startTimeOT in", values, "starttimeot");
            return (Criteria) this;
        }

        public Criteria andStarttimeotNotIn(List<Date> values) {
            addCriterion("startTimeOT not in", values, "starttimeot");
            return (Criteria) this;
        }

        public Criteria andStarttimeotBetween(Date value1, Date value2) {
            addCriterion("startTimeOT between", value1, value2, "starttimeot");
            return (Criteria) this;
        }

        public Criteria andStarttimeotNotBetween(Date value1, Date value2) {
            addCriterion("startTimeOT not between", value1, value2, "starttimeot");
            return (Criteria) this;
        }

        public Criteria andEndtimeotIsNull() {
            addCriterion("endTimeOT is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeotIsNotNull() {
            addCriterion("endTimeOT is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeotEqualTo(Date value) {
            addCriterion("endTimeOT =", value, "endtimeot");
            return (Criteria) this;
        }

        public Criteria andEndtimeotNotEqualTo(Date value) {
            addCriterion("endTimeOT <>", value, "endtimeot");
            return (Criteria) this;
        }

        public Criteria andEndtimeotGreaterThan(Date value) {
            addCriterion("endTimeOT >", value, "endtimeot");
            return (Criteria) this;
        }

        public Criteria andEndtimeotGreaterThanOrEqualTo(Date value) {
            addCriterion("endTimeOT >=", value, "endtimeot");
            return (Criteria) this;
        }

        public Criteria andEndtimeotLessThan(Date value) {
            addCriterion("endTimeOT <", value, "endtimeot");
            return (Criteria) this;
        }

        public Criteria andEndtimeotLessThanOrEqualTo(Date value) {
            addCriterion("endTimeOT <=", value, "endtimeot");
            return (Criteria) this;
        }

        public Criteria andEndtimeotIn(List<Date> values) {
            addCriterion("endTimeOT in", values, "endtimeot");
            return (Criteria) this;
        }

        public Criteria andEndtimeotNotIn(List<Date> values) {
            addCriterion("endTimeOT not in", values, "endtimeot");
            return (Criteria) this;
        }

        public Criteria andEndtimeotBetween(Date value1, Date value2) {
            addCriterion("endTimeOT between", value1, value2, "endtimeot");
            return (Criteria) this;
        }

        public Criteria andEndtimeotNotBetween(Date value1, Date value2) {
            addCriterion("endTimeOT not between", value1, value2, "endtimeot");
            return (Criteria) this;
        }

        public Criteria andIswholenightIsNull() {
            addCriterion("isWholeNight is null");
            return (Criteria) this;
        }

        public Criteria andIswholenightIsNotNull() {
            addCriterion("isWholeNight is not null");
            return (Criteria) this;
        }

        public Criteria andIswholenightEqualTo(Integer value) {
            addCriterion("isWholeNight =", value, "iswholenight");
            return (Criteria) this;
        }

        public Criteria andIswholenightNotEqualTo(Integer value) {
            addCriterion("isWholeNight <>", value, "iswholenight");
            return (Criteria) this;
        }

        public Criteria andIswholenightGreaterThan(Integer value) {
            addCriterion("isWholeNight >", value, "iswholenight");
            return (Criteria) this;
        }

        public Criteria andIswholenightGreaterThanOrEqualTo(Integer value) {
            addCriterion("isWholeNight >=", value, "iswholenight");
            return (Criteria) this;
        }

        public Criteria andIswholenightLessThan(Integer value) {
            addCriterion("isWholeNight <", value, "iswholenight");
            return (Criteria) this;
        }

        public Criteria andIswholenightLessThanOrEqualTo(Integer value) {
            addCriterion("isWholeNight <=", value, "iswholenight");
            return (Criteria) this;
        }

        public Criteria andIswholenightIn(List<Integer> values) {
            addCriterion("isWholeNight in", values, "iswholenight");
            return (Criteria) this;
        }

        public Criteria andIswholenightNotIn(List<Integer> values) {
            addCriterion("isWholeNight not in", values, "iswholenight");
            return (Criteria) this;
        }

        public Criteria andIswholenightBetween(Integer value1, Integer value2) {
            addCriterion("isWholeNight between", value1, value2, "iswholenight");
            return (Criteria) this;
        }

        public Criteria andIswholenightNotBetween(Integer value1, Integer value2) {
            addCriterion("isWholeNight not between", value1, value2, "iswholenight");
            return (Criteria) this;
        }

        public Criteria andWholenightdateIsNull() {
            addCriterion("wholeNightDate is null");
            return (Criteria) this;
        }

        public Criteria andWholenightdateIsNotNull() {
            addCriterion("wholeNightDate is not null");
            return (Criteria) this;
        }

        public Criteria andWholenightdateEqualTo(String value) {
            addCriterion("wholeNightDate =", value, "wholenightdate");
            return (Criteria) this;
        }

        public Criteria andWholenightdateNotEqualTo(String value) {
            addCriterion("wholeNightDate <>", value, "wholenightdate");
            return (Criteria) this;
        }

        public Criteria andWholenightdateGreaterThan(String value) {
            addCriterion("wholeNightDate >", value, "wholenightdate");
            return (Criteria) this;
        }

        public Criteria andWholenightdateGreaterThanOrEqualTo(String value) {
            addCriterion("wholeNightDate >=", value, "wholenightdate");
            return (Criteria) this;
        }

        public Criteria andWholenightdateLessThan(String value) {
            addCriterion("wholeNightDate <", value, "wholenightdate");
            return (Criteria) this;
        }

        public Criteria andWholenightdateLessThanOrEqualTo(String value) {
            addCriterion("wholeNightDate <=", value, "wholenightdate");
            return (Criteria) this;
        }

        public Criteria andWholenightdateLike(String value) {
            addCriterion("wholeNightDate like", value, "wholenightdate");
            return (Criteria) this;
        }

        public Criteria andWholenightdateNotLike(String value) {
            addCriterion("wholeNightDate not like", value, "wholenightdate");
            return (Criteria) this;
        }

        public Criteria andWholenightdateIn(List<String> values) {
            addCriterion("wholeNightDate in", values, "wholenightdate");
            return (Criteria) this;
        }

        public Criteria andWholenightdateNotIn(List<String> values) {
            addCriterion("wholeNightDate not in", values, "wholenightdate");
            return (Criteria) this;
        }

        public Criteria andWholenightdateBetween(String value1, String value2) {
            addCriterion("wholeNightDate between", value1, value2, "wholenightdate");
            return (Criteria) this;
        }

        public Criteria andWholenightdateNotBetween(String value1, String value2) {
            addCriterion("wholeNightDate not between", value1, value2, "wholenightdate");
            return (Criteria) this;
        }

        public Criteria andDeductintegralIsNull() {
            addCriterion("deductIntegral is null");
            return (Criteria) this;
        }

        public Criteria andDeductintegralIsNotNull() {
            addCriterion("deductIntegral is not null");
            return (Criteria) this;
        }

        public Criteria andDeductintegralEqualTo(Integer value) {
            addCriterion("deductIntegral =", value, "deductintegral");
            return (Criteria) this;
        }

        public Criteria andDeductintegralNotEqualTo(Integer value) {
            addCriterion("deductIntegral <>", value, "deductintegral");
            return (Criteria) this;
        }

        public Criteria andDeductintegralGreaterThan(Integer value) {
            addCriterion("deductIntegral >", value, "deductintegral");
            return (Criteria) this;
        }

        public Criteria andDeductintegralGreaterThanOrEqualTo(Integer value) {
            addCriterion("deductIntegral >=", value, "deductintegral");
            return (Criteria) this;
        }

        public Criteria andDeductintegralLessThan(Integer value) {
            addCriterion("deductIntegral <", value, "deductintegral");
            return (Criteria) this;
        }

        public Criteria andDeductintegralLessThanOrEqualTo(Integer value) {
            addCriterion("deductIntegral <=", value, "deductintegral");
            return (Criteria) this;
        }

        public Criteria andDeductintegralIn(List<Integer> values) {
            addCriterion("deductIntegral in", values, "deductintegral");
            return (Criteria) this;
        }

        public Criteria andDeductintegralNotIn(List<Integer> values) {
            addCriterion("deductIntegral not in", values, "deductintegral");
            return (Criteria) this;
        }

        public Criteria andDeductintegralBetween(Integer value1, Integer value2) {
            addCriterion("deductIntegral between", value1, value2, "deductintegral");
            return (Criteria) this;
        }

        public Criteria andDeductintegralNotBetween(Integer value1, Integer value2) {
            addCriterion("deductIntegral not between", value1, value2, "deductintegral");
            return (Criteria) this;
        }

        public Criteria andDatatimeIsNull() {
            addCriterion("dataTime is null");
            return (Criteria) this;
        }

        public Criteria andDatatimeIsNotNull() {
            addCriterion("dataTime is not null");
            return (Criteria) this;
        }

        public Criteria andDatatimeEqualTo(String value) {
            addCriterion("dataTime =", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeNotEqualTo(String value) {
            addCriterion("dataTime <>", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeGreaterThan(String value) {
            addCriterion("dataTime >", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeGreaterThanOrEqualTo(String value) {
            addCriterion("dataTime >=", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeLessThan(String value) {
            addCriterion("dataTime <", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeLessThanOrEqualTo(String value) {
            addCriterion("dataTime <=", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeLike(String value) {
            addCriterion("dataTime like", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeNotLike(String value) {
            addCriterion("dataTime not like", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeIn(List<String> values) {
            addCriterion("dataTime in", values, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeNotIn(List<String> values) {
            addCriterion("dataTime not in", values, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeBetween(String value1, String value2) {
            addCriterion("dataTime between", value1, value2, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeNotBetween(String value1, String value2) {
            addCriterion("dataTime not between", value1, value2, "datatime");
            return (Criteria) this;
        }

        public Criteria andJobnumberLikeInsensitive(String value) {
            addCriterion("upper(jobnumber) like", value.toUpperCase(), "jobnumber");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andDeptnameLikeInsensitive(String value) {
            addCriterion("upper(deptname) like", value.toUpperCase(), "deptname");
            return (Criteria) this;
        }

        public Criteria andWholenightdateLikeInsensitive(String value) {
            addCriterion("upper(wholeNightDate) like", value.toUpperCase(), "wholenightdate");
            return (Criteria) this;
        }

        public Criteria andDatatimeLikeInsensitive(String value) {
            addCriterion("upper(dataTime) like", value.toUpperCase(), "datatime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}