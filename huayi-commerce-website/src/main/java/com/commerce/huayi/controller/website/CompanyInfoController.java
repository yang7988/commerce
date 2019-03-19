package com.commerce.huayi.controller.website;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.service.CompanyInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/companyInfo")
@Api(value = "公司介绍")
public class CompanyInfoController {

    @Autowired
    private CompanyInfoService companyInfoService;

    @PostMapping(value = "/getCompanyInfo")
    @ApiOperation(value = "公司介绍",notes = "获取公司介绍信息")
    public ApiResponse getCompanyInfo() {
        return ApiResponse.returnSuccess(companyInfoService.getCompanyInfo());
    }

}
