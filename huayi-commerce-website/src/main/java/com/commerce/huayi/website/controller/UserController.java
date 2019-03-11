package com.commerce.huayi.website.controller;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.website.entity.request.UserLoginReq;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @PostMapping(value = "/login")
    public ApiResponse login(@Valid UserLoginReq userLoginReq, BindingResult bindingResult) {
        return ApiResponse.returnSuccess();
    }
}