package com.commerce.huayi.Interceptor;

import com.commerce.huayi.strategy.TranslateStrategy;
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


    @Autowired
    private TranslateStrategy translateStrategy;


    @Pointcut("execution(* com.commerce.huayi.controller..*Controller.*(..))")
    public void controller() {
    }


    @Around(value = "controller()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object retVl;
        try {
            //开始翻译
            retVl = translateStrategy.translate(joinPoint.proceed());
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            long consumerTime = System.currentTimeMillis() - startTime;
            LOGGER.warn("翻译拦截器翻译耗时=====" + consumerTime + "/ms");
        }
        return retVl;
    }
}