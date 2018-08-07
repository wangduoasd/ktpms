package com.kaituo.pms.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class p_taskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public p_taskExample() {
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

        public Criteria andTaskIdIsNull() {
            addCriterion("task_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("task_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(Integer value) {
            addCriterion("task_id =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(Integer value) {
            addCriterion("task_id <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(Integer value) {
            addCriterion("task_id >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_id >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(Integer value) {
            addCriterion("task_id <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(Integer value) {
            addCriterion("task_id <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<Integer> values) {
            addCriterion("task_id in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<Integer> values) {
            addCriterion("task_id not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(Integer value1, Integer value2) {
            addCriterion("task_id between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(Integer value1, Integer value2) {
            addCriterion("task_id not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNull() {
            addCriterion("task_name is null");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNotNull() {
            addCriterion("task_name is not null");
            return (Criteria) this;
        }

        public Criteria andTaskNameEqualTo(String value) {
            addCriterion("task_name =", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotEqualTo(String value) {
            addCriterion("task_name <>", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThan(String value) {
            addCriterion("task_name >", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThanOrEqualTo(String value) {
            addCriterion("task_name >=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThan(String value) {
            addCriterion("task_name <", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThanOrEqualTo(String value) {
            addCriterion("task_name <=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLike(String value) {
            addCriterion("task_name like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotLike(String value) {
            addCriterion("task_name not like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameIn(List<String> values) {
            addCriterion("task_name in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotIn(List<String> values) {
            addCriterion("task_name not in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameBetween(String value1, String value2) {
            addCriterion("task_name between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotBetween(String value1, String value2) {
            addCriterion("task_name not between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskDifficultyIsNull() {
            addCriterion("task_difficulty is null");
            return (Criteria) this;
        }

        public Criteria andTaskDifficultyIsNotNull() {
            addCriterion("task_difficulty is not null");
            return (Criteria) this;
        }

        public Criteria andTaskDifficultyEqualTo(Integer value) {
            addCriterion("task_difficulty =", value, "taskDifficulty");
            return (Criteria) this;
        }

        public Criteria andTaskDifficultyNotEqualTo(Integer value) {
            addCriterion("task_difficulty <>", value, "taskDifficulty");
            return (Criteria) this;
        }

        public Criteria andTaskDifficultyGreaterThan(Integer value) {
            addCriterion("task_difficulty >", value, "taskDifficulty");
            return (Criteria) this;
        }

        public Criteria andTaskDifficultyGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_difficulty >=", value, "taskDifficulty");
            return (Criteria) this;
        }

        public Criteria andTaskDifficultyLessThan(Integer value) {
            addCriterion("task_difficulty <", value, "taskDifficulty");
            return (Criteria) this;
        }

        public Criteria andTaskDifficultyLessThanOrEqualTo(Integer value) {
            addCriterion("task_difficulty <=", value, "taskDifficulty");
            return (Criteria) this;
        }

        public Criteria andTaskDifficultyIn(List<Integer> values) {
            addCriterion("task_difficulty in", values, "taskDifficulty");
            return (Criteria) this;
        }

        public Criteria andTaskDifficultyNotIn(List<Integer> values) {
            addCriterion("task_difficulty not in", values, "taskDifficulty");
            return (Criteria) this;
        }

        public Criteria andTaskDifficultyBetween(Integer value1, Integer value2) {
            addCriterion("task_difficulty between", value1, value2, "taskDifficulty");
            return (Criteria) this;
        }

        public Criteria andTaskDifficultyNotBetween(Integer value1, Integer value2) {
            addCriterion("task_difficulty not between", value1, value2, "taskDifficulty");
            return (Criteria) this;
        }

        public Criteria andTaskPriceIsNull() {
            addCriterion("task_price is null");
            return (Criteria) this;
        }

        public Criteria andTaskPriceIsNotNull() {
            addCriterion("task_price is not null");
            return (Criteria) this;
        }

        public Criteria andTaskPriceEqualTo(Integer value) {
            addCriterion("task_price =", value, "taskPrice");
            return (Criteria) this;
        }

        public Criteria andTaskPriceNotEqualTo(Integer value) {
            addCriterion("task_price <>", value, "taskPrice");
            return (Criteria) this;
        }

        public Criteria andTaskPriceGreaterThan(Integer value) {
            addCriterion("task_price >", value, "taskPrice");
            return (Criteria) this;
        }

        public Criteria andTaskPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_price >=", value, "taskPrice");
            return (Criteria) this;
        }

        public Criteria andTaskPriceLessThan(Integer value) {
            addCriterion("task_price <", value, "taskPrice");
            return (Criteria) this;
        }

        public Criteria andTaskPriceLessThanOrEqualTo(Integer value) {
            addCriterion("task_price <=", value, "taskPrice");
            return (Criteria) this;
        }

        public Criteria andTaskPriceIn(List<Integer> values) {
            addCriterion("task_price in", values, "taskPrice");
            return (Criteria) this;
        }

        public Criteria andTaskPriceNotIn(List<Integer> values) {
            addCriterion("task_price not in", values, "taskPrice");
            return (Criteria) this;
        }

        public Criteria andTaskPriceBetween(Integer value1, Integer value2) {
            addCriterion("task_price between", value1, value2, "taskPrice");
            return (Criteria) this;
        }

        public Criteria andTaskPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("task_price not between", value1, value2, "taskPrice");
            return (Criteria) this;
        }

        public Criteria andTaskAwardIsNull() {
            addCriterion("task_award is null");
            return (Criteria) this;
        }

        public Criteria andTaskAwardIsNotNull() {
            addCriterion("task_award is not null");
            return (Criteria) this;
        }

        public Criteria andTaskAwardEqualTo(Integer value) {
            addCriterion("task_award =", value, "taskAward");
            return (Criteria) this;
        }

        public Criteria andTaskAwardNotEqualTo(Integer value) {
            addCriterion("task_award <>", value, "taskAward");
            return (Criteria) this;
        }

        public Criteria andTaskAwardGreaterThan(Integer value) {
            addCriterion("task_award >", value, "taskAward");
            return (Criteria) this;
        }

        public Criteria andTaskAwardGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_award >=", value, "taskAward");
            return (Criteria) this;
        }

        public Criteria andTaskAwardLessThan(Integer value) {
            addCriterion("task_award <", value, "taskAward");
            return (Criteria) this;
        }

        public Criteria andTaskAwardLessThanOrEqualTo(Integer value) {
            addCriterion("task_award <=", value, "taskAward");
            return (Criteria) this;
        }

        public Criteria andTaskAwardIn(List<Integer> values) {
            addCriterion("task_award in", values, "taskAward");
            return (Criteria) this;
        }

        public Criteria andTaskAwardNotIn(List<Integer> values) {
            addCriterion("task_award not in", values, "taskAward");
            return (Criteria) this;
        }

        public Criteria andTaskAwardBetween(Integer value1, Integer value2) {
            addCriterion("task_award between", value1, value2, "taskAward");
            return (Criteria) this;
        }

        public Criteria andTaskAwardNotBetween(Integer value1, Integer value2) {
            addCriterion("task_award not between", value1, value2, "taskAward");
            return (Criteria) this;
        }

        public Criteria andTaskNumberIsNull() {
            addCriterion("task_number is null");
            return (Criteria) this;
        }

        public Criteria andTaskNumberIsNotNull() {
            addCriterion("task_number is not null");
            return (Criteria) this;
        }

        public Criteria andTaskNumberEqualTo(Integer value) {
            addCriterion("task_number =", value, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andTaskNumberNotEqualTo(Integer value) {
            addCriterion("task_number <>", value, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andTaskNumberGreaterThan(Integer value) {
            addCriterion("task_number >", value, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andTaskNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_number >=", value, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andTaskNumberLessThan(Integer value) {
            addCriterion("task_number <", value, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andTaskNumberLessThanOrEqualTo(Integer value) {
            addCriterion("task_number <=", value, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andTaskNumberIn(List<Integer> values) {
            addCriterion("task_number in", values, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andTaskNumberNotIn(List<Integer> values) {
            addCriterion("task_number not in", values, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andTaskNumberBetween(Integer value1, Integer value2) {
            addCriterion("task_number between", value1, value2, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andTaskNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("task_number not between", value1, value2, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andTaskTimeIsNull() {
            addCriterion("task_time is null");
            return (Criteria) this;
        }

        public Criteria andTaskTimeIsNotNull() {
            addCriterion("task_time is not null");
            return (Criteria) this;
        }

        public Criteria andTaskTimeEqualTo(Integer value) {
            addCriterion("task_time =", value, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeNotEqualTo(Integer value) {
            addCriterion("task_time <>", value, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeGreaterThan(Integer value) {
            addCriterion("task_time >", value, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_time >=", value, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeLessThan(Integer value) {
            addCriterion("task_time <", value, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeLessThanOrEqualTo(Integer value) {
            addCriterion("task_time <=", value, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeIn(List<Integer> values) {
            addCriterion("task_time in", values, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeNotIn(List<Integer> values) {
            addCriterion("task_time not in", values, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeBetween(Integer value1, Integer value2) {
            addCriterion("task_time between", value1, value2, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("task_time not between", value1, value2, "taskTime");
            return (Criteria) this;
        }

        public Criteria andTaskStarttimeIsNull() {
            addCriterion("task_starttime is null");
            return (Criteria) this;
        }

        public Criteria andTaskStarttimeIsNotNull() {
            addCriterion("task_starttime is not null");
            return (Criteria) this;
        }

        public Criteria andTaskStarttimeEqualTo(Date value) {
            addCriterionForJDBCDate("task_starttime =", value, "taskStarttime");
            return (Criteria) this;
        }

        public Criteria andTaskStarttimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("task_starttime <>", value, "taskStarttime");
            return (Criteria) this;
        }

        public Criteria andTaskStarttimeGreaterThan(Date value) {
            addCriterionForJDBCDate("task_starttime >", value, "taskStarttime");
            return (Criteria) this;
        }

        public Criteria andTaskStarttimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("task_starttime >=", value, "taskStarttime");
            return (Criteria) this;
        }

        public Criteria andTaskStarttimeLessThan(Date value) {
            addCriterionForJDBCDate("task_starttime <", value, "taskStarttime");
            return (Criteria) this;
        }

        public Criteria andTaskStarttimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("task_starttime <=", value, "taskStarttime");
            return (Criteria) this;
        }

        public Criteria andTaskStarttimeIn(List<Date> values) {
            addCriterionForJDBCDate("task_starttime in", values, "taskStarttime");
            return (Criteria) this;
        }

        public Criteria andTaskStarttimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("task_starttime not in", values, "taskStarttime");
            return (Criteria) this;
        }

        public Criteria andTaskStarttimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("task_starttime between", value1, value2, "taskStarttime");
            return (Criteria) this;
        }

        public Criteria andTaskStarttimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("task_starttime not between", value1, value2, "taskStarttime");
            return (Criteria) this;
        }

        public Criteria andTaskEndtimeIsNull() {
            addCriterion("task_endtime is null");
            return (Criteria) this;
        }

        public Criteria andTaskEndtimeIsNotNull() {
            addCriterion("task_endtime is not null");
            return (Criteria) this;
        }

        public Criteria andTaskEndtimeEqualTo(Date value) {
            addCriterionForJDBCDate("task_endtime =", value, "taskEndtime");
            return (Criteria) this;
        }

        public Criteria andTaskEndtimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("task_endtime <>", value, "taskEndtime");
            return (Criteria) this;
        }

        public Criteria andTaskEndtimeGreaterThan(Date value) {
            addCriterionForJDBCDate("task_endtime >", value, "taskEndtime");
            return (Criteria) this;
        }

        public Criteria andTaskEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("task_endtime >=", value, "taskEndtime");
            return (Criteria) this;
        }

        public Criteria andTaskEndtimeLessThan(Date value) {
            addCriterionForJDBCDate("task_endtime <", value, "taskEndtime");
            return (Criteria) this;
        }

        public Criteria andTaskEndtimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("task_endtime <=", value, "taskEndtime");
            return (Criteria) this;
        }

        public Criteria andTaskEndtimeIn(List<Date> values) {
            addCriterionForJDBCDate("task_endtime in", values, "taskEndtime");
            return (Criteria) this;
        }

        public Criteria andTaskEndtimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("task_endtime not in", values, "taskEndtime");
            return (Criteria) this;
        }

        public Criteria andTaskEndtimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("task_endtime between", value1, value2, "taskEndtime");
            return (Criteria) this;
        }

        public Criteria andTaskEndtimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("task_endtime not between", value1, value2, "taskEndtime");
            return (Criteria) this;
        }

        public Criteria andTaskImageIsNull() {
            addCriterion("task_image is null");
            return (Criteria) this;
        }

        public Criteria andTaskImageIsNotNull() {
            addCriterion("task_image is not null");
            return (Criteria) this;
        }

        public Criteria andTaskImageEqualTo(String value) {
            addCriterion("task_image =", value, "taskImage");
            return (Criteria) this;
        }

        public Criteria andTaskImageNotEqualTo(String value) {
            addCriterion("task_image <>", value, "taskImage");
            return (Criteria) this;
        }

        public Criteria andTaskImageGreaterThan(String value) {
            addCriterion("task_image >", value, "taskImage");
            return (Criteria) this;
        }

        public Criteria andTaskImageGreaterThanOrEqualTo(String value) {
            addCriterion("task_image >=", value, "taskImage");
            return (Criteria) this;
        }

        public Criteria andTaskImageLessThan(String value) {
            addCriterion("task_image <", value, "taskImage");
            return (Criteria) this;
        }

        public Criteria andTaskImageLessThanOrEqualTo(String value) {
            addCriterion("task_image <=", value, "taskImage");
            return (Criteria) this;
        }

        public Criteria andTaskImageLike(String value) {
            addCriterion("task_image like", value, "taskImage");
            return (Criteria) this;
        }

        public Criteria andTaskImageNotLike(String value) {
            addCriterion("task_image not like", value, "taskImage");
            return (Criteria) this;
        }

        public Criteria andTaskImageIn(List<String> values) {
            addCriterion("task_image in", values, "taskImage");
            return (Criteria) this;
        }

        public Criteria andTaskImageNotIn(List<String> values) {
            addCriterion("task_image not in", values, "taskImage");
            return (Criteria) this;
        }

        public Criteria andTaskImageBetween(String value1, String value2) {
            addCriterion("task_image between", value1, value2, "taskImage");
            return (Criteria) this;
        }

        public Criteria andTaskImageNotBetween(String value1, String value2) {
            addCriterion("task_image not between", value1, value2, "taskImage");
            return (Criteria) this;
        }

        public Criteria andTaskDescribeIsNull() {
            addCriterion("task_describe is null");
            return (Criteria) this;
        }

        public Criteria andTaskDescribeIsNotNull() {
            addCriterion("task_describe is not null");
            return (Criteria) this;
        }

        public Criteria andTaskDescribeEqualTo(String value) {
            addCriterion("task_describe =", value, "taskDescribe");
            return (Criteria) this;
        }

        public Criteria andTaskDescribeNotEqualTo(String value) {
            addCriterion("task_describe <>", value, "taskDescribe");
            return (Criteria) this;
        }

        public Criteria andTaskDescribeGreaterThan(String value) {
            addCriterion("task_describe >", value, "taskDescribe");
            return (Criteria) this;
        }

        public Criteria andTaskDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("task_describe >=", value, "taskDescribe");
            return (Criteria) this;
        }

        public Criteria andTaskDescribeLessThan(String value) {
            addCriterion("task_describe <", value, "taskDescribe");
            return (Criteria) this;
        }

        public Criteria andTaskDescribeLessThanOrEqualTo(String value) {
            addCriterion("task_describe <=", value, "taskDescribe");
            return (Criteria) this;
        }

        public Criteria andTaskDescribeLike(String value) {
            addCriterion("task_describe like", value, "taskDescribe");
            return (Criteria) this;
        }

        public Criteria andTaskDescribeNotLike(String value) {
            addCriterion("task_describe not like", value, "taskDescribe");
            return (Criteria) this;
        }

        public Criteria andTaskDescribeIn(List<String> values) {
            addCriterion("task_describe in", values, "taskDescribe");
            return (Criteria) this;
        }

        public Criteria andTaskDescribeNotIn(List<String> values) {
            addCriterion("task_describe not in", values, "taskDescribe");
            return (Criteria) this;
        }

        public Criteria andTaskDescribeBetween(String value1, String value2) {
            addCriterion("task_describe between", value1, value2, "taskDescribe");
            return (Criteria) this;
        }

        public Criteria andTaskDescribeNotBetween(String value1, String value2) {
            addCriterion("task_describe not between", value1, value2, "taskDescribe");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIsNull() {
            addCriterion("task_status is null");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIsNotNull() {
            addCriterion("task_status is not null");
            return (Criteria) this;
        }

        public Criteria andTaskStatusEqualTo(Integer value) {
            addCriterion("task_status =", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotEqualTo(Integer value) {
            addCriterion("task_status <>", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusGreaterThan(Integer value) {
            addCriterion("task_status >", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_status >=", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLessThan(Integer value) {
            addCriterion("task_status <", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLessThanOrEqualTo(Integer value) {
            addCriterion("task_status <=", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIn(List<Integer> values) {
            addCriterion("task_status in", values, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotIn(List<Integer> values) {
            addCriterion("task_status not in", values, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusBetween(Integer value1, Integer value2) {
            addCriterion("task_status between", value1, value2, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("task_status not between", value1, value2, "taskStatus");
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

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
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