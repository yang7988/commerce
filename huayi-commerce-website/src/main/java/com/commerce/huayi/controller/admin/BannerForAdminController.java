package com.commerce.huayi.controller.admin;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.BannerReq;
import com.commerce.huayi.entity.request.DelDataReq;
import com.commerce.huayi.entity.response.BannerVo;
import com.commerce.huayi.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/banner")
@Api(tags = "官网banner管理")
public class BannerForAdminController {

    @Autowired
    private BannerService bannerService;

    // 获取banner列表
    @PostMapping(value = "/getBannerList")
    @ApiOperation(value = "官网banner管理", notes = "获取banner列表")
    public ApiResponse<List<BannerVo>> getBannerList() {
        return ApiResponse.returnSuccess(bannerService.getBannerList());
    }

    // 添加banner
    @PostMapping(value = "/addBanner")
    @ApiOperation(value = "官网banner管理", notes = "添加banner")
    public ApiResponse addBanner(@RequestBody BannerReq bannerReq) {
        bannerService.addBanner(bannerReq);
        return ApiResponse.returnSuccess();
    }

    // 删除banner
    @PostMapping(value = "/delBanner")
    @ApiOperation(value = "官网banner管理", notes = "删除banner")
    public ApiResponse delBanner(@RequestBody DelDataReq delDataReq) {
        bannerService.delBanner(delDataReq);
        return ApiResponse.returnSuccess();
    }

    // 更新banner
    @PostMapping(value = "/updateBanner")
    @ApiOperation(value = "官网banner管理", notes = "更新banner信息")
    public ApiResponse updateBanner(@RequestBody BannerReq bannerReq) {
        bannerService.updateBanner(bannerReq);
        return ApiResponse.returnSuccess();
    }

}
