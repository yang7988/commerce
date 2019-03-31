package com.commerce.huayi.controller.admin;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.AdministratorReq;
import com.commerce.huayi.entity.request.DelDataReq;
import com.commerce.huayi.entity.request.UserLoginReq;
import com.commerce.huayi.entity.response.AdministratorVo;
import com.commerce.huayi.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/admin")
@Api(value = "后台管理员管理")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/login")
    @ApiOperation(value = "后台管理员管理",notes = "管理员登陆")
    public ApiResponse<AdministratorVo> login(@Valid @RequestBody UserLoginReq userLoginReq, BindingResult bindingResult) {
        return ApiResponse.returnSuccess(adminService.login(userLoginReq.getUserName(), userLoginReq.getPassword()));
    }

    @PostMapping(value = "/addAdmin")
    @ApiOperation(value = "后台管理员管理",notes = "添加管理员")
    public ApiResponse addAdmin(@Valid @RequestBody AdministratorReq administratorReq, BindingResult bindingResult) {
        String loginName = administratorReq.getLoginName();
        if(adminService.loginNameIsUsed(loginName)) {
            return ApiResponse.returnFail("该登录名已经被占用");
        }
        adminService.addAdmin(administratorReq);
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/delAdmin")
    @ApiOperation(value = "后台管理员管理",notes = "删除管理员")
    public ApiResponse delAdmin(@Valid @RequestBody DelDataReq delDataReq, BindingResult bindingResult) {
        adminService.delAdmin(delDataReq);
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/getAdmin")
    @ApiOperation(value = "后台管理员管理",notes = "获取所有管理员")
    public ApiResponse<List<AdministratorVo>> getAdmin() {
        return ApiResponse.returnSuccess(adminService.getAdmin());
    }

}