package com.commerce.huayi.controller.website;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.cache.JedisTemplate;
import com.commerce.huayi.cache.key.RedisKey;
import com.commerce.huayi.cache.key.RedisKeysPrefix;
import com.commerce.huayi.cache.serializer.Serializer;
import com.commerce.huayi.entity.response.CompanyInfoVo;
import com.commerce.huayi.service.CompanyInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/companyInfo")
@Api(tags = "公司介绍")
public class CompanyInfoController {

    @Autowired
    private CompanyInfoService companyInfoService;

    @Autowired
    private JedisTemplate jedisTemplate;

    @PostMapping(value = "/getCompanyInfo")
    @ApiOperation(value = "公司介绍", notes = "获取公司介绍信息")
    public ApiResponse<List<CompanyInfoVo>> getCompanyInfo() {
        return ApiResponse.returnSuccess(companyInfoService.getCompanyInfo());
    }

    @PostMapping(value = "/tokenTest")
    public ApiResponse tokenTest() {
        String token = "VISFBNISABGET1651AB1EB6531";
        RedisKey redisKey = new RedisKey(RedisKeysPrefix.USER_KEY, "admin");
        jedisTemplate.setex(redisKey,1800,token);

        RedisKey redisKey1 = new RedisKey(RedisKeysPrefix.USER_KEY, "admin");
        String token1 = jedisTemplate.get(redisKey, String.class);

        return ApiResponse.returnSuccess();
    }

}
