package com.commerce.huayi.entity.db;

import java.io.Serializable;

public class TranslateEntityExample implements Serializable {

    private String tableName;

    private String translateCloumn;

    private String translateVal;

    private String whereCloumn;

    private String key;

    public TranslateEntityExample() {
    }

    public TranslateEntityExample(String tableName,String translateCloumn, String whereCloumn, String key) {
        this.tableName = tableName;
        this.translateCloumn = translateCloumn;
        this.whereCloumn = whereCloumn;
        this.key = key;
    }

    public TranslateEntityExample(String tableName,String translateCloumn, String whereCloumn, String key,String translateVal) {
        this.tableName = tableName;
        this.translateCloumn = translateCloumn;
        this.whereCloumn = whereCloumn;
        this.key = key;
        this.translateVal = translateVal;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getWhereCloumn() {
        return whereCloumn;
    }

    public void setWhereCloumn(String whereCloumn) {
        this.whereCloumn = whereCloumn;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTranslateCloumn() {
        return translateCloumn;
    }

    public void setTranslateCloumn(String translateCloumn) {
        this.translateCloumn = translateCloumn;
    }

    public String getTranslateVal() {
        return translateVal;
    }

    public void setTranslateVal(String translateVal) {
        this.translateVal = translateVal;
    }
}