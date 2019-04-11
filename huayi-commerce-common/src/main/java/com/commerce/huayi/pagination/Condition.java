package com.commerce.huayi.pagination;


import java.util.HashMap;
import java.util.Map;

public class Condition{

    private Integer offset = 0;

    private Integer rowSize = 0;

    private Map<String, Object> criterion = new HashMap<>();

    private Condition() {

    }

    private Condition(Integer offset,Integer rowSize) {
        this.offset = offset;
        this.rowSize = rowSize;
    }

    public static Condition create() {
        return new Condition();
    }

    public static Condition create(Integer offset,Integer rowSize) {
        return new Condition(offset,rowSize);
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getRowSize() {
        return rowSize;
    }

    public void setRowSize(Integer rowSize) {
        this.rowSize = rowSize;
    }

    public Map<String, Object> getCriterion() {
        return criterion;
    }

    public void setCriterion(Map<String, Object> criterion) {
        this.criterion = criterion;
    }
}