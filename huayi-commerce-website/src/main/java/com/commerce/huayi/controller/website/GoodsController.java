package com.commerce.huayi.controller.website;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.PrimaryKeyReq;
import com.commerce.huayi.entity.response.CategoryVo;
import com.commerce.huayi.entity.response.GoodsSpuDetailsVo;
import com.commerce.huayi.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/goods")
@Api(tags = "前台商品及分类")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping(value = "/categories")
    @ApiOperation(value = "产品分类管理",notes = "获取产品分类")

    public ApiResponse<List<CategoryVo>> categories(@RequestBody PrimaryKeyReq req) {
        Long parentId = req.getId();
        List<CategoryVo> categories = goodsService.getCategories(parentId);
        return ApiResponse.returnSuccess(categories);
    }

    @PostMapping(value = "/categoryGoods")
    @ApiOperation(value = "获取分类的产品",notes = "获取分类下面的所有产品单元")
    public ApiResponse<List<GoodsSpuDetailsVo>> categoryGoods(@RequestBody PrimaryKeyReq req){
        return ApiResponse.returnSuccess(goodsService.categoryGoods(req.getId()));
    }

    @RequestMapping(value = { "/image/{goodsId}" }, method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE )
    @ResponseBody
    @ApiOperation(value = "获取产品图片",notes = "获取产品单元图片GET请求")
    public byte[] getImg(@PathVariable Long goodsId) throws IOException {
        return goodsService.getGoodsImage(goodsId);
    }

    @PostMapping(value = "/category/populate/goods")
    @ApiOperation(value = "获取分类的主推热门产品",notes = "获取分类的主推热门产品")
    public ApiResponse<List<GoodsSpuDetailsVo>> populateGoods(@RequestBody PrimaryKeyReq req){
        return ApiResponse.returnSuccess(goodsService.populateGoods(req.getId()));
    }
}