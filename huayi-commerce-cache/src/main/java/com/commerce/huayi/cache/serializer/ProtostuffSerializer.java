package com.commerce.huayi.cache.serializer;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

public class ProtostuffSerializer implements Serializer {

    @Override
    @SuppressWarnings("unchecked")
    public byte[] serializer(Object object) {
        // 通过对象的类构建对应的schema
        Schema schema = RuntimeSchema.createFrom(object.getClass());
        // 将对象通过动态生成的schema转换成字节数组
        // LinkedBuffer用于缓存较大的对象
        return ProtostuffIOUtil.toByteArray(object, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
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
            obj = null;
        }
        return obj;
    }
}