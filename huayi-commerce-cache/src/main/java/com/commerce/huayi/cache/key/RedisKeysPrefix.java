package com.commerce.huayi.cache.key;

public enum RedisKeysPrefix {
    USER_KEY("user:", "用户模块 redis key 前缀"),
    PRODUCT_KEY("product:", "商品模块 redis key 前缀");

    private String prefix;
    private String info;

    RedisKeysPrefix(String prefix, String info) {
        this.prefix = prefix;
        this.info = info;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
