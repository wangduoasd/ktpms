package com.kaituo.pms.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DeptExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeptExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andDeptIdIsNull() {
            addCriterion("dept_id is null");
            return (Criteria) this;
        }

        public Criteria andDeptIdIsNotNull() {
            addCriterion("dept_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeptIdEqualTo(Integer value) {
            addCriterion("dept_id =", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdNotEqualTo(Integer value) {
            addCriterion("dept_id <>", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdGreaterThan(Integer value) {
            addCriterion("dept_id >", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dept_id >=", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdLessThan(Integer value) {
            addCriterion("dept_id <", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdLessThanOrEqualTo(Integer value) {
            addCriterion("dept_id <=", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdIn(List<Integer> values) {
            addCriterion("dept_id in", values, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdNotIn(List<Integer> values) {
            addCriterion("dept_id not in", values, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdBetween(Integer value1, Integer value2) {
            addCriterion("dept_id between", value1, value2, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dept_id not between", value1, value2, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNull() {
            addCriterion("dept_name is null");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNotNull() {
            addCriterion("dept_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeptNameEqualTo(String value) {
            addCriterion("dept_name =", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotEqualTo(String value) {
            addCriterion("dept_name <>", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThan(String value) {
            addCriterion("dept_name >", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("dept_name >=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThan(String value) {
            addCriterion("dept_name <", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThanOrEqualTo(String value) {
            addCriterion("dept_name <=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLike(String value) {
            addCriterion("dept_name like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotLike(String value) {
            addCriterion("dept_name not like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameIn(List<String> values) {
            addCriterion("dept_name in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotIn(List<String> values) {
            addCriterion("dept_name not in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameBetween(String value1, String value2) {
            addCriterion("dept_name between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotBetween(String value1, String value2) {
            addCriterion("dept_name not between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptDescribeIsNull() {
            addCriterion("dept_describe is null");
            return (Criteria) this;
        }

        public Criteria andDeptDescribeIsNotNull() {
            addCriterion("dept_describe is not null");
            return (Criteria) this;
        }

        public Criteria andDeptDescribeEqualTo(String value) {
            addCriterion("dept_describe =", value, "deptDescribe");
            return (Criteria) this;
        }

        public Criteria andDeptDescribeNotEqualTo(String value) {
            addCriterion("dept_describe <>", value, "deptDescribe");
            return (Criteria) this;
        }

        public Criteria andDeptDescribeGreaterThan(String value) {
            addCriterion("dept_describe >", value, "deptDescribe");
            return (Criteria) this;
        }

        public Criteria andDeptDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("dept_describe >=", value, "deptDescribe");
            return (Criteria) this;
        }

        public Criteria andDeptDescribeLessThan(String value) {
            addCriterion("dept_describe <", value, "deptDescribe");
            return (Criteria) this;
        }

        public Criteria andDeptDescribeLessThanOrEqualTo(String value) {
            addCriterion("dept_describe <=", value, "deptDescribe");
            return (Criteria) this;
        }

        public Criteria andDeptDescribeLike(String value) {
            addCriterion("dept_describe like", value, "deptDescribe");
            return (Criteria) this;
        }

        public Criteria andDeptDescribeNotLike(String value) {
            addCriterion("dept_describe not like", value, "deptDescribe");
            return (Criteria) this;
        }

        public Criteria andDeptDescribeIn(List<String> values) {
            addCriterion("dept_describe in", values, "deptDescribe");
            return (Criteria) this;
        }

        public Criteria andDeptDescribeNotIn(List<String> values) {
            addCriterion("dept_describe not in", values, "deptDescribe");
            return (Criteria) this;
        }

        public Criteria andDeptDescribeBetween(String value1, String value2) {
            addCriterion("dept_describe between", value1, value2, "deptDescribe");
            return (Criteria) this;
        }

        public Criteria andDeptDescribeNotBetween(String value1, String value2) {
            addCriterion("dept_describe not between", value1, value2, "deptDescribe");
            return (Criteria) this;
        }

        public Criteria andDeptInductiontimeIsNull() {
            addCriterion("dept_inductiontime is null");
            return (Criteria) this;
        }

        public Criteria andDeptInductiontimeIsNotNull() {
            addCriterion("dept_inductiontime is not null");
            return (Criteria) this;
        }

        public Criteria andDeptInductiontimeEqualTo(Date value) {
            addCriterionForJDBCDate("dept_inductiontime =", value, "deptInductiontime");
            return (Criteria) this;
        }

        public Criteria andDeptInductiontimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("dept_inductiontime <>", value, "deptInductiontime");
            return (Criteria) this;
        }

        public Criteria andDeptInductiontimeGreaterThan(Date value) {
            addCriterionForJDBCDate("dept_inductiontime >", value, "deptInductiontime");
            return (Criteria) this;
        }

        public Criteria andDeptInductiontimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("dept_inductiontime >=", value, "deptInductiontime");
            return (Criteria) this;
        }

        public Criteria andDeptInductiontimeLessThan(Date value) {
            addCriterionForJDBCDate("dept_inductiontime <", value, "deptInductiontime");
            return (Criteria) this;
        }

        public Criteria andDeptInductiontimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("dept_inductiontime <=", value, "deptInductiontime");
            return (Criteria) this;
        }

        public Criteria andDeptInductiontimeIn(List<Date> values) {
            addCriterionForJDBCDate("dept_inductiontime in", values, "deptInductiontime");
            return (Criteria) this;
        }

        public Criteria andDeptInductiontimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("dept_inductiontime not in", values, "deptInductiontime");
            return (Criteria) this;
        }

        public Criteria andDeptInductiontimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("dept_inductiontime between", value1, value2, "deptInductiontime");
            return (Criteria) this;
        }

        public Criteria andDeptInductiontimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("dept_inductiontime not between", value1, value2, "deptInductiontime");
            return (Criteria) this;
        }

        public Criteria andDeptStatusIsNull() {
            addCriterion("dept_status is null");
            return (Criteria) this;
        }

        public Criteria andDeptStatusIsNotNull() {
            addCriterion("dept_status is not null");
            return (Criteria) this;
        }

        public Criteria andDeptStatusEqualTo(Integer value) {
            addCriterion("dept_status =", value, "deptStatus");
            return (Criteria) this;
        }

        public Criteria andDeptStatusNotEqualTo(Integer value) {
            addCriterion("dept_status <>", value, "deptStatus");
            return (Criteria) this;
        }

        public Criteria andDeptStatusGreaterThan(Integer value) {
            addCriterion("dept_status >", value, "deptStatus");
            return (Criteria) this;
        }

        public Criteria andDeptStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("dept_status >=", value, "deptStatus");
            return (Criteria) this;
        }

        public Criteria andDeptStatusLessThan(Integer value) {
            addCriterion("dept_status <", value, "deptStatus");
            return (Criteria) this;
        }

        public Criteria andDeptStatusLessThanOrEqualTo(Integer value) {
            addCriterion("dept_status <=", value, "deptStatus");
            return (Criteria) this;
        }

        public Criteria andDeptStatusIn(List<Integer> values) {
            addCriterion("dept_status in", values, "deptStatus");
            return (Criteria) this;
        }

        public Criteria andDeptStatusNotIn(List<Integer> values) {
            addCriterion("dept_status not in", values, "deptStatus");
            return (Criteria) this;
        }

        public Criteria andDeptStatusBetween(Integer value1, Integer value2) {
            addCriterion("dept_status between", value1, value2, "deptStatus");
            return (Criteria) this;
        }

        public Criteria andDeptStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("dept_status not between", value1, value2, "deptStatus");
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