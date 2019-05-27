package com.commerce.huayi.controller.admin;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.api.ApiResponseEnum;
import com.commerce.huayi.entity.request.AdministratorReq;
import com.commerce.huayi.entity.request.DelDataReq;
import com.commerce.huayi.entity.response.AdministratorVo;
import com.commerce.huayi.service.AdminService;
import com.commerce.huayi.utils.ServletUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin")
@Api(tags = "后台管理员管理")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/addAdmin")
    @ApiOperation(value = "后台管理员管理", notes = "添加管理员")
    public ApiResponse addAdmin(@RequestBody AdministratorReq administratorReq) {

        String operatorLoginName = ServletUtils.loginName();
        if(!"0".equals(adminService.getAdminInfo(operatorLoginName).getAdminRole())) {
            return ApiResponse.returnFail(ApiResponseEnum.FORBIDDEN);
        }

        if(StringUtils.isBlank(administratorReq.getLoginName()) || StringUtils.isBlank(administratorReq.getMobilePhone())
            || StringUtils.isBlank(administratorReq.getAdminRole()) || StringUtils.isBlank(administratorReq.getPassword())) {
            return ApiResponse.returnFail(ApiResponseEnum.PARAMETER_INVALID);
        }

        String loginName = administratorReq.getLoginName();
        if (adminService.loginNameIsUsed(loginName)) {
            return ApiResponse.returnFail("该登录名已经被占用");
        }
        adminService.addAdmin(administratorReq);
        return ApiResponse.returnSuccess();

    }

    @PostMapping(value = "/delAdmin")
    @ApiOperation(value = "后台管理员管理", notes = "删除管理员")
    public ApiResponse delAdmin(@RequestBody DelDataReq delDataReq) {
        String operatorLoginName = ServletUtils.loginName();
        if(!"0".equals(adminService.getAdminInfo(operatorLoginName).getAdminRole())) {
            return ApiResponse.returnFail(ApiResponseEnum.FORBIDDEN);
        }
        adminService.delAdmin(delDataReq);
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/getAdmin")
    @ApiOperation(value = "后台管理员管理", notes = "获取所有管理员")
    public ApiResponse<List<AdministratorVo>> getAdmin() {
        return ApiResponse.returnSuccess(adminService.getAdmin());
    }

}