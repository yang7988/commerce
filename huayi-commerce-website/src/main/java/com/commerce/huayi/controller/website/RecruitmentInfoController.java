package com.commerce.huayi.controller.website;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.DelDataReq;
import com.commerce.huayi.entity.response.RecruitmentInfoVo;
import com.commerce.huayi.service.RecruitmentInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/recruitmentInfo")
@Api(value = "人才中心")
public class RecruitmentInfoController {

    @Autowired
    private RecruitmentInfoService recruitmentInfoService;

    @PostMapping(value = "/getRecruitmentInfos")
    @ApiOperation(value = "人才中心",notes = "获取人才中心信息")
    public ApiResponse<List<RecruitmentInfoVo>> getRecruitmentInfos() {
        return ApiResponse.returnSuccess(recruitmentInfoService.getRecruitmentInfos());
    }

    @PostMapping(value = "/getRecruitmentInfo")
    @ApiOperation(value = "人才中心",notes = "获取人才中心明细")
    public ApiResponse<RecruitmentInfoVo> getRecruitmentInfo(@RequestBody DelDataReq param) {
        return ApiResponse.returnSuccess(recruitmentInfoService.getRecruitmentInfo(param.getId()));
    }

}
