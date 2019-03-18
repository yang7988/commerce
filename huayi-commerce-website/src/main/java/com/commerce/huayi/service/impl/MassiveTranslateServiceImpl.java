package com.commerce.huayi.service.impl;

import com.commerce.huayi.mapper.TranslateMapper;
import com.commerce.huayi.service.TranslateService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("massiveTranslateService")
public class MassiveTranslateServiceImpl implements TranslateService, InitializingBean {

    @Autowired
    private TranslateMapper translateMapper;

    @Override
    public Object translate(Object object) {
        return object;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        translateMapper.selectDict()
    }
}