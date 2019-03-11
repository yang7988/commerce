package com.commerce.huayi.cache.key;

public class RedisKey {
    private RedisKeysPrefix redisKeysPrefix;
    private String key;

    public RedisKey(RedisKeysPrefix redisKeysPrefix, String key) {
        this.redisKeysPrefix = redisKeysPrefix;
        this.key = key;
    }

    public String getRedisKey() {
        return makeString();
    }

    public RedisKeysPrefix getRedisKeysPrefix() {
        return redisKeysPrefix;
    }

    public void setRedisKeysPrefix(RedisKeysPrefix redisKeysPrefix) {
        this.redisKeysPrefix = redisKeysPrefix;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String makeString() {
        return this.redisKeysPrefix.getPrefix().concat(this.key);
    }
}