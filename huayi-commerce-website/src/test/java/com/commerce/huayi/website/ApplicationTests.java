package com.commerce.huayi.website;

import com.alibaba.fastjson.JSON;
import com.commerce.huayi.Application;
import com.commerce.huayi.cache.JedisTemplate;
import com.commerce.huayi.entity.request.PageReq;
import com.commerce.huayi.entity.response.CustomerMessagePageVo;
import com.commerce.huayi.service.CustomerMessageService;
import com.commerce.huayi.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
