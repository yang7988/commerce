package com.commerce.huayi.controller.website;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.UserLoginReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
@Api(value = "用户管理")
public class UserController {

    @ApiOperation(value = "用户登录",notes = "根据用户名密码登陆")

    @PostMapping(value = "/login")
    public ApiResponse login(@RequestBody UserLoginReq userLoginReq) {
        return ApiResponse.returnSuccess();
    }
}