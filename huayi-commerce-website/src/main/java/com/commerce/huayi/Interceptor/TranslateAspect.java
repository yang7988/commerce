package com.commerce.huayi.Interceptor;

import com.commerce.huayi.service.TranslateService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TranslateAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorAspect.class);
    //原表中需要翻译的字段对应的翻译表中的翻译字段名后缀 例如category_name -> category_name_translate
    private static final String TRANSLATE_FIELD_SUFFIX = "_translate";
    private static final String TRANSLATE_API_RESPONSE_TABLE_PREFIX = "tb_api_response";
    private static final String TRANSLATE_API_RESPONSE_COLUMN = "code";

    @Autowired
    private TranslateService translateService;


    @Pointcut("execution(* com.commerce.huayi.controller..*Controller.*(..))")
    public void controller() {
    }


    @Around(value = "controller()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object retVl;
        try {
            //开始翻译
            retVl = translateService.translate(joinPoint.proceed());
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            long consumerTime = System.currentTimeMillis() - startTime;
            LOGGER.warn("翻译拦截器翻译耗时=====" + consumerTime + "/ms");
        }
        return retVl;
    }
}