package com.commerce.huayi.service;

public interface TranslateService {
    //原表中需要翻译的字段对应的翻译表中的翻译字段名后缀 例如category_name -> category_name_translate
    String TRANSLATE_FIELD_SUFFIX = "_translate";
    String TRANSLATE_API_RESPONSE_TABLE_PREFIX = "tb_api_response";
    String TRANSLATE_API_RESPONSE_COLUMN = "code";

    Object  translate(Object object) ;
}
