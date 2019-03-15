package com.commerce.huayi.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Translate {

    boolean required() default true;

    String key() default "";
}
