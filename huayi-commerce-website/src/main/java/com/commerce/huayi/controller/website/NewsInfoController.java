package com.commerce.huayi.controller.website;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.service.NewsInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/newsInfo")
@Api(value = "新闻中心")
public class NewsInfoController {

    @Autowired
    private NewsInfoService newsInfoService;

    @PostMapping(value = "/getNewsInfos")
    @ApiOperation(value = "新闻中心",notes = "获取新闻")
    public ApiResponse getNewsInfos() {
        return ApiResponse.returnSuccess(newsInfoService.getNewsInfos());
    }

}
