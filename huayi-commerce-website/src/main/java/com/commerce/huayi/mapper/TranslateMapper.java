package com.commerce.huayi.mapper;


import com.commerce.huayi.entity.db.TranslateEntity;
import com.commerce.huayi.entity.db.TranslateEntityExample;

public interface TranslateMapper {
    TranslateEntity selectByKey(TranslateEntityExample entityExample);
}