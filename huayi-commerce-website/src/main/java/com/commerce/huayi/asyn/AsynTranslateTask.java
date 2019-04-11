package com.commerce.huayi.asyn;

import com.commerce.huayi.constant.LanguageEnum;
import com.commerce.huayi.entity.request.AbstractDictReq;
import com.commerce.huayi.mapper.TranslateMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AsynTranslateTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsynTranslateTask.class);

    private AbstractDictReq dictReq;
    private TranslateMapper translateMapper;


    public AsynTranslateTask(AbstractDictReq dictReq, TranslateMapper translateMapper) {
        this.dictReq = dictReq;
        this.translateMapper = translateMapper;
    }

    @Override
    public void run() {
        //增加产品字典翻译
        List<String> languages = Stream.of(LanguageEnum.values()).map(LanguageEnum::getLanguage).collect(Collectors.toList());
        for (String language : languages) {
            try {
                Map<String, String> map = dictReq.buildSql(language);
                if(MapUtils.isEmpty(map)) {
                    continue;
                }
                for (String sqlKey : map.keySet()) {
                    Map<String, String> sqlMap = new HashMap<>(1);
                    sqlMap.put("sqlStatement", map.get(sqlKey));
                    translateMapper.insertTranslateDict(sqlMap);
                }
            } catch (Exception e) {
                LOGGER.error("=========AsynTranslateTask===新增翻译字典异常======{}", ExceptionUtils.getStackTrace(e));
            }
        }

    }
}