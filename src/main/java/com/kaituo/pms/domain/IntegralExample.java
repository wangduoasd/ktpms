package com.kaituo.pms.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IntegralExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IntegralExample() {
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

        public Criteria andIntegralIdIsNull() {
            addCriterion("integral_id is null");
            return (Criteria) this;
        }

        public Criteria andIntegralIdIsNotNull() {
            addCriterion("integral_id is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralIdEqualTo(Integer value) {
            addCriterion("integral_id =", value, "integralId");
            return (Criteria) this;
        }

        public Criteria andIntegralIdNotEqualTo(Integer value) {
            addCriterion("integral_id <>", value, "integralId");
            return (Criteria) this;
        }

        public Criteria andIntegralIdGreaterThan(Integer value) {
            addCriterion("integral_id >", value, "integralId");
            return (Criteria) this;
        }

        public Criteria andIntegralIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("integral_id >=", value, "integralId");
            return (Criteria) this;
        }

        public Criteria andIntegralIdLessThan(Integer value) {
            addCriterion("integral_id <", value, "integralId");
            return (Criteria) this;
        }

        public Criteria andIntegralIdLessThanOrEqualTo(Integer value) {
            addCriterion("integral_id <=", value, "integralId");
            return (Criteria) this;
        }

        public Criteria andIntegralIdIn(List<Integer> values) {
            addCriterion("integral_id in", values, "integralId");
            return (Criteria) this;
        }

        public Criteria andIntegralIdNotIn(List<Integer> values) {
            addCriterion("integral_id not in", values, "integralId");
            return (Criteria) this;
        }

        public Criteria andIntegralIdBetween(Integer value1, Integer value2) {
            addCriterion("integral_id between", value1, value2, "integralId");
            return (Criteria) this;
        }

        public Criteria andIntegralIdNotBetween(Integer value1, Integer value2) {
            addCriterion("integral_id not between", value1, value2, "integralId");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalIsNull() {
            addCriterion("integral_total is null");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalIsNotNull() {
            addCriterion("integral_total is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalEqualTo(Integer value) {
            addCriterion("integral_total =", value, "integralTotal");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalNotEqualTo(Integer value) {
            addCriterion("integral_total <>", value, "integralTotal");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalGreaterThan(Integer value) {
            addCriterion("integral_total >", value, "integralTotal");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("integral_total >=", value, "integralTotal");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalLessThan(Integer value) {
            addCriterion("integral_total <", value, "integralTotal");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalLessThanOrEqualTo(Integer value) {
            addCriterion("integral_total <=", value, "integralTotal");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalIn(List<Integer> values) {
            addCriterion("integral_total in", values, "integralTotal");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalNotIn(List<Integer> values) {
            addCriterion("integral_total not in", values, "integralTotal");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalBetween(Integer value1, Integer value2) {
            addCriterion("integral_total between", value1, value2, "integralTotal");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("integral_total not between", value1, value2, "integralTotal");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andIntegralChangestrIsNull() {
            addCriterion("integral_changeStr is null");
            return (Criteria) this;
        }

        public Criteria andIntegralChangestrIsNotNull() {
            addCriterion("integral_changeStr is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralChangestrEqualTo(String value) {
            addCriterion("integral_changeStr =", value, "integralChangestr");
            return (Criteria) this;
        }

        public Criteria andIntegralChangestrNotEqualTo(String value) {
            addCriterion("integral_changeStr <>", value, "integralChangestr");
            return (Criteria) this;
        }

        public Criteria andIntegralChangestrGreaterThan(String value) {
            addCriterion("integral_changeStr >", value, "integralChangestr");
            return (Criteria) this;
        }

        public Criteria andIntegralChangestrGreaterThanOrEqualTo(String value) {
            addCriterion("integral_changeStr >=", value, "integralChangestr");
            return (Criteria) this;
        }

        public Criteria andIntegralChangestrLessThan(String value) {
            addCriterion("integral_changeStr <", value, "integralChangestr");
            return (Criteria) this;
        }

        public Criteria andIntegralChangestrLessThanOrEqualTo(String value) {
            addCriterion("integral_changeStr <=", value, "integralChangestr");
            return (Criteria) this;
        }

        public Criteria andIntegralChangestrLike(String value) {
            addCriterion("integral_changeStr like", value, "integralChangestr");
            return (Criteria) this;
        }

        public Criteria andIntegralChangestrNotLike(String value) {
            addCriterion("integral_changeStr not like", value, "integralChangestr");
            return (Criteria) this;
        }

        public Criteria andIntegralChangestrIn(List<String> values) {
            addCriterion("integral_changeStr in", values, "integralChangestr");
            return (Criteria) this;
        }

        public Criteria andIntegralChangestrNotIn(List<String> values) {
            addCriterion("integral_changeStr not in", values, "integralChangestr");
            return (Criteria) this;
        }

        public Criteria andIntegralChangestrBetween(String value1, String value2) {
            addCriterion("integral_changeStr between", value1, value2, "integralChangestr");
            return (Criteria) this;
        }

        public Criteria andIntegralChangestrNotBetween(String value1, String value2) {
            addCriterion("integral_changeStr not between", value1, value2, "integralChangestr");
            return (Criteria) this;
        }

        public Criteria andIntegralTimeIsNull() {
            addCriterion("integral_time is null");
            return (Criteria) this;
        }

        public Criteria andIntegralTimeIsNotNull() {
            addCriterion("integral_time is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralTimeEqualTo(Date value) {
            addCriterion("integral_time =", value, "integralTime");
            return (Criteria) this;
        }

        public Criteria andIntegralTimeNotEqualTo(Date value) {
            addCriterion("integral_time <>", value, "integralTime");
            return (Criteria) this;
        }

        public Criteria andIntegralTimeGreaterThan(Date value) {
            addCriterion("integral_time >", value, "integralTime");
            return (Criteria) this;
        }

        public Criteria andIntegralTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("integral_time >=", value, "integralTime");
            return (Criteria) this;
        }

        public Criteria andIntegralTimeLessThan(Date value) {
            addCriterion("integral_time <", value, "integralTime");
            return (Criteria) this;
        }

        public Criteria andIntegralTimeLessThanOrEqualTo(Date value) {
            addCriterion("integral_time <=", value, "integralTime");
            return (Criteria) this;
        }

        public Criteria andIntegralTimeIn(List<Date> values) {
            addCriterion("integral_time in", values, "integralTime");
            return (Criteria) this;
        }

        public Criteria andIntegralTimeNotIn(List<Date> values) {
            addCriterion("integral_time not in", values, "integralTime");
            return (Criteria) this;
        }

        public Criteria andIntegralTimeBetween(Date value1, Date value2) {
            addCriterion("integral_time between", value1, value2, "integralTime");
            return (Criteria) this;
        }

        public Criteria andIntegralTimeNotBetween(Date value1, Date value2) {
            addCriterion("integral_time not between", value1, value2, "integralTime");
            return (Criteria) this;
        }

        public Criteria andIntegralChangeintIsNull() {
            addCriterion("integral_changeInt is null");
            return (Criteria) this;
        }

        public Criteria andIntegralChangeintIsNotNull() {
            addCriterion("integral_changeInt is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralChangeintEqualTo(Integer value) {
            addCriterion("integral_changeInt =", value, "integralChangeint");
            return (Criteria) this;
        }

        public Criteria andIntegralChangeintNotEqualTo(Integer value) {
            addCriterion("integral_changeInt <>", value, "integralChangeint");
            return (Criteria) this;
        }

        public Criteria andIntegralChangeintGreaterThan(Integer value) {
            addCriterion("integral_changeInt >", value, "integralChangeint");
            return (Criteria) this;
        }

        public Criteria andIntegralChangeintGreaterThanOrEqualTo(Integer value) {
            addCriterion("integral_changeInt >=", value, "integralChangeint");
            return (Criteria) this;
        }

        public Criteria andIntegralChangeintLessThan(Integer value) {
            addCriterion("integral_changeInt <", value, "integralChangeint");
            return (Criteria) this;
        }

        public Criteria andIntegralChangeintLessThanOrEqualTo(Integer value) {
            addCriterion("integral_changeInt <=", value, "integralChangeint");
            return (Criteria) this;
        }

        public Criteria andIntegralChangeintIn(List<Integer> values) {
            addCriterion("integral_changeInt in", values, "integralChangeint");
            return (Criteria) this;
        }

        public Criteria andIntegralChangeintNotIn(List<Integer> values) {
            addCriterion("integral_changeInt not in", values, "integralChangeint");
            return (Criteria) this;
        }

        public Criteria andIntegralChangeintBetween(Integer value1, Integer value2) {
            addCriterion("integral_changeInt between", value1, value2, "integralChangeint");
            return (Criteria) this;
        }

        public Criteria andIntegralChangeintNotBetween(Integer value1, Integer value2) {
            addCriterion("integral_changeInt not between", value1, value2, "integralChangeint");
            return (Criteria) this;
        }

        public Criteria andIntegralStatusIsNull() {
            addCriterion("integral_status is null");
            return (Criteria) this;
        }

        public Criteria andIntegralStatusIsNotNull() {
            addCriterion("integral_status is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralStatusEqualTo(Integer value) {
            addCriterion("integral_status =", value, "integralStatus");
            return (Criteria) this;
        }

        public Criteria andIntegralStatusNotEqualTo(Integer value) {
            addCriterion("integral_status <>", value, "integralStatus");
            return (Criteria) this;
        }

        public Criteria andIntegralStatusGreaterThan(Integer value) {
            addCriterion("integral_status >", value, "integralStatus");
            return (Criteria) this;
        }

        public Criteria andIntegralStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("integral_status >=", value, "integralStatus");
            return (Criteria) this;
        }

        public Criteria andIntegralStatusLessThan(Integer value) {
            addCriterion("integral_status <", value, "integralStatus");
            return (Criteria) this;
        }

        public Criteria andIntegralStatusLessThanOrEqualTo(Integer value) {
            addCriterion("integral_status <=", value, "integralStatus");
            return (Criteria) this;
        }

        public Criteria andIntegralStatusIn(List<Integer> values) {
            addCriterion("integral_status in", values, "integralStatus");
            return (Criteria) this;
        }

        public Criteria andIntegralStatusNotIn(List<Integer> values) {
            addCriterion("integral_status not in", values, "integralStatus");
            return (Criteria) this;
        }

        public Criteria andIntegralStatusBetween(Integer value1, Integer value2) {
            addCriterion("integral_status between", value1, value2, "integralStatus");
            return (Criteria) this;
        }

        public Criteria andIntegralStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("integral_status not between", value1, value2, "integralStatus");
            return (Criteria) this;
        }

        public Criteria andIntegralOperatorIsNull() {
            addCriterion("integral_operator is null");
            return (Criteria) this;
        }

        public Criteria andIntegralOperatorIsNotNull() {
            addCriterion("integral_operator is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralOperatorEqualTo(String value) {
            addCriterion("integral_operator =", value, "integralOperator");
            return (Criteria) this;
        }

        public Criteria andIntegralOperatorNotEqualTo(String value) {
            addCriterion("integral_operator <>", value, "integralOperator");
            return (Criteria) this;
        }

        public Criteria andIntegralOperatorGreaterThan(String value) {
            addCriterion("integral_operator >", value, "integralOperator");
            return (Criteria) this;
        }

        public Criteria andIntegralOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("integral_operator >=", value, "integralOperator");
            return (Criteria) this;
        }

        public Criteria andIntegralOperatorLessThan(String value) {
            addCriterion("integral_operator <", value, "integralOperator");
            return (Criteria) this;
        }

        public Criteria andIntegralOperatorLessThanOrEqualTo(String value) {
            addCriterion("integral_operator <=", value, "integralOperator");
            return (Criteria) this;
        }

        public Criteria andIntegralOperatorLike(String value) {
            addCriterion("integral_operator like", value, "integralOperator");
            return (Criteria) this;
        }

        public Criteria andIntegralOperatorNotLike(String value) {
            addCriterion("integral_operator not like", value, "integralOperator");
            return (Criteria) this;
        }

        public Criteria andIntegralOperatorIn(List<String> values) {
            addCriterion("integral_operator in", values, "integralOperator");
            return (Criteria) this;
        }

        public Criteria andIntegralOperatorNotIn(List<String> values) {
            addCriterion("integral_operator not in", values, "integralOperator");
            return (Criteria) this;
        }

        public Criteria andIntegralOperatorBetween(String value1, String value2) {
            addCriterion("integral_operator between", value1, value2, "integralOperator");
            return (Criteria) this;
        }

        public Criteria andIntegralOperatorNotBetween(String value1, String value2) {
            addCriterion("integral_operator not between", value1, value2, "integralOperator");
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