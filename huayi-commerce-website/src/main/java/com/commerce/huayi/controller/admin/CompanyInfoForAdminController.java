package com.commerce.huayi.controller.admin;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.CompanyInfoReq;
import com.commerce.huayi.entity.request.DelDataReq;
import com.commerce.huayi.entity.response.CompanyInfoVo;
import com.commerce.huayi.service.CompanyInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/companyInfo")
@Api(value = "公司介绍管理")
public class CompanyInfoForAdminController {

    @Autowired
    private CompanyInfoService companyInfoService;

    @PostMapping(value = "/getCompanyInfo")
    @ApiOperation(value = "公司介绍管理",notes = "获取公司介绍信息")
    public ApiResponse<List<CompanyInfoVo>> getCompanyInfo() {
        return ApiResponse.returnSuccess(companyInfoService.getCompanyInfo());
    }

    @PostMapping(value = "/addCompanyInfo")
    @ApiOperation(value = "公司介绍管理",notes = "添加公司介绍信息")
    public ApiResponse addCompanyInfo(@RequestBody CompanyInfoReq companyInfoReq) {
        companyInfoService.addCompanyInfo(companyInfoReq);
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/delCompanyInfo")
    @ApiOperation(value = "公司介绍管理",notes = "删除公司介绍信息")
    public ApiResponse delCompanyInfo(@RequestBody DelDataReq param) {
        companyInfoService.delCompanyInfo(param.getId());
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/updateCompanyInfo")
    @ApiOperation(value = "公司介绍管理",notes = "更新公司介绍信息")
    public ApiResponse updateCompanyInfo(@RequestBody CompanyInfoReq companyInfoReq) {
        companyInfoService.updateCompanyInfo(companyInfoReq);
        return ApiResponse.returnSuccess();
    }

}
