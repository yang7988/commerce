package com.commerce.huayi.Interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TranslateAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorAspect.class);

    @Pointcut("execution(* com.commerce.huayi.service.*Service.*(..)) && @annotation(com.commerce.huayi.annotation.Translate)")
    public void service() {
    }

    @Around(value = "service()")
    public Object validate(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Object retVl = null;
        try {
             retVl = joinPoint.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        } finally {

        }
        return retVl;
    }
}