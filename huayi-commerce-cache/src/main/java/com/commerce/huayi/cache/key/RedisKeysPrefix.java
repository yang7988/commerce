package com.commerce.huayi.cache.key;

public enum RedisKeysPrefix {
    USER_KEY("user:", "用户模块 redis key 前缀"),
    PRODUCT_KEY("product:", "商品模块 redis key 前缀"),
    I18N_KEY("i18n:", "国际化翻译的字典 redis key 前缀"),
    LANGUAGE_KEY("language:", "语言字典 redis key 前缀"),
    IMAGE_KEY("product:image:", "商品图片存放 redis key 前缀");

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
