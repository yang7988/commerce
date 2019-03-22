package com.commerce.huayi.controller.admin;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.UserLoginReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/admin")
@Api(value = "后台管理")
public class AdminController {

    @PostMapping(value = "/login")
    @ApiOperation(value = "管理员登陆",notes = "管理员登陆")
    public ApiResponse login(@Valid @RequestBody UserLoginReq userLoginReq, BindingResult bindingResult) {
        return ApiResponse.returnSuccess();
    }
}