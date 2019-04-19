package com.commerce.huayi.controller.website;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.response.BannerVo;
import com.commerce.huayi.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/banner")
@Api(tags = "官网banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    // 获取banner列表
    @PostMapping(value = "/getBannerList")
    @ApiOperation(value = "官网banner管理", notes = "获取banner")
    public ApiResponse<List<BannerVo>> getBannerList() {
        return ApiResponse.returnSuccess(bannerService.getBannerList());
    }

}
