package com.commerce.huayi.entity.db;

import java.io.Serializable;
import java.util.Date;

public class TranslateEntity implements Serializable {

    private String translateResult;

    public String getTranslateResult() {
        return translateResult;
    }

    public void setTranslateResult(String translateResult) {
        this.translateResult = translateResult;
    }
}