package com.commerce.huayi.Interceptor;

import com.commerce.huayi.annotation.Pretreatment;
import com.commerce.huayi.asyn.AsynTranslateTask;
import com.commerce.huayi.asyn.TranslateCacheFlushTask;
import com.commerce.huayi.cache.JedisTemplate;
import com.commerce.huayi.entity.request.AbstractDictReq;
import com.commerce.huayi.mapper.TranslateMapper;
import com.commerce.huayi.service.TranslateService;
import com.commerce.huayi.service.impl.ThreadService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Aspect
public class PretreatmentAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(PretreatmentAspect.class);

    @Autowired
    private ThreadService threadService;

    @Autowired
    private TranslateMapper translateMapper;

    @Autowired
    private TranslateService translateService;

    @Autowired
    private JedisTemplate jedisTemplate;

    @Pointcut("execution(* com.commerce.huayi.controller..*Controller.*(..)) && @annotation(com.commerce.huayi.annotation.Pretreatment)")
    public void controller() {
    }

    @Pointcut("execution(* com.commerce.huayi.service.*Service.*(..)) && @annotation(com.commerce.huayi.annotation.Dictionary)")
    public void service() {
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

    @AfterReturning(value = "service()",returning = "retVal")
    public void translateExecute(JoinPoint joinPoint, Object retVal) {
        Object[] args = joinPoint.getArgs();
        Optional<Object> optional = Stream.of(args).filter(arg -> arg instanceof AbstractDictReq).findFirst();
        Object arg = optional.orElse(null);
        if(arg == null) {
            return;
        }
        AbstractDictReq req = (AbstractDictReq) arg;
        Future<Boolean> future = threadService.submit(new AsynTranslateTask(req, translateMapper));
        try {
            if (future.get()) {
                TranslateCacheFlushTask flushTask = new TranslateCacheFlushTask(translateService, jedisTemplate);
                threadService.submit(flushTask);
            }
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error(e.getMessage(),e);
        }

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