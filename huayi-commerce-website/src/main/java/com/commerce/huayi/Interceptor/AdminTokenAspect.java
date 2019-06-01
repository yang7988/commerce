package com.commerce.huayi.Interceptor;

import com.commerce.huayi.api.ApiResponseEnum;
import com.commerce.huayi.api.BusinessException;
import com.commerce.huayi.cache.JedisTemplate;
import com.commerce.huayi.cache.key.RedisKey;
import com.commerce.huayi.cache.key.RedisKeysPrefix;
import com.commerce.huayi.utils.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AdminTokenAspect {

    @Autowired
    private JedisTemplate jedisTemplate;

    @Pointcut("execution(* com.commerce.huayi.controller.admin..*Controller.*(..))")
    public void controller() {
    }

    @Before("controller()")
    public void beforeAdminOperator(JoinPoint point) {
        if(ServletUtils.getHttpServletRequest().getRequestURI().contains("exportCustomerMessages")) {
            // 暂时对导出操作放行，不进行任何校验
        } else {
            String loginName = ServletUtils.loginName();
            String adminToken = ServletUtils.adminToken();

            if (StringUtils.isBlank(loginName)) {
                throw new BusinessException(ApiResponseEnum.USERNAME_PASSWORD_ERROR, "header param loginName can not be null");
            }

            if (StringUtils.isBlank(adminToken)) {
                throw new BusinessException(ApiResponseEnum.USERNAME_PASSWORD_ERROR, "header param adminToken can not be null");
            }

            RedisKey redisKey = new RedisKey(RedisKeysPrefix.USER_KEY, loginName);
            String token = jedisTemplate.get(redisKey, String.class);

            if (adminToken.equals(token)) {
                // 校验通过
                RedisKey redisKey11 = new RedisKey(RedisKeysPrefix.USER_KEY, loginName);
                jedisTemplate.setex(redisKey11, 1800, token);
            } else {

                throw new BusinessException(ApiResponseEnum.USERNAME_PASSWORD_ERROR);
            }
        }

    }

}


