package com.commerce.huayi.controller.admin;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.ContractInfoReq;
import com.commerce.huayi.service.ContractInfoService;
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
@RequestMapping(value = "/api/admin/contractInfo")
@Api(value = "联系我们信息管理")
public class ContractInfoForAdminController {

    @Autowired
    private ContractInfoService contractInfoService;

    @PostMapping(value = "/addContractInfo")
    @ApiOperation(value = "联系我们信息管理",notes = "添加联系我们数据")
    public ApiResponse addContractInfo(@Valid @RequestBody ContractInfoReq contractInfoReq, BindingResult bindingResult) {
        contractInfoService.addContractInfo(contractInfoReq);
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/delContractInfo")
    @ApiOperation(value = "联系我们信息管理",notes = "删除联系我们数据")
    public ApiResponse delContractInfo(@RequestBody Map<String, String> param) {
        contractInfoService.delContractInfo(param.get("id"));
        return ApiResponse.returnSuccess();
    }

}
