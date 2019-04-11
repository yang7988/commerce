package com.commerce.huayi.Interceptor;

import com.commerce.huayi.annotation.Pretreatment;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Aspect
public class PretreatmentAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(PretreatmentAspect.class);

    @Pointcut("execution(* com.commerce.huayi.controller..*Controller.*(..)) && @annotation(com.commerce.huayi.annotation.Pretreatment)")
    public void controller() {
    }

    @Before(value = "controller()")
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Optional<Object> optional = Stream.of(args).filter(arg -> arg.getClass().getAnnotation(Pretreatment.class) != null).findFirst();
        Object obj = optional.orElse(null);
        if(obj == null) {
            return;
        }
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        if (declaredFields == null || declaredFields.length <= 0) {
            return;
        }
        List<Field> fields = Stream.of(declaredFields).filter(field -> field.getAnnotation(Pretreatment.class) != null).collect(Collectors.toList());
        fields.forEach(field -> pretreatmentString(obj,field));
    }

    private void pretreatmentString(Object obj, Field field)  {
        try {
            field.setAccessible(true);
            Object result = field.get(obj);
            if(!(result instanceof String)) {
                return;
            }
            String ret = (String) result;
            if(StringUtils.isBlank(ret)) {
                return;
            }
            ret = ret.replaceAll(" ", "");
            String pretreatmentString = DigestUtils.md5Hex(ret);
            field.set(obj,pretreatmentString);
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(),e);
        }
    }
}