package com.commerce.huayi.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解，用于接口动态翻译
 */
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Translate {
    String refTable() default "";
    String refColumn() default "";
}
