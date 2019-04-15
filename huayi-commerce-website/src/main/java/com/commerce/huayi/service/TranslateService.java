package com.commerce.huayi.service;

import org.springframework.beans.factory.InitializingBean;

public interface TranslateService extends InitializingBean {
    //原表中需要翻译的字段对应的翻译表中的翻译字段名后缀 例如category_name -> category_name_translate

    Object  translate(Object object) ;
}
