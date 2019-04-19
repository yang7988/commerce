package com.commerce.huayi.Interceptor;

import com.commerce.huayi.service.TranslateService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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


    @Autowired
    private TranslateService translateService;


    @Pointcut("execution(* com.commerce.huayi.controller..*Controller.*(..))")
    public void controller() {
    }

    @AfterReturning(value = "controller()", returning = "retVal")
    public void translateExecute(JoinPoint joinPoint, Object retVal) {
        long startTime = System.currentTimeMillis();
        translateService.translate(retVal);
        if (LOGGER.isWarnEnabled()) {
            LOGGER.warn("国际化翻译耗时======{}===={}", (System.currentTimeMillis() - startTime) + "/ms", joinPoint.toString());
        }
    }

}