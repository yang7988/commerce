package com.commerce.huayi.Interceptor;

import com.commerce.huayi.annotation.Translate;
import com.commerce.huayi.constant.LanguageEnum;
import com.commerce.huayi.constant.TranslateDict;
import com.commerce.huayi.entity.db.TranslateEntity;
import com.commerce.huayi.entity.db.TranslateEntityExample;
import com.commerce.huayi.mapper.TranslateMapper;
import com.commerce.huayi.utils.ServletUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Aspect
public class TranslateAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorAspect.class);

    @Autowired
    private TranslateMapper translateMapper;

    @Pointcut("execution(* com.commerce.huayi.service.*Service.*(..)) && @annotation(com.commerce.huayi.annotation.Translate)")
    public void service() {
    }

    @Around(value = "service()")
    public Object validate(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        LanguageEnum language = ServletUtils.language();
        Object retVl;
        try {
            retVl = joinPoint.proceed();
            //开始翻译
            this.startTranslate(language, retVl);
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            long consumerTime = System.currentTimeMillis() - startTime;
            LOGGER.warn("翻译拦截器翻译耗时=====" + consumerTime + "/ms");
        }
        return retVl;
    }

    private void startTranslate(LanguageEnum language, Object retVl) throws Exception{
        if (Collection.class.isAssignableFrom(retVl.getClass())) {
            Collection collection = (Collection) retVl;
            for (Object object : collection) {
                translateObject(language, object);
            }
        } else if (Map.class.isAssignableFrom(retVl.getClass())) {
            Map map = (Map) retVl;
            for (Object value : map.values()) {
                translateObject(language, value);
            }
        } else {
            translateObject(language, retVl);
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
    private void translateField(LanguageEnum language, Object object, Class<?> clazz, Field translateField) throws Exception{
        Translate annotation = translateField.getAnnotation(Translate.class);
        String referenceTableName = annotation.refTable();
        String referenceColumnName = annotation.refColumn();
        if(StringUtils.isBlank(referenceTableName) || StringUtils.isBlank(referenceColumnName)) {
            return;
        }
        String translateTableName = referenceTableName.concat("_").concat(language.getLanguage());
        String translateColumnName = referenceColumnName.concat("_translate");
        //  字段名首字母大写
        String fieldName = toUpperCaseFirstOne(translateField.getName());
        //  java bean getter属性
        String getterMethodName = "get" + fieldName;
        //  java bean setter属性
        String setterMethodName = "set" + fieldName;
        //  getter方法
        Method getterMethod = clazz.getMethod(getterMethodName);
        //  setter 方法
        Method setterMethod = clazz.getMethod(setterMethodName, translateField.getType());
        //  反射获得java bean 的名称 例如 categoryName -> big_bluetooth_earphone
        Object getterReturn = getterMethod.invoke(object);
        String translateKey = (String) getterReturn;
        //  根据字典获取翻译后的文本字符串
        TranslateEntityExample translateEntityExample = new TranslateEntityExample(translateTableName, translateColumnName,
                referenceColumnName, translateKey);
        TranslateEntity translateEntity = translateMapper.selectByKey(translateEntityExample);
        if(translateEntity == null || StringUtils.isBlank(translateEntity.getTranslateResult())) {
            return;
        }
//        String translateResult = TranslateDict.categoryDist.get(language.name()).get(invoke);
        //  将翻译后的文本覆盖原 java的字段
        setterMethod.invoke(object, translateEntity.getTranslateResult());
    }


    //获取添加了@Translate注解的字段，如果字段存在该注解表此字段需要被翻译
    private List<Field> getTranslateFields(Field[] fields) {
        if (fields.length == 0) {
            return null;
        }
        return Stream.of(fields).filter(field -> field.getAnnotation(Translate.class) != null)
                .collect(Collectors.toList());

    }

    //获取需要翻译的方法的语言
    private LanguageEnum getLanguage(Object[] args) {
        Optional<Object> optional = Stream.of(args).filter(obj -> obj instanceof LanguageEnum).findFirst();
        Object object = optional.orElse(null);
        return object == null ? null : (LanguageEnum) object;
    }


    private static String toUpperCaseFirstOne(String content) {
        if (Character.isUpperCase(content.charAt(0))) {
            return content;
        }
        return String.valueOf(Character.toUpperCase(content.charAt(0))) + content.substring(1);
    }
}