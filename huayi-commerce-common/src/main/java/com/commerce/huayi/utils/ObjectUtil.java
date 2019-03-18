

package com.commerce.huayi.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ObjectUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectUtil.class);

    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(obj.getClass());
        } catch (IntrospectionException e) {
            LOGGER.error(e.getMessage(),e);
        }
        if (beanInfo == null) {
            return null;
        }
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        if(propertyDescriptors.length == 0) {
            return null;
        }
        Map<String, Object> map = new HashMap<>(propertyDescriptors.length);
        for (PropertyDescriptor property : propertyDescriptors) {
            objectToMap(obj, map, property);
        }
        return map;
    }

    private static void objectToMap(Object obj, Map<String, Object> map, PropertyDescriptor property) {
        String key = property.getName();
        if (key.compareToIgnoreCase("class") == 0) return;
        Method getter = property.getReadMethod();
        getter.setAccessible(true);
        Object value;
        try {
            value = getter.invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            value = null;
            LOGGER.error(e.getMessage(),e);
        }
        map.put(key, value);
    }


}
