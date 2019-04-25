package com.commerce.huayi.Interceptor;

import com.commerce.huayi.constant.LanguageEnum;
import com.commerce.huayi.service.impl.DataSourceContext;
import com.commerce.huayi.utils.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
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
        LanguageEnum languageEnum;
        if (StringUtils.isBlank(language) || (languageEnum = LanguageEnum.enums(language)) == null) {
            languageEnum = LanguageEnum.ZH_CN;
        }
        DataSourceContext.change(point.toString(),languageEnum.getDatasource());
    }

    @After("controller()")
    public void afterSwitchDS(JoinPoint point) {
        DataSourceContext.clear(point.toString());
    }
}