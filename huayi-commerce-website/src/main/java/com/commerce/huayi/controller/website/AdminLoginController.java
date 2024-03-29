package com.commerce.huayi.controller.website;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.UserLoginReq;
import com.commerce.huayi.entity.response.AdministratorVo;
import com.commerce.huayi.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin")
@Api(tags = "后台管理员管理")
public class AdminLoginController {

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/login")
    @ApiOperation(value = "后台管理员管理", notes = "管理员登陆")
    public ApiResponse<AdministratorVo> login(@RequestBody UserLoginReq userLoginReq) {
        return ApiResponse.returnSuccess(adminService.login(userLoginReq.getUserName(), userLoginReq.getPassword()));
    }

}