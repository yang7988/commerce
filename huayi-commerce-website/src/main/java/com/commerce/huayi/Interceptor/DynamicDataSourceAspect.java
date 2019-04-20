package com.commerce.huayi.Interceptor;

import com.commerce.huayi.constant.LanguageEnum;
import com.commerce.huayi.service.impl.DataSourceContextHolder;
import com.commerce.huayi.utils.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DynamicDataSourceAspect {

    @Pointcut("execution(* com.commerce.huayi.controller..*Controller.*(..))")
    public void controller() {
    }

    @Before("controller()")
    public void beforeSwitchDS(JoinPoint point) {
        String language = ServletUtils.language();
        if (StringUtils.isBlank(language)) {
            DataSourceContextHolder.setDB(DataSourceContextHolder.DEFAULT_DS);
            return;
        }
        LanguageEnum languageEnum = LanguageEnum.enums(language);
        if(languageEnum == null) {
            DataSourceContextHolder.setDB(DataSourceContextHolder.DEFAULT_DS);
            return;
        }
        DataSourceContextHolder.setDB(languageEnum.getDatasource());
    }


}