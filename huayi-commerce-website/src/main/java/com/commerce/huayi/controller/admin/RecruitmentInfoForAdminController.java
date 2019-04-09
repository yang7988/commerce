package com.commerce.huayi.controller.admin;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.DelDataReq;
import com.commerce.huayi.entity.request.RecruitmentInfoReq;
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
@RequestMapping(value = "/api/admin/recruitmentInfo")
@Api(value = "人才中心管理")
public class RecruitmentInfoForAdminController {

    @Autowired
    private RecruitmentInfoService recruitmentInfoService;

    @PostMapping(value = "/getRecruitmentInfos")
    @ApiOperation(value = "人才中心管理",notes = "获取人才中心信息")
    public ApiResponse<List<RecruitmentInfoVo>> getRecruitmentInfos() {
        return ApiResponse.returnSuccess(recruitmentInfoService.getRecruitmentInfos());
    }

    @PostMapping(value = "/getRecruitmentInfo")
    @ApiOperation(value = "人才中心管理",notes = "获取人才中心明细")
    public ApiResponse<RecruitmentInfoVo> getRecruitmentInfo(@RequestBody DelDataReq param) {
        return ApiResponse.returnSuccess(recruitmentInfoService.getRecruitmentInfo(param.getId()));
    }

    @PostMapping(value = "/addRecruitmentInfo")
    @ApiOperation(value = "人才中心管理",notes = "添加人才中心数据")
    public ApiResponse addRecruitmentInfo(@RequestBody RecruitmentInfoReq recruitmentInfoReq) {
        recruitmentInfoService.addRecruitmentInfo(recruitmentInfoReq);
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/delRecruitmentInfo")
    @ApiOperation(value = "人才中心管理",notes = "删除人才中心数据")
    public ApiResponse delRecruitmentInfo(@RequestBody DelDataReq param) {
        recruitmentInfoService.delRecruitmentInfo(param.getId());
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/updateRecruitmentInfo")
    @ApiOperation(value = "人才中心管理",notes = "更新人才中心数据")
    public ApiResponse updateRecruitmentInfo(@RequestBody RecruitmentInfoReq recruitmentInfoReq) {
        recruitmentInfoService.updateRecruitmentInfo(recruitmentInfoReq);
        return ApiResponse.returnSuccess();
    }

}
