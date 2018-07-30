package com.kaituo.pms.domain;

import java.util.ArrayList;
import java.util.List;

public class PrizeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PrizeExample() {
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

        public Criteria andPrizeIdIsNull() {
            addCriterion("prize_id is null");
            return (Criteria) this;
        }

        public Criteria andPrizeIdIsNotNull() {
            addCriterion("prize_id is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeIdEqualTo(Integer value) {
            addCriterion("prize_id =", value, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdNotEqualTo(Integer value) {
            addCriterion("prize_id <>", value, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdGreaterThan(Integer value) {
            addCriterion("prize_id >", value, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("prize_id >=", value, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdLessThan(Integer value) {
            addCriterion("prize_id <", value, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdLessThanOrEqualTo(Integer value) {
            addCriterion("prize_id <=", value, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdIn(List<Integer> values) {
            addCriterion("prize_id in", values, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdNotIn(List<Integer> values) {
            addCriterion("prize_id not in", values, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdBetween(Integer value1, Integer value2) {
            addCriterion("prize_id between", value1, value2, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("prize_id not between", value1, value2, "prizeId");
            return (Criteria) this;
        }

        public Criteria andPrizeNameIsNull() {
            addCriterion("prize_name is null");
            return (Criteria) this;
        }

        public Criteria andPrizeNameIsNotNull() {
            addCriterion("prize_name is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeNameEqualTo(String value) {
            addCriterion("prize_name =", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameNotEqualTo(String value) {
            addCriterion("prize_name <>", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameGreaterThan(String value) {
            addCriterion("prize_name >", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameGreaterThanOrEqualTo(String value) {
            addCriterion("prize_name >=", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameLessThan(String value) {
            addCriterion("prize_name <", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameLessThanOrEqualTo(String value) {
            addCriterion("prize_name <=", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameLike(String value) {
            addCriterion("prize_name like", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameNotLike(String value) {
            addCriterion("prize_name not like", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameIn(List<String> values) {
            addCriterion("prize_name in", values, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameNotIn(List<String> values) {
            addCriterion("prize_name not in", values, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameBetween(String value1, String value2) {
            addCriterion("prize_name between", value1, value2, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameNotBetween(String value1, String value2) {
            addCriterion("prize_name not between", value1, value2, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeImageIsNull() {
            addCriterion("prize_image is null");
            return (Criteria) this;
        }

        public Criteria andPrizeImageIsNotNull() {
            addCriterion("prize_image is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeImageEqualTo(String value) {
            addCriterion("prize_image =", value, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageNotEqualTo(String value) {
            addCriterion("prize_image <>", value, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageGreaterThan(String value) {
            addCriterion("prize_image >", value, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageGreaterThanOrEqualTo(String value) {
            addCriterion("prize_image >=", value, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageLessThan(String value) {
            addCriterion("prize_image <", value, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageLessThanOrEqualTo(String value) {
            addCriterion("prize_image <=", value, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageLike(String value) {
            addCriterion("prize_image like", value, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageNotLike(String value) {
            addCriterion("prize_image not like", value, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageIn(List<String> values) {
            addCriterion("prize_image in", values, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageNotIn(List<String> values) {
            addCriterion("prize_image not in", values, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageBetween(String value1, String value2) {
            addCriterion("prize_image between", value1, value2, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageNotBetween(String value1, String value2) {
            addCriterion("prize_image not between", value1, value2, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeAmountIsNull() {
            addCriterion("prize_amount is null");
            return (Criteria) this;
        }

        public Criteria andPrizeAmountIsNotNull() {
            addCriterion("prize_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeAmountEqualTo(Integer value) {
            addCriterion("prize_amount =", value, "prizeAmount");
            return (Criteria) this;
        }

        public Criteria andPrizeAmountNotEqualTo(Integer value) {
            addCriterion("prize_amount <>", value, "prizeAmount");
            return (Criteria) this;
        }

        public Criteria andPrizeAmountGreaterThan(Integer value) {
            addCriterion("prize_amount >", value, "prizeAmount");
            return (Criteria) this;
        }

        public Criteria andPrizeAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("prize_amount >=", value, "prizeAmount");
            return (Criteria) this;
        }

        public Criteria andPrizeAmountLessThan(Integer value) {
            addCriterion("prize_amount <", value, "prizeAmount");
            return (Criteria) this;
        }

        public Criteria andPrizeAmountLessThanOrEqualTo(Integer value) {
            addCriterion("prize_amount <=", value, "prizeAmount");
            return (Criteria) this;
        }

        public Criteria andPrizeAmountIn(List<Integer> values) {
            addCriterion("prize_amount in", values, "prizeAmount");
            return (Criteria) this;
        }

        public Criteria andPrizeAmountNotIn(List<Integer> values) {
            addCriterion("prize_amount not in", values, "prizeAmount");
            return (Criteria) this;
        }

        public Criteria andPrizeAmountBetween(Integer value1, Integer value2) {
            addCriterion("prize_amount between", value1, value2, "prizeAmount");
            return (Criteria) this;
        }

        public Criteria andPrizeAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("prize_amount not between", value1, value2, "prizeAmount");
            return (Criteria) this;
        }

        public Criteria andPrizePriceIsNull() {
            addCriterion("prize_price is null");
            return (Criteria) this;
        }

        public Criteria andPrizePriceIsNotNull() {
            addCriterion("prize_price is not null");
            return (Criteria) this;
        }

        public Criteria andPrizePriceEqualTo(Integer value) {
            addCriterion("prize_price =", value, "prizePrice");
            return (Criteria) this;
        }

        public Criteria andPrizePriceNotEqualTo(Integer value) {
            addCriterion("prize_price <>", value, "prizePrice");
            return (Criteria) this;
        }

        public Criteria andPrizePriceGreaterThan(Integer value) {
            addCriterion("prize_price >", value, "prizePrice");
            return (Criteria) this;
        }

        public Criteria andPrizePriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("prize_price >=", value, "prizePrice");
            return (Criteria) this;
        }

        public Criteria andPrizePriceLessThan(Integer value) {
            addCriterion("prize_price <", value, "prizePrice");
            return (Criteria) this;
        }

        public Criteria andPrizePriceLessThanOrEqualTo(Integer value) {
            addCriterion("prize_price <=", value, "prizePrice");
            return (Criteria) this;
        }

        public Criteria andPrizePriceIn(List<Integer> values) {
            addCriterion("prize_price in", values, "prizePrice");
            return (Criteria) this;
        }

        public Criteria andPrizePriceNotIn(List<Integer> values) {
            addCriterion("prize_price not in", values, "prizePrice");
            return (Criteria) this;
        }

        public Criteria andPrizePriceBetween(Integer value1, Integer value2) {
            addCriterion("prize_price between", value1, value2, "prizePrice");
            return (Criteria) this;
        }

        public Criteria andPrizePriceNotBetween(Integer value1, Integer value2) {
            addCriterion("prize_price not between", value1, value2, "prizePrice");
            return (Criteria) this;
        }

        public Criteria andPrizeQuotaIsNull() {
            addCriterion("prize_quota is null");
            return (Criteria) this;
        }

        public Criteria andPrizeQuotaIsNotNull() {
            addCriterion("prize_quota is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeQuotaEqualTo(Integer value) {
            addCriterion("prize_quota =", value, "prizeQuota");
            return (Criteria) this;
        }

        public Criteria andPrizeQuotaNotEqualTo(Integer value) {
            addCriterion("prize_quota <>", value, "prizeQuota");
            return (Criteria) this;
        }

        public Criteria andPrizeQuotaGreaterThan(Integer value) {
            addCriterion("prize_quota >", value, "prizeQuota");
            return (Criteria) this;
        }

        public Criteria andPrizeQuotaGreaterThanOrEqualTo(Integer value) {
            addCriterion("prize_quota >=", value, "prizeQuota");
            return (Criteria) this;
        }

        public Criteria andPrizeQuotaLessThan(Integer value) {
            addCriterion("prize_quota <", value, "prizeQuota");
            return (Criteria) this;
        }

        public Criteria andPrizeQuotaLessThanOrEqualTo(Integer value) {
            addCriterion("prize_quota <=", value, "prizeQuota");
            return (Criteria) this;
        }

        public Criteria andPrizeQuotaIn(List<Integer> values) {
            addCriterion("prize_quota in", values, "prizeQuota");
            return (Criteria) this;
        }

        public Criteria andPrizeQuotaNotIn(List<Integer> values) {
            addCriterion("prize_quota not in", values, "prizeQuota");
            return (Criteria) this;
        }

        public Criteria andPrizeQuotaBetween(Integer value1, Integer value2) {
            addCriterion("prize_quota between", value1, value2, "prizeQuota");
            return (Criteria) this;
        }

        public Criteria andPrizeQuotaNotBetween(Integer value1, Integer value2) {
            addCriterion("prize_quota not between", value1, value2, "prizeQuota");
            return (Criteria) this;
        }

        public Criteria andPrizeConditionIsNull() {
            addCriterion("prize_condition is null");
            return (Criteria) this;
        }

        public Criteria andPrizeConditionIsNotNull() {
            addCriterion("prize_condition is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeConditionEqualTo(Integer value) {
            addCriterion("prize_condition =", value, "prizeCondition");
            return (Criteria) this;
        }

        public Criteria andPrizeConditionNotEqualTo(Integer value) {
            addCriterion("prize_condition <>", value, "prizeCondition");
            return (Criteria) this;
        }

        public Criteria andPrizeConditionGreaterThan(Integer value) {
            addCriterion("prize_condition >", value, "prizeCondition");
            return (Criteria) this;
        }

        public Criteria andPrizeConditionGreaterThanOrEqualTo(Integer value) {
            addCriterion("prize_condition >=", value, "prizeCondition");
            return (Criteria) this;
        }

        public Criteria andPrizeConditionLessThan(Integer value) {
            addCriterion("prize_condition <", value, "prizeCondition");
            return (Criteria) this;
        }

        public Criteria andPrizeConditionLessThanOrEqualTo(Integer value) {
            addCriterion("prize_condition <=", value, "prizeCondition");
            return (Criteria) this;
        }

        public Criteria andPrizeConditionIn(List<Integer> values) {
            addCriterion("prize_condition in", values, "prizeCondition");
            return (Criteria) this;
        }

        public Criteria andPrizeConditionNotIn(List<Integer> values) {
            addCriterion("prize_condition not in", values, "prizeCondition");
            return (Criteria) this;
        }

        public Criteria andPrizeConditionBetween(Integer value1, Integer value2) {
            addCriterion("prize_condition between", value1, value2, "prizeCondition");
            return (Criteria) this;
        }

        public Criteria andPrizeConditionNotBetween(Integer value1, Integer value2) {
            addCriterion("prize_condition not between", value1, value2, "prizeCondition");
            return (Criteria) this;
        }

        public Criteria andPrizeStatusIsNull() {
            addCriterion("prize_status is null");
            return (Criteria) this;
        }

        public Criteria andPrizeStatusIsNotNull() {
            addCriterion("prize_status is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeStatusEqualTo(Integer value) {
            addCriterion("prize_status =", value, "prizeStatus");
            return (Criteria) this;
        }

        public Criteria andPrizeStatusNotEqualTo(Integer value) {
            addCriterion("prize_status <>", value, "prizeStatus");
            return (Criteria) this;
        }

        public Criteria andPrizeStatusGreaterThan(Integer value) {
            addCriterion("prize_status >", value, "prizeStatus");
            return (Criteria) this;
        }

        public Criteria andPrizeStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("prize_status >=", value, "prizeStatus");
            return (Criteria) this;
        }

        public Criteria andPrizeStatusLessThan(Integer value) {
            addCriterion("prize_status <", value, "prizeStatus");
            return (Criteria) this;
        }

        public Criteria andPrizeStatusLessThanOrEqualTo(Integer value) {
            addCriterion("prize_status <=", value, "prizeStatus");
            return (Criteria) this;
        }

        public Criteria andPrizeStatusIn(List<Integer> values) {
            addCriterion("prize_status in", values, "prizeStatus");
            return (Criteria) this;
        }

        public Criteria andPrizeStatusNotIn(List<Integer> values) {
            addCriterion("prize_status not in", values, "prizeStatus");
            return (Criteria) this;
        }

        public Criteria andPrizeStatusBetween(Integer value1, Integer value2) {
            addCriterion("prize_status between", value1, value2, "prizeStatus");
            return (Criteria) this;
        }

        public Criteria andPrizeStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("prize_status not between", value1, value2, "prizeStatus");
            return (Criteria) this;
        }

        public Criteria andPrizeBuyIsNull() {
            addCriterion("prize_buy is null");
            return (Criteria) this;
        }

        public Criteria andPrizeBuyIsNotNull() {
            addCriterion("prize_buy is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeBuyEqualTo(Integer value) {
            addCriterion("prize_buy =", value, "prizeBuy");
            return (Criteria) this;
        }

        public Criteria andPrizeBuyNotEqualTo(Integer value) {
            addCriterion("prize_buy <>", value, "prizeBuy");
            return (Criteria) this;
        }

        public Criteria andPrizeBuyGreaterThan(Integer value) {
            addCriterion("prize_buy >", value, "prizeBuy");
            return (Criteria) this;
        }

        public Criteria andPrizeBuyGreaterThanOrEqualTo(Integer value) {
            addCriterion("prize_buy >=", value, "prizeBuy");
            return (Criteria) this;
        }

        public Criteria andPrizeBuyLessThan(Integer value) {
            addCriterion("prize_buy <", value, "prizeBuy");
            return (Criteria) this;
        }

        public Criteria andPrizeBuyLessThanOrEqualTo(Integer value) {
            addCriterion("prize_buy <=", value, "prizeBuy");
            return (Criteria) this;
        }

        public Criteria andPrizeBuyIn(List<Integer> values) {
            addCriterion("prize_buy in", values, "prizeBuy");
            return (Criteria) this;
        }

        public Criteria andPrizeBuyNotIn(List<Integer> values) {
            addCriterion("prize_buy not in", values, "prizeBuy");
            return (Criteria) this;
        }

        public Criteria andPrizeBuyBetween(Integer value1, Integer value2) {
            addCriterion("prize_buy between", value1, value2, "prizeBuy");
            return (Criteria) this;
        }

        public Criteria andPrizeBuyNotBetween(Integer value1, Integer value2) {
            addCriterion("prize_buy not between", value1, value2, "prizeBuy");
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