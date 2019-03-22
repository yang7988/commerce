package com.commerce.huayi.entity.db;

import java.io.Serializable;

public class TranslateEntity implements Serializable {

    private String tableName;

    private String translateResult;

    public String getTranslateResult() {
        return translateResult;
    }

    public void setTranslateResult(String translateResult) {
        this.translateResult = translateResult;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}