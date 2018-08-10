package com.kaituo.pms.bean;

import java.util.ArrayList;
import java.util.List;

public class VersionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VersionExample() {
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

        public Criteria andVersionIdIsNull() {
            addCriterion("version_id is null");
            return (Criteria) this;
        }

        public Criteria andVersionIdIsNotNull() {
            addCriterion("version_id is not null");
            return (Criteria) this;
        }

        public Criteria andVersionIdEqualTo(Integer value) {
            addCriterion("version_id =", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotEqualTo(Integer value) {
            addCriterion("version_id <>", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdGreaterThan(Integer value) {
            addCriterion("version_id >", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("version_id >=", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdLessThan(Integer value) {
            addCriterion("version_id <", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdLessThanOrEqualTo(Integer value) {
            addCriterion("version_id <=", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdIn(List<Integer> values) {
            addCriterion("version_id in", values, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotIn(List<Integer> values) {
            addCriterion("version_id not in", values, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdBetween(Integer value1, Integer value2) {
            addCriterion("version_id between", value1, value2, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("version_id not between", value1, value2, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentIsNull() {
            addCriterion("version_current is null");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentIsNotNull() {
            addCriterion("version_current is not null");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentEqualTo(String value) {
            addCriterion("version_current =", value, "versionCurrent");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentNotEqualTo(String value) {
            addCriterion("version_current <>", value, "versionCurrent");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentGreaterThan(String value) {
            addCriterion("version_current >", value, "versionCurrent");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentGreaterThanOrEqualTo(String value) {
            addCriterion("version_current >=", value, "versionCurrent");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentLessThan(String value) {
            addCriterion("version_current <", value, "versionCurrent");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentLessThanOrEqualTo(String value) {
            addCriterion("version_current <=", value, "versionCurrent");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentLike(String value) {
            addCriterion("version_current like", value, "versionCurrent");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentNotLike(String value) {
            addCriterion("version_current not like", value, "versionCurrent");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentIn(List<String> values) {
            addCriterion("version_current in", values, "versionCurrent");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentNotIn(List<String> values) {
            addCriterion("version_current not in", values, "versionCurrent");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentBetween(String value1, String value2) {
            addCriterion("version_current between", value1, value2, "versionCurrent");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentNotBetween(String value1, String value2) {
            addCriterion("version_current not between", value1, value2, "versionCurrent");
            return (Criteria) this;
        }

        public Criteria andVersionHistoryIsNull() {
            addCriterion("version_history is null");
            return (Criteria) this;
        }

        public Criteria andVersionHistoryIsNotNull() {
            addCriterion("version_history is not null");
            return (Criteria) this;
        }

        public Criteria andVersionHistoryEqualTo(String value) {
            addCriterion("version_history =", value, "versionHistory");
            return (Criteria) this;
        }

        public Criteria andVersionHistoryNotEqualTo(String value) {
            addCriterion("version_history <>", value, "versionHistory");
            return (Criteria) this;
        }

        public Criteria andVersionHistoryGreaterThan(String value) {
            addCriterion("version_history >", value, "versionHistory");
            return (Criteria) this;
        }

        public Criteria andVersionHistoryGreaterThanOrEqualTo(String value) {
            addCriterion("version_history >=", value, "versionHistory");
            return (Criteria) this;
        }

        public Criteria andVersionHistoryLessThan(String value) {
            addCriterion("version_history <", value, "versionHistory");
            return (Criteria) this;
        }

        public Criteria andVersionHistoryLessThanOrEqualTo(String value) {
            addCriterion("version_history <=", value, "versionHistory");
            return (Criteria) this;
        }

        public Criteria andVersionHistoryLike(String value) {
            addCriterion("version_history like", value, "versionHistory");
            return (Criteria) this;
        }

        public Criteria andVersionHistoryNotLike(String value) {
            addCriterion("version_history not like", value, "versionHistory");
            return (Criteria) this;
        }

        public Criteria andVersionHistoryIn(List<String> values) {
            addCriterion("version_history in", values, "versionHistory");
            return (Criteria) this;
        }

        public Criteria andVersionHistoryNotIn(List<String> values) {
            addCriterion("version_history not in", values, "versionHistory");
            return (Criteria) this;
        }

        public Criteria andVersionHistoryBetween(String value1, String value2) {
            addCriterion("version_history between", value1, value2, "versionHistory");
            return (Criteria) this;
        }

        public Criteria andVersionHistoryNotBetween(String value1, String value2) {
            addCriterion("version_history not between", value1, value2, "versionHistory");
            return (Criteria) this;
        }

        public Criteria andVersionOverviewIsNull() {
            addCriterion("version_overview is null");
            return (Criteria) this;
        }

        public Criteria andVersionOverviewIsNotNull() {
            addCriterion("version_overview is not null");
            return (Criteria) this;
        }

        public Criteria andVersionOverviewEqualTo(String value) {
            addCriterion("version_overview =", value, "versionOverview");
            return (Criteria) this;
        }

        public Criteria andVersionOverviewNotEqualTo(String value) {
            addCriterion("version_overview <>", value, "versionOverview");
            return (Criteria) this;
        }

        public Criteria andVersionOverviewGreaterThan(String value) {
            addCriterion("version_overview >", value, "versionOverview");
            return (Criteria) this;
        }

        public Criteria andVersionOverviewGreaterThanOrEqualTo(String value) {
            addCriterion("version_overview >=", value, "versionOverview");
            return (Criteria) this;
        }

        public Criteria andVersionOverviewLessThan(String value) {
            addCriterion("version_overview <", value, "versionOverview");
            return (Criteria) this;
        }

        public Criteria andVersionOverviewLessThanOrEqualTo(String value) {
            addCriterion("version_overview <=", value, "versionOverview");
            return (Criteria) this;
        }

        public Criteria andVersionOverviewLike(String value) {
            addCriterion("version_overview like", value, "versionOverview");
            return (Criteria) this;
        }

        public Criteria andVersionOverviewNotLike(String value) {
            addCriterion("version_overview not like", value, "versionOverview");
            return (Criteria) this;
        }

        public Criteria andVersionOverviewIn(List<String> values) {
            addCriterion("version_overview in", values, "versionOverview");
            return (Criteria) this;
        }

        public Criteria andVersionOverviewNotIn(List<String> values) {
            addCriterion("version_overview not in", values, "versionOverview");
            return (Criteria) this;
        }

        public Criteria andVersionOverviewBetween(String value1, String value2) {
            addCriterion("version_overview between", value1, value2, "versionOverview");
            return (Criteria) this;
        }

        public Criteria andVersionOverviewNotBetween(String value1, String value2) {
            addCriterion("version_overview not between", value1, value2, "versionOverview");
            return (Criteria) this;
        }

        public Criteria andVersionCompanyIsNull() {
            addCriterion("version_company is null");
            return (Criteria) this;
        }

        public Criteria andVersionCompanyIsNotNull() {
            addCriterion("version_company is not null");
            return (Criteria) this;
        }

        public Criteria andVersionCompanyEqualTo(String value) {
            addCriterion("version_company =", value, "versionCompany");
            return (Criteria) this;
        }

        public Criteria andVersionCompanyNotEqualTo(String value) {
            addCriterion("version_company <>", value, "versionCompany");
            return (Criteria) this;
        }

        public Criteria andVersionCompanyGreaterThan(String value) {
            addCriterion("version_company >", value, "versionCompany");
            return (Criteria) this;
        }

        public Criteria andVersionCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("version_company >=", value, "versionCompany");
            return (Criteria) this;
        }

        public Criteria andVersionCompanyLessThan(String value) {
            addCriterion("version_company <", value, "versionCompany");
            return (Criteria) this;
        }

        public Criteria andVersionCompanyLessThanOrEqualTo(String value) {
            addCriterion("version_company <=", value, "versionCompany");
            return (Criteria) this;
        }

        public Criteria andVersionCompanyLike(String value) {
            addCriterion("version_company like", value, "versionCompany");
            return (Criteria) this;
        }

        public Criteria andVersionCompanyNotLike(String value) {
            addCriterion("version_company not like", value, "versionCompany");
            return (Criteria) this;
        }

        public Criteria andVersionCompanyIn(List<String> values) {
            addCriterion("version_company in", values, "versionCompany");
            return (Criteria) this;
        }

        public Criteria andVersionCompanyNotIn(List<String> values) {
            addCriterion("version_company not in", values, "versionCompany");
            return (Criteria) this;
        }

        public Criteria andVersionCompanyBetween(String value1, String value2) {
            addCriterion("version_company between", value1, value2, "versionCompany");
            return (Criteria) this;
        }

        public Criteria andVersionCompanyNotBetween(String value1, String value2) {
            addCriterion("version_company not between", value1, value2, "versionCompany");
            return (Criteria) this;
        }

        public Criteria andVersionDeveloperIsNull() {
            addCriterion("version_developer is null");
            return (Criteria) this;
        }

        public Criteria andVersionDeveloperIsNotNull() {
            addCriterion("version_developer is not null");
            return (Criteria) this;
        }

        public Criteria andVersionDeveloperEqualTo(String value) {
            addCriterion("version_developer =", value, "versionDeveloper");
            return (Criteria) this;
        }

        public Criteria andVersionDeveloperNotEqualTo(String value) {
            addCriterion("version_developer <>", value, "versionDeveloper");
            return (Criteria) this;
        }

        public Criteria andVersionDeveloperGreaterThan(String value) {
            addCriterion("version_developer >", value, "versionDeveloper");
            return (Criteria) this;
        }

        public Criteria andVersionDeveloperGreaterThanOrEqualTo(String value) {
            addCriterion("version_developer >=", value, "versionDeveloper");
            return (Criteria) this;
        }

        public Criteria andVersionDeveloperLessThan(String value) {
            addCriterion("version_developer <", value, "versionDeveloper");
            return (Criteria) this;
        }

        public Criteria andVersionDeveloperLessThanOrEqualTo(String value) {
            addCriterion("version_developer <=", value, "versionDeveloper");
            return (Criteria) this;
        }

        public Criteria andVersionDeveloperLike(String value) {
            addCriterion("version_developer like", value, "versionDeveloper");
            return (Criteria) this;
        }

        public Criteria andVersionDeveloperNotLike(String value) {
            addCriterion("version_developer not like", value, "versionDeveloper");
            return (Criteria) this;
        }

        public Criteria andVersionDeveloperIn(List<String> values) {
            addCriterion("version_developer in", values, "versionDeveloper");
            return (Criteria) this;
        }

        public Criteria andVersionDeveloperNotIn(List<String> values) {
            addCriterion("version_developer not in", values, "versionDeveloper");
            return (Criteria) this;
        }

        public Criteria andVersionDeveloperBetween(String value1, String value2) {
            addCriterion("version_developer between", value1, value2, "versionDeveloper");
            return (Criteria) this;
        }

        public Criteria andVersionDeveloperNotBetween(String value1, String value2) {
            addCriterion("version_developer not between", value1, value2, "versionDeveloper");
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