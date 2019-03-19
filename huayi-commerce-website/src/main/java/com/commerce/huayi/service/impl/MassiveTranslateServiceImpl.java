package com.commerce.huayi.service.impl;

import com.commerce.huayi.cache.JedisTemplate;
import com.commerce.huayi.cache.key.RedisKey;
import com.commerce.huayi.cache.key.RedisKeysPrefix;
import com.commerce.huayi.constant.LanguageEnum;
import com.commerce.huayi.entity.db.TranslateEntity;
import com.commerce.huayi.entity.db.TranslateEntityExample;
import com.commerce.huayi.mapper.TranslateMapper;
import com.commerce.huayi.service.TranslateService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("massiveTranslateService")
public class MassiveTranslateServiceImpl implements TranslateService, InitializingBean {

    @Autowired
    private TranslateMapper translateMapper;

    @Autowired
    private JedisTemplate jedisTemplate;

    @Override
    public Object translate(Object object) {
        return object;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Set<String> allDictTables = getAllDictTables();
        if(CollectionUtils.isEmpty(allDictTables)) {
            return;
        }
        for (String tableName : allDictTables) {
            TranslateEntityExample example = new TranslateEntityExample();
            example.setTableName(tableName);
            List<Map<String, String>> maps = translateMapper.selectDict(example);
            this.storeDict(tableName, maps);
        }
    }

    private void storeDict(String tableName, List<Map<String, String>> maps) {
        maps.forEach(map -> map.entrySet().forEach(entry -> storeDictToRedis(tableName, map, entry)));
    }

    private void storeDictToRedis(String tableName, Map<String, String> map, Map.Entry<String, String> entry) {
        String key = entry.getKey();
        if (StringUtils.isBlank(key) || !key.contains("_translate")) {
            return;
        }
        String translateKey = key.split("_translate")[0];
        if (StringUtils.isBlank(translateKey)) {
            return;
        }
        RedisKey redisKey = new RedisKey(RedisKeysPrefix.I18N_KEY, tableName);
        String translateKeyVal = map.get(translateKey);
        if(StringUtils.isBlank(translateKeyVal)) {
            return;
        }
        String hashField = key.concat(":").concat(translateKeyVal);
        String value = map.get(key);
        jedisTemplate.hset(redisKey, hashField, value);
    }


    private Set<String> getAllDictTables() {
        List<TranslateEntity> translateEntities = translateMapper.selectAllTables("huayi-commerce");
        if (CollectionUtils.isEmpty(translateEntities)) {
            return null;
        }
        Set<String> languages = Arrays.stream(LanguageEnum.values()).map(LanguageEnum::getLanguage).collect(Collectors.toSet());
        List<TranslateEntity> collect = translateEntities.stream()
                .filter(entity -> languages.contains(entity.getTableName()
                        .substring(entity.getTableName().lastIndexOf("_") + 1))).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(collect)) {
            return null;
        }
        return collect.stream().map(TranslateEntity::getTableName).collect(Collectors.toSet());
    }
}