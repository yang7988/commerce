package com.commerce.huayi.controller.website;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.DelDataReq;
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
@RequestMapping(value = "/api/newsInfo")
@Api(tags = "新闻中心")
public class NewsInfoController {

    @Autowired
    private NewsInfoService newsInfoService;

    @PostMapping(value = "/getNewsInfos")
    @ApiOperation(value = "新闻中心",notes = "获取新闻列表")
    public ApiResponse<Page<NewsInfoVo>> getNewsInfos(@RequestBody NewsListReq pageRequest) {
        return ApiResponse.returnSuccess(newsInfoService.getNewsInfos(pageRequest));
    }

    @PostMapping(value = "/getNewsInfo")
    @ApiOperation(value = "新闻中心",notes = "获取新闻明细")
    public ApiResponse<NewsInfoVo> getNewsInfo(@RequestBody DelDataReq param) {
        return ApiResponse.returnSuccess(newsInfoService.getNewsInfo(param.getId()));
    }

}
