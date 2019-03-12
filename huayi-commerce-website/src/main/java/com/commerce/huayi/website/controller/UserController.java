package com.commerce.huayi.website.controller;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.website.entity.request.UserLoginReq;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @ApiOperation(value = "用户登录",notes = "根据用户名密码登陆")
    @ApiImplicitParam(name = "userLoginReq", value = "用户登陆请求的实体", required = true, dataType = "UserLoginReq")
    @PostMapping(value = "/login")
    public ApiResponse login(@Valid UserLoginReq userLoginReq, BindingResult bindingResult) {
        return ApiResponse.returnSuccess();
    }
}