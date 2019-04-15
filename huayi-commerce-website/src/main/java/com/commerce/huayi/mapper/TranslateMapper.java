package com.commerce.huayi.mapper;


import com.commerce.huayi.entity.db.TranslateEntity;
import com.commerce.huayi.entity.db.TranslateEntityExample;

import java.util.List;
import java.util.Map;

public interface TranslateMapper {
    List<TranslateEntity> selectByKey(TranslateEntityExample entityExample);

    int updateByKey(TranslateEntityExample entityExample);

    List<Map<String, String>> selectDict(TranslateEntityExample entityExample);

    List<TranslateEntity> selectAllTables(String tableSchema);

    int updateTranslate(Map<String,String> map);
}