package com.commerce.huayi.Interceptor;

import com.commerce.huayi.annotation.Translate;
import com.commerce.huayi.constant.LanguageEnum;
import com.commerce.huayi.constant.TranslateDict;
import org.apache.commons.collections.CollectionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Pointcut("execution(* com.commerce.huayi.service.*Service.*(..)) && @annotation(com.commerce.huayi.annotation.Translate)")
    public void service() {
    }

    @Around(value = "service()")
    public Object validate(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        LanguageEnum language = getLanguage(args);
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
        List<Field> translateFields = getTranslateFields(declaredFields);
        if (CollectionUtils.isEmpty(translateFields)) {
            return;
        }
        for (Field translateField : translateFields) {
            translateField(language, object, clazz, translateField);
        }
    }

    private void translateField(LanguageEnum language, Object object, Class<?> clazz, Field translateField) throws Exception{
        String fieldName = toUpperCaseFirstOne(translateField.getName());
        String getterMethodName = "get" + fieldName;
        String setterMethodName = "set" + fieldName;
        Method getterMethod = clazz.getMethod(getterMethodName);
        Method setterMethod = clazz.getMethod(setterMethodName, translateField.getType());
        Object invoke = getterMethod.invoke(object);
        String translateResult = TranslateDict.categoryDist.get(language.name()).get(invoke);
        setterMethod.invoke(object, translateResult);
    }

    private List<Field> getTranslateFields(Field[] fields) {
        if (fields.length == 0) {
            return null;
        }
        return Stream.of(fields).filter(field -> field.getAnnotation(Translate.class) != null)
                .collect(Collectors.toList());

    }

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