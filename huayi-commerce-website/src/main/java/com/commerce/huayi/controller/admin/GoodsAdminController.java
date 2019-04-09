package com.commerce.huayi.controller.admin;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.api.ApiResponseEnum;
import com.commerce.huayi.entity.request.*;
import com.commerce.huayi.entity.response.GoodsSpecValuePageVo;
import com.commerce.huayi.entity.response.GoodsSpecValueVo;
import com.commerce.huayi.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/goods")
@Api(tags = "后台产品管理")
public class GoodsAdminController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping(value = "/addCategory")
    @ApiOperation(value = "添加分类",notes = "添加产品分类")
    public ApiResponse addCategory(@RequestBody CategoryReq categoryReq) {
        ApiResponseEnum responseEnum = goodsService.addCategory(categoryReq);
        if(ApiResponseEnum.SUCCESS == responseEnum) {
            return ApiResponse.returnSuccess();
        }
        return ApiResponse.returnFail(responseEnum);
    }

    @PostMapping(value = "/deleteCategory")
    @ApiOperation(value = "删除分类",notes = "删除产品分类")
    public ApiResponse deleteCategory(@RequestBody PrimaryKeyReq req) {
        Integer result = goodsService.deleteCategory(req.getId());
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/updateCategory")
    @ApiOperation(value = "更新分类",notes = "更新产品分类")
    public ApiResponse updateCategory(@RequestBody UpdateCategoryReq req) {
        Integer result = goodsService.updateCategory(req);
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/addGoods")
    @ApiOperation(value = "添加产品单元",notes = "添加产品单元")
    public ApiResponse addGoods(@RequestBody AddGoodsReq addGoodsReq) {
        ApiResponseEnum responseEnum = goodsService.addGoods(addGoodsReq);
        if(ApiResponseEnum.SUCCESS == responseEnum) {
            return ApiResponse.returnSuccess();
        }
        return ApiResponse.returnFail(responseEnum);
    }

    @PostMapping(value = "/deleteGoods")
    @ApiOperation(value = "删除产品单元",notes = "删除产品单元")
    public ApiResponse deleteGoods(@RequestBody PrimaryKeyReq req) {
        ApiResponseEnum responseEnum = goodsService.deleteGoods(req.getId());
        if(ApiResponseEnum.SUCCESS == responseEnum) {
            return ApiResponse.returnSuccess();
        }
        return ApiResponse.returnFail(responseEnum);
    }

    public ApiResponse updateGoods() {
        return null;
    }


    @PostMapping(value = "/image/addGoodsImage")
    @ApiOperation(value = "添加产品图片",notes = "添加产品单元图片")
    public ApiResponse addGoodsImage(PrimaryKeyReq req,
                                     @RequestParam(value = "goodsImage") MultipartFile goodsImage) {
        byte[] bytes;
        try {
            bytes = goodsImage.getBytes();
        } catch (IOException e) {
            bytes = null;
        }
        ApiResponseEnum responseEnum = goodsService.addGoodsImage(req.getId(),bytes);
        if(ApiResponseEnum.SUCCESS == responseEnum) {
            return ApiResponse.returnSuccess();
        }
        return ApiResponse.returnFail(responseEnum);
    }

    @PostMapping(value = "/addSpecInfo")
    @ApiOperation(value = "添加规格",notes = "添加产品规格")
    public ApiResponse addSpecInfo(@RequestBody AddSpuSpecReq req) {
        ApiResponseEnum responseEnum = goodsService.addSpecInfo(req);
        if(ApiResponseEnum.SUCCESS == responseEnum) {
            return ApiResponse.returnSuccess();
        }
        return ApiResponse.returnFail(responseEnum);
    }

    @PostMapping(value = "/getSpecInfoList")
    @ApiOperation(value = "获取产品规格列表",notes = "获取产品规格列表")
    public ApiResponse<GoodsSpecValuePageVo> getSpecInfoList(@RequestBody PageReq pageReq) {
        return ApiResponse.returnSuccess(goodsService.getSpecInfoList(pageReq));
    }
}