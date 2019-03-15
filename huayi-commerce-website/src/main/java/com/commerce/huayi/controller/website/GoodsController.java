package com.commerce.huayi.controller.website;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.response.CategoryVo;
import com.commerce.huayi.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/goods")
@Api(value = "产品管理")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/categories/{id}")
    @ApiOperation(value = "产品分类管理",notes = "获取产品分类")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",paramType = "long",required = false)
    )
    public ApiResponse categories(@PathVariable(name = "id", required = false) Long id) {
        Long parentId = id == null ? 0L : id;
        List<CategoryVo> categories = goodsService.getCategories(parentId);
        return ApiResponse.returnSuccess(categories);
    }

    @RequestMapping(value = "/allCategories")
    public ApiResponse allCategories() {
        List<CategoryVo> allCategories = goodsService.getAllCategories();
        return ApiResponse.returnSuccess(allCategories);
    }
}