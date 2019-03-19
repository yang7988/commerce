package com.commerce.huayi.service.impl;

import com.alibaba.fastjson.JSON;
import com.commerce.huayi.annotation.Translate;
import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.cache.JedisTemplate;
import com.commerce.huayi.cache.key.RedisKey;
import com.commerce.huayi.cache.key.RedisKeysPrefix;
import com.commerce.huayi.constant.Constant;
import com.commerce.huayi.constant.LanguageEnum;
import com.commerce.huayi.entity.db.TranslateEntity;
import com.commerce.huayi.entity.db.TranslateEntityExample;
import com.commerce.huayi.mapper.TranslateMapper;
import com.commerce.huayi.service.TranslateService;
import com.commerce.huayi.utils.ServletUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TranslateServiceImpl implements TranslateService, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(TranslateServiceImpl.class);

    //数据库所有的字典表缓存
    private static final HashSet<String> ALL_DICT_TABLES = new HashSet<>(16);

    @Autowired
    private TranslateMapper translateMapper;

    @Autowired
    private JedisTemplate jedisTemplate;

    public Object translate(Object requiredObject) {
        LanguageEnum language = ServletUtils.language();
        LanguageEnum definLanguage = language == null ? LanguageEnum.ZH_CN : language;
        if (!(requiredObject instanceof ApiResponse)) {
            return requiredObject;
        }
        //翻译ApiResponse
        ApiResponse apiResponse = (ApiResponse) requiredObject;
        String apiMessageCode = apiResponse.getMessage();
        Class<ApiResponse> apiResponseClass = ApiResponse.class;
        if (!isNecessary(definLanguage, apiResponseClass)) {
            return requiredObject;
        }
        //获取ApiResponse#message字段，并翻译
        Field translateField = null;
        try {
            translateField = apiResponseClass.getDeclaredField("message");
        } catch (NoSuchFieldException e) {
            LOGGER.error("TranslateService===translate===ApiResponse===NoSuchFieldException==={}", "message");
        }
        if (translateField == null) {
            return requiredObject;
        }
        String refTable = Constant.TRANSLATE_API_RESPONSE_TABLE_PREFIX;
        String refColumn = Constant.TRANSLATE_API_RESPONSE_COLUMN;
        try {
            //翻译message字段
            translateField(definLanguage, apiResponse, apiResponseClass, translateField, refTable, refColumn);
        } catch (Exception e) {
            LOGGER.error("TranslateService===translate===ApiResponse===error", e);
        }
        //  翻译apiresponse#data数据
        Object data = apiResponse.getData();
        if (data == null) {
            return requiredObject;
        }
        try {
            this.translateByType(definLanguage, data);
        } catch (Exception e) {
            LOGGER.error("TranslateService===translate===error", e);
        }
        return requiredObject;
    }

    private void translateByType(LanguageEnum language, Object data) throws Exception {
        if (Map.class.isAssignableFrom(data.getClass())) {
            LOGGER.warn("======国际化翻译不支持返回数据为Map类型");
            return;
        }
        if (Collection.class.isAssignableFrom(data.getClass())) {
            Collection collection = (Collection) data;
            if (isNecessary(language, collection)) {
                for (Object object : collection) {
                    translateObject(language, object);
                }
            }
        } else {
            if (isNecessary(language, data.getClass())) {
                translateObject(language, data);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private boolean isNecessary(LanguageEnum language, Collection collection) {
        Set<Class> clazzs = (Set<Class>) collection.stream().map(Object::getClass).collect(Collectors.toSet());
        return clazzs.stream().allMatch(clazz -> isNecessary(language, clazz));
    }

    private boolean isNecessary(LanguageEnum language, Class clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        if (declaredFields.length == 0) {
            return false;
        }
        //获取翻译字段
        List<Field> translateFields = getTranslateFields(declaredFields);
        if (CollectionUtils.isEmpty(translateFields)) {
            return false;
        }
        List<Translate> translateAnnotations = translateFields.stream().map(field -> field.getAnnotation(Translate.class)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(translateAnnotations)) {
            return false;
        }
        Set<String> refTables = translateAnnotations.stream().filter(annotation -> StringUtils.isNotBlank(annotation.refTable())).map(Translate::refTable)
                .collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(refTables)) {
            return false;
        }
        if (refTables.stream().anyMatch(refTable -> !tableNameExist(refTable.concat("_").concat(language.getLanguage())))) {
            LOGGER.error("====国际化翻译检测到未存在该语言对应的翻译字典======language=" + language.getLanguage());
            return false;
        }
        return true;
    }

    private void translateObject(LanguageEnum language, Object object) throws Exception {
        Class<?> clazz = object.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        //获取翻译字段
        List<Field> translateFields = getTranslateFields(declaredFields);
        //如果字段不存在@Translate注解，不予翻译
        if (CollectionUtils.isEmpty(translateFields)) {
            return;
        }
        //依次翻译所需的字段
        for (Field translateField : translateFields) {
            translateField(language, object, clazz, translateField);
        }
    }

    //获取翻译文本，反射覆盖java bean的属性
    private void translateField(LanguageEnum language, Object object, Class<?> clazz, Field translateField) throws Exception {
        Translate annotation = translateField.getAnnotation(Translate.class);
        String referenceTableName = annotation.refTable();
        String referenceColumnName = annotation.refColumn();
        if (StringUtils.isBlank(referenceTableName) || StringUtils.isBlank(referenceColumnName)) {
            return;
        }
        this.translateField(language, object, clazz, translateField, referenceTableName, referenceColumnName);
    }

    private void translateField(LanguageEnum language, Object object, Class<?> clazz, Field translateField,
                                String referenceTableName, String referenceColumnName) throws Exception {
        if (StringUtils.isBlank(referenceTableName) || StringUtils.isBlank(referenceColumnName)) {
            return;
        }
        String translateTableName = referenceTableName.concat("_").concat(language.getLanguage());
        String translateColumnName = referenceColumnName.concat(Constant.TRANSLATE_FIELD_SUFFIX);
        translateField.setAccessible(true);
        Object requiredFieldValue = translateField.get(object);
        if (!(requiredFieldValue instanceof String)) {
            return;
        }
        String translateKey = (String) requiredFieldValue;
        //  根据字典查询翻译的值
        String translatedVal = getDictTranslate(translateTableName, translateColumnName, translateKey, referenceColumnName);
        //  将翻译后的文本覆盖原 java的字段
        if (StringUtils.isNotBlank(translatedVal)) {
            translateField.set(object, translatedVal);
        }
    }

    //根据字典进行翻译
    private String getDictTranslate(String tableName, String columnName, String key, String refColumn) {
        //i18n的redis key
        RedisKey redisKey = new RedisKey(RedisKeysPrefix.I18N_KEY, tableName);
        String hashField = columnName.concat(":").concat(key);
        String translatedVal = jedisTemplate.hget(redisKey, hashField, String.class);
        if (StringUtils.isNotBlank(translatedVal)) {
            return translatedVal;
        }
        List<TranslateEntity> translateEntities = null;
        //检查翻译的字典表是否存在
        boolean tableExist = tableNameExist(tableName);
        if (!tableExist) {
            LOGGER.warn("TranslateService===tableNameExist===tableName=" + tableName + "==NotExist");
            return null;
        }
        TranslateEntityExample example = new TranslateEntityExample(tableName, columnName, refColumn, key);
        try {
            translateEntities = translateMapper.selectByKey(example);
        } catch (Exception e) {
            LOGGER.error("TranslateMapper===selectByKey===error===" + JSON.toJSONString(example), e);
        }
        if (CollectionUtils.isEmpty(translateEntities)) {
            return translatedVal;
        }
        translatedVal = translateEntities.get(0).getTranslateResult();
        if (StringUtils.isBlank(translatedVal)) {
            return translatedVal;
        }
        jedisTemplate.hset(redisKey, hashField, translatedVal);
        return translatedVal;
    }

    private boolean tableNameExist(String tableName) {
        if (CollectionUtils.isEmpty(ALL_DICT_TABLES)) {
            return false;
        }
        return ALL_DICT_TABLES.contains(tableName);
    }

    //获取添加了@Translate注解的字段，如果字段存在该注解表此字段需要被翻译
    private List<Field> getTranslateFields(Field[] fields) {
        if (fields.length == 0) {
            return null;
        }
        return Stream.of(fields).filter(field -> field.getAnnotation(Translate.class) != null)
                .collect(Collectors.toList());

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Set<String> allDictTables = getAllDictTables();
        if (CollectionUtils.isEmpty(allDictTables)) {
            return;
        }
        allDictTables.forEach(this::selectDictByExample);
    }

    /**
     * 查询字典实体
     *
     * @param tableName 翻译表表名
     */
    private void selectDictByExample(String tableName) {
        TranslateEntityExample example = new TranslateEntityExample();
        example.setTableName(tableName);
        List<Map<String, String>> objectList = translateMapper.selectDict(example);
        if (CollectionUtils.isEmpty(objectList)) {
            return;
        }
        this.storeDict(tableName, objectList);
    }

    /**
     * 迭代存储
     *
     * @param tableName  翻译表表名
     * @param objectList 翻译字典集合
     */
    private void storeDict(String tableName, List<Map<String, String>> objectList) {
        objectList.forEach(objectMap -> objectMap.entrySet().forEach(entry -> storeDictToRedis(tableName, objectMap, entry)));
    }

    /**
     * 在redis中存储字典表
     *
     * @param tableName 字典表名
     * @param objectMap 翻译字典对应翻译表中一行记录
     * @param entry     翻译字典的entry
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
        if (StringUtils.isBlank(translateKeyVal)) {
            return;
        }
        String hashField = key.concat(":").concat(translateKeyVal);
        String value = objectMap.get(key);
        jedisTemplate.hset(redisKey, hashField, value);
    }

    /**
     * 获取所有后缀为chinese、english、german、french、japanese的翻译字典表
     *
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
        Set<String> dictTables = collect.stream().map(TranslateEntity::getTableName).collect(Collectors.toSet());
        ALL_DICT_TABLES.addAll(dictTables);
        return dictTables;
    }

}