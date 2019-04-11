package com.commerce.huayi.entity.request;

import java.util.Map;

public abstract class AbstractDictReq {

    public abstract Map<String,String> buildSql(String language);
}