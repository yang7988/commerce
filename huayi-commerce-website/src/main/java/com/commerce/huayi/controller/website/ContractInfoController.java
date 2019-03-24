package com.commerce.huayi.controller.website;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.response.ContractInfoVo;
import com.commerce.huayi.service.ContractInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/contractInfo")
@Api(value = "联系我们")
public class ContractInfoController {

    @Autowired
    private ContractInfoService contractInfoService;

    @PostMapping(value = "/getContractInfo")
    @ApiOperation(value = "联系我们",notes = "获取联系我们信息")
    public ApiResponse<List<ContractInfoVo>> getContractInfo() {
        return ApiResponse.returnSuccess(contractInfoService.getContractInfo());
    }

}
