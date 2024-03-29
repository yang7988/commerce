package com.commerce.huayi.website;

import com.commerce.huayi.Application;
import com.commerce.huayi.cache.JedisTemplate;
import com.commerce.huayi.cache.enums.JedisStatus;
import com.commerce.huayi.cache.key.RedisKey;
import com.commerce.huayi.cache.key.RedisKeysPrefix;
import com.commerce.huayi.service.CustomerMessageService;
import com.commerce.huayi.service.GoodsService;
import com.commerce.huayi.service.ImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {

    @Autowired
    private GoodsService goodsService;

    public void contextLoads() {
    }

    @Autowired
    private JedisTemplate jedisTemplate;

    @Test
    public void testRedis() throws Exception {

    }

    @Autowired
    private CustomerMessageService customerMessageService;

    @Autowired
    private ImageService imageService;

    @Test
    public void testI18n() throws Exception {

    }

    @Test
    public void testImage() throws Exception {
        imageService.upload(new byte[]{0,2});
    }

    @Test
    public void testJson() throws Exception {
        /*Map<String, String> map = new HashMap<>();
        map.put("goodsName_english", "iphone");
        map.put("goodsName_chinese", "爱疯");
        map.put("goodsDescription_english", "iphone");
        map.put("goodsDescription_chinese", "爱疯");
        map.put("specName_english", "ram");
        map.put("specName_chinese", "内存");
        map.put("specDescription_english", "ram");
        map.put("specDescription_chinese", "手机内存");
        map.put("specValue_english", "16G");
        map.put("specValue_chinese", "16G内存");
        System.out.println(JSON.toJSONString(map));*/
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("table", "tb_goods_spu_chinese");
        objectMap.put("goods_name", "sdfasld");
        objectMap.put("goods_name_translate", "你好");
        objectMap.put("goods_description", "asfdasds");
        objectMap.put("goods_description_translate", "你好啊");
        objectMap.put("id", 100);
    }
}
