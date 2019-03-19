package com.commerce.huayi.service.impl;

import com.commerce.huayi.cache.JedisTemplate;
import com.commerce.huayi.cache.key.RedisKey;
import com.commerce.huayi.cache.key.RedisKeysPrefix;
import com.commerce.huayi.constant.Constant;
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
        allDictTables.forEach(this::selectDictByExample);
    }

    /**
     * 查询字典实体
     * @param tableName 翻译表表名
     */
    private void selectDictByExample(String tableName) {
        TranslateEntityExample example = new TranslateEntityExample();
        example.setTableName(tableName);
        List<Map<String, String>> objectList = translateMapper.selectDict(example);
        if(CollectionUtils.isEmpty(objectList)) {
            return;
        }
        this.storeDict(tableName, objectList);
    }

    /**
     * 迭代存储
     * @param tableName 翻译表表名
     * @param objectList 翻译字典集合
     */
    private void storeDict(String tableName, List<Map<String, String>> objectList) {
        objectList.forEach(objectMap -> objectMap.entrySet().forEach(entry -> storeDictToRedis(tableName, objectMap, entry)));
    }

    /**
     * 在redis中存储字典表
     * @param tableName 字典表名
     * @param objectMap 翻译字典对应翻译表中一行记录
     * @param entry 翻译字典的entry
     */
    private void storeDictToRedis(String tableName, Map<String, String> objectMap, Map.Entry<String, String> entry) {
        String key = entry.getKey();
        if (StringUtils.isBlank(key) || !key.contains(Constant.TRANSLATE_FIELD_SUFFIX)) {
            return;
        }
        String translateKey = key.split(Constant.TRANSLATE_FIELD_SUFFIX)[0];
        if (StringUtils.isBlank(translateKey)) {
            return;
        }
        RedisKey redisKey = new RedisKey(RedisKeysPrefix.I18N_KEY, tableName);
        String translateKeyVal = objectMap.get(translateKey);
        if(StringUtils.isBlank(translateKeyVal)) {
            return;
        }
        String hashField = key.concat(":").concat(translateKeyVal);
        String value = objectMap.get(key);
        jedisTemplate.hset(redisKey, hashField, value);
    }

    /**
     * 获取所有后缀为chinese、english、german、french、japanese的翻译字典表
     * @return 返回字典表名
     */
    private Set<String> getAllDictTables() {
        List<TranslateEntity> translateEntities = translateMapper.selectAllTables(Constant.TRANSLATE_TABLE_SCHEMA);
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