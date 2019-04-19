package com.commerce.huayi.asyn;

import com.commerce.huayi.cache.JedisTemplate;
import com.commerce.huayi.service.TranslateService;

public class TranslateCacheFlushTask implements Runnable {

    private TranslateService translateService;

    private JedisTemplate jedisTemplate;

    public TranslateCacheFlushTask(TranslateService translateService, JedisTemplate jedisTemplate) {
        this.translateService = translateService;
        this.jedisTemplate = jedisTemplate;
    }

    @Override
    public void run() {
        try {
            jedisTemplate.afterPropertiesSet();
            translateService.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}