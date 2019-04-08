package com.commerce.huayi.website;

import com.alibaba.fastjson.JSON;
import com.commerce.huayi.Application;
import com.commerce.huayi.cache.JedisTemplate;
import com.commerce.huayi.cache.enums.JedisStatus;
import com.commerce.huayi.cache.key.RedisKey;
import com.commerce.huayi.cache.key.RedisKeysPrefix;
import com.commerce.huayi.entity.request.PageReq;
import com.commerce.huayi.entity.response.CustomerMessagePageVo;
import com.commerce.huayi.service.CustomerMessageService;
import com.commerce.huayi.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {

    @Autowired
    private GoodsService goodsService;

    @Test
    public void contextLoads() {
    }

    @Autowired
    private JedisTemplate jedisTemplate;

    @Test
    public void testRedis() throws Exception {

    }

    @Autowired
    private CustomerMessageService customerMessageService;

    @Test
    public void testI18n() throws Exception {
        PageReq pageReq = new PageReq();
        pageReq.setPageIndex(1);
        pageReq.setPageMaxSize(10);
        CustomerMessagePageVo customerMessages = customerMessageService.getCustomerMessages(pageReq);
        System.out.println(customerMessages);

    }

    @Test
    public void testImage() throws Exception {
        RedisKey redisKey = new RedisKey(RedisKeysPrefix.IMAGE_KEY, "big_bluetooth_earphone");
        Path path = Paths.get("E:\\BaiduNetdiskDownload\\category\\big_bluetooth_earphone.jpg");
        byte[] data = null;
        data = Files.readAllBytes(path);
        JedisStatus jedisStatus = jedisTemplate.hset(redisKey, "big_bluetooth_earphone_sample", data);
        byte[] bytes = jedisTemplate.hget(redisKey, "big_bluetooth_earphone_sample");
        System.out.println(jedisStatus);
    }
}
