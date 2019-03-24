package com.commerce.huayi.controller.admin;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.DelDataReq;
import com.commerce.huayi.entity.request.NewsInfoReq;
import com.commerce.huayi.service.NewsInfoService;
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
@RequestMapping(value = "/api/admin/newsInfo")
@Api(value = "新闻中心管理")
public class NewsInfoForAdminController {

    @Autowired
    private NewsInfoService newsInfoService;

    @PostMapping(value = "/addNewsInfo")
    @ApiOperation(value = "新闻管理",notes = "添加新闻")
    public ApiResponse addNewsInfo(@Valid @RequestBody NewsInfoReq newsInfoReq, BindingResult bindingResult) {
        newsInfoService.addNewsInfo(newsInfoReq);
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/delNewsInfo")
    @ApiOperation(value = "新闻管理",notes = "删除新闻")
    public ApiResponse delNewsInfo(@RequestBody DelDataReq param) {
        newsInfoService.delNewsInfo(param.getId());
        return ApiResponse.returnSuccess();
    }

}
