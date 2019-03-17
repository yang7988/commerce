package com.commerce.huayi.controller.website;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.RecruitmentInfoReq;
import com.commerce.huayi.service.RecruitmentInfoService;
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
@RequestMapping(value = "/api/recruitmentInfo")
@Api(value = "人才中心管理")
public class RecruitmentInfoController {

    @Autowired
    private RecruitmentInfoService recruitmentInfoService;

    @PostMapping(value = "/getRecruitmentInfos")
    @ApiOperation(value = "人才中心管理",notes = "获取人才中心信息")
    public ApiResponse getRecruitmentInfos() {
        return ApiResponse.returnSuccess(recruitmentInfoService.getRecruitmentInfos());
    }

    @PostMapping(value = "/addRecruitmentInfo")
    @ApiOperation(value = "人才中心管理",notes = "添加人才中心数据")
    public ApiResponse addRecruitmentInfo(@Valid @RequestBody RecruitmentInfoReq recruitmentInfoReq, BindingResult bindingResult) {
        recruitmentInfoService.addRecruitmentInfo(recruitmentInfoReq);
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/delRecruitmentInfo")
    @ApiOperation(value = "人才中心管理",notes = "删除人才中心数据")
    public ApiResponse delRecruitmentInfo(@RequestBody Map<String, String> param) {
        recruitmentInfoService.delRecruitmentInfo(param.get("id"));
        return ApiResponse.returnSuccess();
    }

}
