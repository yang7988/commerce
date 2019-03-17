package com.commerce.huayi.controller.website;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.CompanyInfoReq;
import com.commerce.huayi.service.CompanyInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/companyInfo")
@Api(value = "公司介绍管理")
public class CompanyInfoController {

    @Autowired
    private CompanyInfoService companyInfoService;

    @PostMapping(value = "/getCompanyInfo")
    @ApiOperation(value = "公司介绍管理",notes = "获取公司介绍信息")
    public ApiResponse getCompanyInfo() {
        return ApiResponse.returnSuccess(companyInfoService.getCompanyInfo());
    }

    @PostMapping(value = "/addCompanyInfo")
    @ApiOperation(value = "公司介绍管理",notes = "添加公司介绍信息")
    public ApiResponse addCompanyInfo(@Valid @RequestBody CompanyInfoReq companyInfoReq, BindingResult bindingResult) {
        companyInfoService.addCompanyInfo(companyInfoReq);
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/delCompanyInfo")
    @ApiOperation(value = "公司介绍管理",notes = "删除公司介绍信息")
    public ApiResponse delCompanyInfo(@RequestBody Map<String, String> param) {
        companyInfoService.delCompanyInfo(param.get("id"));
        return ApiResponse.returnSuccess();
    }

}
