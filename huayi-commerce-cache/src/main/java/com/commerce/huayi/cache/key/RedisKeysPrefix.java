package com.commerce.huayi.cache.key;

public enum RedisKeysPrefix {
    PAY_KEY("pay:", "pay 模块 redis key 前缀"),
    USER_KEY("user:", "user 模块 redis key 前缀"),
    TASK_KEY("task:", "task 模块 redis key 前缀");

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
