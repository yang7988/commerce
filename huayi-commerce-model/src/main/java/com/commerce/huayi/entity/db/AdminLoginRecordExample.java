package com.commerce.huayi.entity.db;

import com.commerce.huayi.pagination.Limit;
import com.commerce.huayi.pagination.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminLoginRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = 0;

    protected int limitEnd = 0;

    protected Limit limit;

    protected Page page;

    public AdminLoginRecordExample() {
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

    public void setLimitStart(int limitStart) {
        this.limitStart=limitStart;
    }

    public int getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(int limitEnd) {
        this.limitEnd=limitEnd;
    }

    public int getLimitEnd() {
        return limitEnd;
    }

    public void setLimit(Limit limit) {
        this.limit=limit;
    }

    public Limit getLimit() {
        return limit;
    }

    public void setPage(Page page) {
        this.page=page;
    }

    public Page getPage() {
        return page;
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLoginUserIsNull() {
            addCriterion("login_user is null");
            return (Criteria) this;
        }

        public Criteria andLoginUserIsNotNull() {
            addCriterion("login_user is not null");
            return (Criteria) this;
        }

        public Criteria andLoginUserEqualTo(String value) {
            addCriterion("login_user =", value, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserNotEqualTo(String value) {
            addCriterion("login_user <>", value, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserGreaterThan(String value) {
            addCriterion("login_user >", value, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserGreaterThanOrEqualTo(String value) {
            addCriterion("login_user >=", value, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserLessThan(String value) {
            addCriterion("login_user <", value, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserLessThanOrEqualTo(String value) {
            addCriterion("login_user <=", value, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserLike(String value) {
            addCriterion("login_user like", value, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserNotLike(String value) {
            addCriterion("login_user not like", value, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserIn(List<String> values) {
            addCriterion("login_user in", values, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserNotIn(List<String> values) {
            addCriterion("login_user not in", values, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserBetween(String value1, String value2) {
            addCriterion("login_user between", value1, value2, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginUserNotBetween(String value1, String value2) {
            addCriterion("login_user not between", value1, value2, "loginUser");
            return (Criteria) this;
        }

        public Criteria andLoginKeyIsNull() {
            addCriterion("login_key is null");
            return (Criteria) this;
        }

        public Criteria andLoginKeyIsNotNull() {
            addCriterion("login_key is not null");
            return (Criteria) this;
        }

        public Criteria andLoginKeyEqualTo(String value) {
            addCriterion("login_key =", value, "loginKey");
            return (Criteria) this;
        }

        public Criteria andLoginKeyNotEqualTo(String value) {
            addCriterion("login_key <>", value, "loginKey");
            return (Criteria) this;
        }

        public Criteria andLoginKeyGreaterThan(String value) {
            addCriterion("login_key >", value, "loginKey");
            return (Criteria) this;
        }

        public Criteria andLoginKeyGreaterThanOrEqualTo(String value) {
            addCriterion("login_key >=", value, "loginKey");
            return (Criteria) this;
        }

        public Criteria andLoginKeyLessThan(String value) {
            addCriterion("login_key <", value, "loginKey");
            return (Criteria) this;
        }

        public Criteria andLoginKeyLessThanOrEqualTo(String value) {
            addCriterion("login_key <=", value, "loginKey");
            return (Criteria) this;
        }

        public Criteria andLoginKeyLike(String value) {
            addCriterion("login_key like", value, "loginKey");
            return (Criteria) this;
        }

        public Criteria andLoginKeyNotLike(String value) {
            addCriterion("login_key not like", value, "loginKey");
            return (Criteria) this;
        }

        public Criteria andLoginKeyIn(List<String> values) {
            addCriterion("login_key in", values, "loginKey");
            return (Criteria) this;
        }

        public Criteria andLoginKeyNotIn(List<String> values) {
            addCriterion("login_key not in", values, "loginKey");
            return (Criteria) this;
        }

        public Criteria andLoginKeyBetween(String value1, String value2) {
            addCriterion("login_key between", value1, value2, "loginKey");
            return (Criteria) this;
        }

        public Criteria andLoginKeyNotBetween(String value1, String value2) {
            addCriterion("login_key not between", value1, value2, "loginKey");
            return (Criteria) this;
        }

        public Criteria andLoginAddIsNull() {
            addCriterion("login_add is null");
            return (Criteria) this;
        }

        public Criteria andLoginAddIsNotNull() {
            addCriterion("login_add is not null");
            return (Criteria) this;
        }

        public Criteria andLoginAddEqualTo(String value) {
            addCriterion("login_add =", value, "loginAdd");
            return (Criteria) this;
        }

        public Criteria andLoginAddNotEqualTo(String value) {
            addCriterion("login_add <>", value, "loginAdd");
            return (Criteria) this;
        }

        public Criteria andLoginAddGreaterThan(String value) {
            addCriterion("login_add >", value, "loginAdd");
            return (Criteria) this;
        }

        public Criteria andLoginAddGreaterThanOrEqualTo(String value) {
            addCriterion("login_add >=", value, "loginAdd");
            return (Criteria) this;
        }

        public Criteria andLoginAddLessThan(String value) {
            addCriterion("login_add <", value, "loginAdd");
            return (Criteria) this;
        }

        public Criteria andLoginAddLessThanOrEqualTo(String value) {
            addCriterion("login_add <=", value, "loginAdd");
            return (Criteria) this;
        }

        public Criteria andLoginAddLike(String value) {
            addCriterion("login_add like", value, "loginAdd");
            return (Criteria) this;
        }

        public Criteria andLoginAddNotLike(String value) {
            addCriterion("login_add not like", value, "loginAdd");
            return (Criteria) this;
        }

        public Criteria andLoginAddIn(List<String> values) {
            addCriterion("login_add in", values, "loginAdd");
            return (Criteria) this;
        }

        public Criteria andLoginAddNotIn(List<String> values) {
            addCriterion("login_add not in", values, "loginAdd");
            return (Criteria) this;
        }

        public Criteria andLoginAddBetween(String value1, String value2) {
            addCriterion("login_add between", value1, value2, "loginAdd");
            return (Criteria) this;
        }

        public Criteria andLoginAddNotBetween(String value1, String value2) {
            addCriterion("login_add not between", value1, value2, "loginAdd");
            return (Criteria) this;
        }

        public Criteria andLoginInDateIsNull() {
            addCriterion("login_in_date is null");
            return (Criteria) this;
        }

        public Criteria andLoginInDateIsNotNull() {
            addCriterion("login_in_date is not null");
            return (Criteria) this;
        }

        public Criteria andLoginInDateEqualTo(Date value) {
            addCriterion("login_in_date =", value, "loginInDate");
            return (Criteria) this;
        }

        public Criteria andLoginInDateNotEqualTo(Date value) {
            addCriterion("login_in_date <>", value, "loginInDate");
            return (Criteria) this;
        }

        public Criteria andLoginInDateGreaterThan(Date value) {
            addCriterion("login_in_date >", value, "loginInDate");
            return (Criteria) this;
        }

        public Criteria andLoginInDateGreaterThanOrEqualTo(Date value) {
            addCriterion("login_in_date >=", value, "loginInDate");
            return (Criteria) this;
        }

        public Criteria andLoginInDateLessThan(Date value) {
            addCriterion("login_in_date <", value, "loginInDate");
            return (Criteria) this;
        }

        public Criteria andLoginInDateLessThanOrEqualTo(Date value) {
            addCriterion("login_in_date <=", value, "loginInDate");
            return (Criteria) this;
        }

        public Criteria andLoginInDateIn(List<Date> values) {
            addCriterion("login_in_date in", values, "loginInDate");
            return (Criteria) this;
        }

        public Criteria andLoginInDateNotIn(List<Date> values) {
            addCriterion("login_in_date not in", values, "loginInDate");
            return (Criteria) this;
        }

        public Criteria andLoginInDateBetween(Date value1, Date value2) {
            addCriterion("login_in_date between", value1, value2, "loginInDate");
            return (Criteria) this;
        }

        public Criteria andLoginInDateNotBetween(Date value1, Date value2) {
            addCriterion("login_in_date not between", value1, value2, "loginInDate");
            return (Criteria) this;
        }

        public Criteria andLoginOutDateIsNull() {
            addCriterion("login_out_date is null");
            return (Criteria) this;
        }

        public Criteria andLoginOutDateIsNotNull() {
            addCriterion("login_out_date is not null");
            return (Criteria) this;
        }

        public Criteria andLoginOutDateEqualTo(Date value) {
            addCriterion("login_out_date =", value, "loginOutDate");
            return (Criteria) this;
        }

        public Criteria andLoginOutDateNotEqualTo(Date value) {
            addCriterion("login_out_date <>", value, "loginOutDate");
            return (Criteria) this;
        }

        public Criteria andLoginOutDateGreaterThan(Date value) {
            addCriterion("login_out_date >", value, "loginOutDate");
            return (Criteria) this;
        }

        public Criteria andLoginOutDateGreaterThanOrEqualTo(Date value) {
            addCriterion("login_out_date >=", value, "loginOutDate");
            return (Criteria) this;
        }

        public Criteria andLoginOutDateLessThan(Date value) {
            addCriterion("login_out_date <", value, "loginOutDate");
            return (Criteria) this;
        }

        public Criteria andLoginOutDateLessThanOrEqualTo(Date value) {
            addCriterion("login_out_date <=", value, "loginOutDate");
            return (Criteria) this;
        }

        public Criteria andLoginOutDateIn(List<Date> values) {
            addCriterion("login_out_date in", values, "loginOutDate");
            return (Criteria) this;
        }

        public Criteria andLoginOutDateNotIn(List<Date> values) {
            addCriterion("login_out_date not in", values, "loginOutDate");
            return (Criteria) this;
        }

        public Criteria andLoginOutDateBetween(Date value1, Date value2) {
            addCriterion("login_out_date between", value1, value2, "loginOutDate");
            return (Criteria) this;
        }

        public Criteria andLoginOutDateNotBetween(Date value1, Date value2) {
            addCriterion("login_out_date not between", value1, value2, "loginOutDate");
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