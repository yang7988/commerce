package com.commerce.huayi.Interceptor;

import com.commerce.huayi.api.ApiResponseEnum;
import com.commerce.huayi.api.BusinessException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validator;
import java.util.Set;

/**
 * 参数校验拦截器
 */
@Component
@Aspect
public class ValidatorAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorAspect.class);

    @Autowired
    private Validator validator;

    @Pointcut("execution(* com.commerce.huayi.controller..*Controller.*(..))")
    public void controller() {
    }

    @Around(value = "controller()")
    public Object validate(ProceedingJoinPoint joinPoint) throws Throwable {
        if(LOGGER.isWarnEnabled()) {
            LOGGER.warn("====正在校验{}方法参数",joinPoint.toString());
        }
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            validate(arg);
        }
        return joinPoint.proceed();
    }

    private void validate(Object arg) {
        if (arg == null) {
            return;
        }
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(arg);
        if (constraintViolations.size() <= 0) {
            return;
        }
        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            validate(constraintViolation);
        }
    }

    private void validate(ConstraintViolation<Object> constraintViolation) {
        Path property = constraintViolation.getPropertyPath();
        String name = property.iterator().next().getName();
        String errorMsg = "[" + name + "]" + constraintViolation.getMessage();
        LOGGER.error(errorMsg);
        throw new BusinessException(ApiResponseEnum.PARAMETER_INVALID, errorMsg);
    }

}