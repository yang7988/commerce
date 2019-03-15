package com.commerce.huayi.controller.website;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.GetCategoryReq;
import com.commerce.huayi.entity.response.CategoryVo;
import com.commerce.huayi.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/goods")
@Api(value = "产品管理")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping(value = "/categories")
    @ApiOperation(value = "产品分类管理",notes = "获取产品分类")

    public ApiResponse categories(@Valid @RequestBody GetCategoryReq categoryReq, BindingResult bindingResult) {
        Long parentId = categoryReq.getId() == null ? 0L : categoryReq.getId();
        List<CategoryVo> categories = goodsService.getCategories(parentId);
        return ApiResponse.returnSuccess(categories);
    }

}