package com.commerce.huayi.cache.serializer;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtostuffSerializer implements Serializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProtostuffSerializer.class);

    @Override
    @SuppressWarnings("unchecked")
    public byte[] serializer(Object object) {
        try {
            // 通过对象的类构建对应的schema
            Schema schema = RuntimeSchema.createFrom(object.getClass());
            // 将对象通过动态生成的schema转换成字节数组
            // LinkedBuffer用于缓存较大的对象
            return ProtostuffIOUtil.toByteArray(object, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        } catch (Exception e) {
            LOGGER.error("=====ProtostuffSerializer====serializer====error==={}", ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserializer(byte[] bytes, Class<T> clazz) {
        T obj;
        try {
            if (null == bytes || bytes.length == 0) {
                return null;
            }
            // 通过对象的类构建对应的schema；
            Schema schema = RuntimeSchema.createFrom(clazz);
            // 通过schema新建一个对象，这里需要转换一下
            obj = (T) schema.newMessage();
            // 数据反序列化
            ProtostuffIOUtil.mergeFrom(bytes, obj, schema);
        } catch (Exception e) {
            LOGGER.error("=====ProtostuffSerializer====deserializer==className={}====error==={}",
                    clazz.getSimpleName(), ExceptionUtils.getStackTrace(e));
            obj = null;
        }
        return obj;
    }

}