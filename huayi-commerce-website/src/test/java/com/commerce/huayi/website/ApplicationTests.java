package com.commerce.huayi.website;

import com.commerce.huayi.Application;
import com.commerce.huayi.cache.JedisTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private JedisTemplate jedisTemplate;

    @Test
    public void testRedis() throws Exception {

    }
}
