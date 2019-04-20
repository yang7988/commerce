package com.commerce.huayi.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * java bean拷贝工具类
 */
public class BeanCopyUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanCopyUtil.class);

    /**
     * java bean拷贝
     *
     * @param dest   目标对象
     * @param origin 原始对象
     */
    public static void copy(Object dest, Object origin) {
        try {
            BeanUtils.copyProperties(dest, origin);
        } catch (IllegalAccessException e) {
            LOGGER.error("BeanCopyUtil copy IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            LOGGER.error("BeanCopyUtil copy InvocationTargetException", e);
        }
    }

    public static <T> T copy(Class<T> destClazz, Object origin) {
        Constructor<T> constructor = null;
        try {
            constructor = destClazz.getConstructor();
        } catch (NoSuchMethodException e) {
            LOGGER.error("BeanCopyUtil destClazz NoSuchMethodException", e);
        }
        return copyReflectInstance(constructor, origin);
    }

    /**
     * java bean的集合拷贝
     *
     * @param destClazz 目标对象的Class对象
     * @param origins   原始java bean集合对象
     * @param <T>       目标对象泛型
     * @return 返回目标集合对象
     */
    public static <T> List<T> copy(Class<T> destClazz, List<?> origins) {
        if (CollectionUtils.isEmpty(origins)) {
            return null;
        }
        List<T> dests;
        Constructor<T> constructor;
        try {
            constructor = destClazz.getConstructor();
            dests = new ArrayList<>(origins.size());
        } catch (NoSuchMethodException e) {
            LOGGER.error("BeanCopyUtil destClazz NoSuchMethodException", e);
            return null;
        }
        List<T> finalDests = dests;
        origins.forEach(obj -> finalDests.add(copyReflectInstance(constructor, obj)));
        return dests;
    }

    /**
     * @param constructor 目标对象的反射构造器对象
     * @param origin      原始目标对象
     * @param <T>         目标对象泛型
     * @return 返回目标对象实例
     */
    private static <T> T copyReflectInstance(Constructor<T> constructor, Object origin) {
        if (constructor == null || origin == null) {
            return null;
        }
        T t = null;
        try {
            t = constructor.newInstance();
            BeanUtils.copyProperties(t, origin);
        } catch (IllegalAccessException e) {
            LOGGER.error("BeanCopyUtil copy IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            LOGGER.error("BeanCopyUtil copy InvocationTargetException", e);
        } catch (InstantiationException e) {
            LOGGER.error("BeanCopyUtil copy InstantiationException", e);
        }
        return t;
    }
}