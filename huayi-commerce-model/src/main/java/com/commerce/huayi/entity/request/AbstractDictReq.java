package com.commerce.huayi.entity.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public abstract class AbstractDictReq {

    @JsonIgnore
    private Long dictId;

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public abstract Map<String,Object> buildSql(String language);
}