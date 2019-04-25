package com.commerce.huayi.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceContext.class);

    private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();

    // 设置数据源名
    public static void change(String joinPoint,String dbType) {
        LOGGER.warn("{}---正在切换数据源到-----{}",joinPoint,dbType);
        HOLDER.set(dbType);
    }

    // 获取数据源名
    public static String get() {
        return HOLDER.get();
    }

    // 清除数据源名
    public static void clear(String joinPoint) {
        LOGGER.warn("{}---正在清除数据源-----{}",joinPoint,HOLDER.get());
        HOLDER.remove();
    }

}