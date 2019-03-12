package com.commerce.huayi.website;

import com.commerce.huayi.cache.JedisTemplate;
import com.commerce.huayi.cache.key.RedisKey;
import com.commerce.huayi.cache.key.RedisKeysPrefix;
import com.commerce.huayi.website.entity.db.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

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
        RedisKey redisKey = new RedisKey(RedisKeysPrefix.USER_KEY, "userid:1");
        User user = jedisTemplate.get(redisKey, User.class);
        if(user == null) {
            user = new User();
            user.setId(1);
            user.setUserName("xuyangyang");
            user.setPassword("asdasdasd");
            user.setAddress("dfdasdasdasd");
            user.setCreateDate(new Date());
            user.setUpdateDate(new Date());
            user.setIsDelete((byte)1);
            jedisTemplate.set(redisKey, user);
        }
        jedisTemplate.delete(redisKey);
        User user1 = jedisTemplate.get(redisKey, User.class);
        System.out.println(user);
    }
}
