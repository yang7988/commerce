package com.commerce.huayi.mapper;


import com.commerce.huayi.entity.db.TranslateEntity;
import com.commerce.huayi.entity.db.TranslateEntityExample;

import java.util.List;

public interface TranslateMapper {
    List<TranslateEntity> selectByKey(TranslateEntityExample entityExample);
}