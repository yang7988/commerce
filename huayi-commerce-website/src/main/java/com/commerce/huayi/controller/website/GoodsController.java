package com.commerce.huayi.controller.website;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.CategoryReq;
import com.commerce.huayi.entity.response.CategoryVo;
import com.commerce.huayi.entity.response.GoodsSpuDetailsVo;
import com.commerce.huayi.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/goods")
@Api(value = "商品及分类管理")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping(value = "/categories")
    @ApiOperation(value = "产品分类管理",notes = "获取产品分类")

    public ApiResponse<List<CategoryVo>> categories(@Valid @RequestBody CategoryReq categoryReq, BindingResult bindingResult) {
        Long parentId = categoryReq.getId() == null ? 0L : categoryReq.getId();
        List<CategoryVo> categories = goodsService.getCategories(parentId);
        return ApiResponse.returnSuccess(categories);
    }

    @PostMapping(value = "/categoryGoods")
    @ApiOperation(value = "获取分类的产品",notes = "获取分类下面的所有产品单元")
    public ApiResponse<List<GoodsSpuDetailsVo>> categoryGoods(@Valid @RequestBody CategoryReq categoryReq, BindingResult bindingResult){
        return ApiResponse.returnSuccess(goodsService.categoryGoods(categoryReq.getId()));
    }

    @RequestMapping(value = { "/image/{category}/{imageKey}" }, method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE )
    @ResponseBody
    @ApiOperation(value = "获取产品图片",notes = "获取产品单元图片GET请求")
    public byte[] getImg(@PathVariable String category, @PathVariable String imageKey) throws IOException {
        return goodsService.getGoodsImage(category, imageKey);
    }
}