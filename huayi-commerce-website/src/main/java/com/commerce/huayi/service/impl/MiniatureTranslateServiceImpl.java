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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("miniatureTranslateService")
public class MiniatureTranslateServiceImpl implements TranslateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MiniatureTranslateServiceImpl.class);

    @Autowired
    private TranslateMapper translateMapper;

    @Autowired
    private DataSource dataSource;

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
        //获取ApiResponse#message字段，并翻译
        try {
            Field translateField = apiResponseClass.getDeclaredField("message");
            //翻译message字段
            translateField(definLanguage, apiResponse, apiResponseClass, translateField,
                    Constant.TRANSLATE_API_RESPONSE_TABLE_PREFIX, Constant.TRANSLATE_API_RESPONSE_COLUMN);
        } catch (Exception e) {
            LOGGER.error("TranslateService===translate===ApiResponse===error", e);
        }
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
        if (Collection.class.isAssignableFrom(data.getClass())) {
            Collection collection = (Collection) data;
            for (Object object : collection) {
                translateObject(language, object);
            }
        } else if (Map.class.isAssignableFrom(data.getClass())) {
            Map map = (Map) data;
            for (Object value : map.values()) {
                translateObject(language, value);
            }
        } else {
            translateObject(language, data);
        }
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
        String translateTableName = referenceTableName.concat("_").concat(language.getLanguage());
        String translateColumnName = referenceColumnName.concat(Constant.TRANSLATE_FIELD_SUFFIX);
        translateField.setAccessible(true);
        Object requiredFieldValue = translateField.get(object);
        if(!(requiredFieldValue instanceof String)) {
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
    private String getDictTranslate(String tableName, String columnName, String key,String refColumn) {
        //i18n的redis key
        RedisKey redisKey = new RedisKey(RedisKeysPrefix.I18N_KEY, tableName);
        String hashField = columnName.concat(":").concat(key);
        String translatedVal = jedisTemplate.hget(redisKey, hashField, String.class);
        if (StringUtils.isNotBlank(translatedVal)) {
            return translatedVal;
        }
        List<TranslateEntity> translateEntities = null;
        //检查翻译的字典表是否存在
        boolean tableExist = validateTableName(tableName);
        if(!tableExist) {
            LOGGER.warn("TranslateService===validateTableName===tableName=" + tableName + "==NotExist");
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

    private boolean validateTableName(String tableName) {
        try (Connection connection = dataSource.getConnection();
             ResultSet rs = connection.getMetaData().getTables(null, null, tableName, null)) {
            return rs.next();
        } catch (SQLException e) {
            LOGGER.error("TranslateService===validateTableName===error==tableName=" + tableName, e);
            return false;
        }
    }

    //获取添加了@Translate注解的字段，如果字段存在该注解表此字段需要被翻译
    private List<Field> getTranslateFields(Field[] fields) {
        if (fields.length == 0) {
            return null;
        }
        return Stream.of(fields).filter(field -> field.getAnnotation(Translate.class) != null)
                .collect(Collectors.toList());

    }

    /*private String toUpperCaseFirstOne(String content) {
        if (Character.isUpperCase(content.charAt(0))) {
            return content;
        }
        return String.valueOf(Character.toUpperCase(content.charAt(0))) + content.substring(1);
    }*/
}