package com.commerce.huayi.strategy.impl;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.service.TranslateService;
import com.commerce.huayi.strategy.TranslateStrategy;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
public class TranslateStrategyImpl implements TranslateStrategy {
    //需要被翻译的数据量规模阈值，超过此值将使用大规模翻译器massiveTranslateService
    private static final int MAX_TRANSLATE_SCALE = 100;
    @Autowired
    private TranslateService miniatureTranslateService;

    @Autowired
    private TranslateService massiveTranslateService;

    @Override
    public Object translate(Object requiredObject) {
        return chooseTranslator(requiredObject).translate(requiredObject);
    }


    private TranslateService chooseTranslator(Object requiredObject) {
        if (!(requiredObject instanceof ApiResponse)) {
            return miniatureTranslateService;
        }
        ApiResponse apiResponse = (ApiResponse) requiredObject;
        Object data = apiResponse.getData();
        if (Collection.class.isAssignableFrom(data.getClass())) {
            Collection collection = (Collection) data;
            return isReachScale(collection) ? massiveTranslateService : miniatureTranslateService;
        }
        if (Map.class.isAssignableFrom(data.getClass())) {
            Map map = (Map) data;
            Collection values = map.values();
            return isReachScale(values) ? massiveTranslateService : miniatureTranslateService;
        }
        return miniatureTranslateService;
    }

    private boolean isReachScale(Collection collection) {
        return CollectionUtils.isNotEmpty(collection) && collection.size() > MAX_TRANSLATE_SCALE;
    }
}