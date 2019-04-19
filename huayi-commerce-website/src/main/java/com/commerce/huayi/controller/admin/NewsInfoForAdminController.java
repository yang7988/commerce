package com.commerce.huayi.controller.admin;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.DelDataReq;
import com.commerce.huayi.entity.request.NewsInfoReq;
import com.commerce.huayi.entity.request.NewsListReq;
import com.commerce.huayi.entity.response.NewsInfoVo;
import com.commerce.huayi.pagination.Page;
import com.commerce.huayi.service.NewsInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin/newsInfo")
@Api(tags = "新闻中心管理")
public class NewsInfoForAdminController {

    @Autowired
    private NewsInfoService newsInfoService;

    @PostMapping(value = "/getNewsInfos")
    @ApiOperation(value = "新闻中心管理", notes = "获取新闻列表")
    public ApiResponse<Page<NewsInfoVo>> getNewsInfos(@RequestBody NewsListReq pageRequest) {
        return ApiResponse.returnSuccess(newsInfoService.getNewsInfos(pageRequest));
    }

    @PostMapping(value = "/getNewsInfo")
    @ApiOperation(value = "新闻中心管理", notes = "获取新闻明细")
    public ApiResponse<NewsInfoVo> getNewsInfo(@RequestBody DelDataReq param) {
        return ApiResponse.returnSuccess(newsInfoService.getNewsInfo(param.getId()));
    }

    @PostMapping(value = "/addNewsInfo")
    @ApiOperation(value = "新闻中心管理", notes = "添加新闻")
    public ApiResponse addNewsInfo(@RequestBody NewsInfoReq newsInfoReq) {
        newsInfoService.addNewsInfo(newsInfoReq);
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/delNewsInfo")
    @ApiOperation(value = "新闻中心管理", notes = "删除新闻")
    public ApiResponse delNewsInfo(@RequestBody DelDataReq param) {
        newsInfoService.delNewsInfo(param.getId());
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/updateNewsInfo")
    @ApiOperation(value = "新闻中心管理", notes = "更新新闻明细")
    public ApiResponse updateNewsInfo(@RequestBody NewsInfoReq newsInfoReq) {
        newsInfoService.updateNewsInfo(newsInfoReq);
        return ApiResponse.returnSuccess();
    }

}
