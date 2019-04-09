package com.commerce.huayi.controller.admin;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.ContractInfoReq;
import com.commerce.huayi.entity.request.DelDataReq;
import com.commerce.huayi.entity.response.ContractInfoVo;
import com.commerce.huayi.service.ContractInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/contractInfo")
@Api(tags = "联系我们信息管理")
public class ContractInfoForAdminController {

    @Autowired
    private ContractInfoService contractInfoService;

    @PostMapping(value = "/getContractInfo")
    @ApiOperation(value = "联系我们信息管理",notes = "获取联系我们信息")
    public ApiResponse<List<ContractInfoVo>> getContractInfo() {
        return ApiResponse.returnSuccess(contractInfoService.getContractInfo());
    }

    @PostMapping(value = "/addContractInfo")
    @ApiOperation(value = "联系我们信息管理",notes = "添加联系我们数据")
    public ApiResponse addContractInfo(@RequestBody ContractInfoReq contractInfoReq) {
        contractInfoService.addContractInfo(contractInfoReq);
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/delContractInfo")
    @ApiOperation(value = "联系我们信息管理",notes = "删除联系我们数据")
    public ApiResponse delContractInfo(@RequestBody DelDataReq param) {
        contractInfoService.delContractInfo(param.getId());
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/updateContractInfo")
    @ApiOperation(value = "联系我们信息管理",notes = "更新联系我们数据")
    public ApiResponse updateContractInfo(@RequestBody ContractInfoReq contractInfoReq) {
        contractInfoService.updateContractInfo(contractInfoReq);
        return ApiResponse.returnSuccess();
    }

}
