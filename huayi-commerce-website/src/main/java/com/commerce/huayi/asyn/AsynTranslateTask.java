package com.commerce.huayi.asyn;

import com.commerce.huayi.constant.LanguageEnum;
import com.commerce.huayi.entity.request.AbstractDictReq;
import com.commerce.huayi.service.TranslateService;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AsynTranslateTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsynTranslateTask.class);

    private Set<String> languages;
    private AbstractDictReq dictReq;
    private TranslateService translateService;


    public AsynTranslateTask(AbstractDictReq dictReq, TranslateService translateService, Set<String> languages) {
        this.dictReq = dictReq;
        this.translateService = translateService;
        this.languages = languages;
    }

    @Override
    public void run() {
        //增加产品字典翻译
        List<String> languages = Stream.of(LanguageEnum.values()).map(LanguageEnum::getLanguage).collect(Collectors.toList());
        for (String language : languages) {
            Map<String, Object> objectMap = dictReq.buildSql(language);
            if (MapUtils.isNotEmpty(objectMap)) {
                translateService.addTranslate(objectMap);
            }
        }

    }
}