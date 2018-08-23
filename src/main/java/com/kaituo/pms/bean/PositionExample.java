package com.kaituo.pms.bean;

import java.util.ArrayList;
import java.util.List;

public class PositionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PositionExample() {
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

        public Criteria andDeptPositionIdIsNull() {
            addCriterion("dept_position_id is null");
            return (Criteria) this;
        }

        public Criteria andDeptPositionIdIsNotNull() {
            addCriterion("dept_position_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeptPositionIdEqualTo(Integer value) {
            addCriterion("dept_position_id =", value, "deptPositionId");
            return (Criteria) this;
        }

        public Criteria andDeptPositionIdNotEqualTo(Integer value) {
            addCriterion("dept_position_id <>", value, "deptPositionId");
            return (Criteria) this;
        }

        public Criteria andDeptPositionIdGreaterThan(Integer value) {
            addCriterion("dept_position_id >", value, "deptPositionId");
            return (Criteria) this;
        }

        public Criteria andDeptPositionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dept_position_id >=", value, "deptPositionId");
            return (Criteria) this;
        }

        public Criteria andDeptPositionIdLessThan(Integer value) {
            addCriterion("dept_position_id <", value, "deptPositionId");
            return (Criteria) this;
        }

        public Criteria andDeptPositionIdLessThanOrEqualTo(Integer value) {
            addCriterion("dept_position_id <=", value, "deptPositionId");
            return (Criteria) this;
        }

        public Criteria andDeptPositionIdIn(List<Integer> values) {
            addCriterion("dept_position_id in", values, "deptPositionId");
            return (Criteria) this;
        }

        public Criteria andDeptPositionIdNotIn(List<Integer> values) {
            addCriterion("dept_position_id not in", values, "deptPositionId");
            return (Criteria) this;
        }

        public Criteria andDeptPositionIdBetween(Integer value1, Integer value2) {
            addCriterion("dept_position_id between", value1, value2, "deptPositionId");
            return (Criteria) this;
        }

        public Criteria andDeptPositionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dept_position_id not between", value1, value2, "deptPositionId");
            return (Criteria) this;
        }

        public Criteria andPositionNameIsNull() {
            addCriterion("position_name is null");
            return (Criteria) this;
        }

        public Criteria andPositionNameIsNotNull() {
            addCriterion("position_name is not null");
            return (Criteria) this;
        }

        public Criteria andPositionNameEqualTo(String value) {
            addCriterion("position_name =", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameNotEqualTo(String value) {
            addCriterion("position_name <>", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameGreaterThan(String value) {
            addCriterion("position_name >", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameGreaterThanOrEqualTo(String value) {
            addCriterion("position_name >=", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameLessThan(String value) {
            addCriterion("position_name <", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameLessThanOrEqualTo(String value) {
            addCriterion("position_name <=", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameLike(String value) {
            addCriterion("position_name like", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameNotLike(String value) {
            addCriterion("position_name not like", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameIn(List<String> values) {
            addCriterion("position_name in", values, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameNotIn(List<String> values) {
            addCriterion("position_name not in", values, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameBetween(String value1, String value2) {
            addCriterion("position_name between", value1, value2, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameNotBetween(String value1, String value2) {
            addCriterion("position_name not between", value1, value2, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionStatusIsNull() {
            addCriterion("position_status is null");
            return (Criteria) this;
        }

        public Criteria andPositionStatusIsNotNull() {
            addCriterion("position_status is not null");
            return (Criteria) this;
        }

        public Criteria andPositionStatusEqualTo(Boolean value) {
            addCriterion("position_status =", value, "positionStatus");
            return (Criteria) this;
        }

        public Criteria andPositionStatusNotEqualTo(Boolean value) {
            addCriterion("position_status <>", value, "positionStatus");
            return (Criteria) this;
        }

        public Criteria andPositionStatusGreaterThan(Boolean value) {
            addCriterion("position_status >", value, "positionStatus");
            return (Criteria) this;
        }

        public Criteria andPositionStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("position_status >=", value, "positionStatus");
            return (Criteria) this;
        }

        public Criteria andPositionStatusLessThan(Boolean value) {
            addCriterion("position_status <", value, "positionStatus");
            return (Criteria) this;
        }

        public Criteria andPositionStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("position_status <=", value, "positionStatus");
            return (Criteria) this;
        }

        public Criteria andPositionStatusIn(List<Boolean> values) {
            addCriterion("position_status in", values, "positionStatus");
            return (Criteria) this;
        }

        public Criteria andPositionStatusNotIn(List<Boolean> values) {
            addCriterion("position_status not in", values, "positionStatus");
            return (Criteria) this;
        }

        public Criteria andPositionStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("position_status between", value1, value2, "positionStatus");
            return (Criteria) this;
        }

        public Criteria andPositionStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("position_status not between", value1, value2, "positionStatus");
            return (Criteria) this;
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