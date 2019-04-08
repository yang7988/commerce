package com.commerce.huayi.controller.admin;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.api.ApiResponseEnum;
import com.commerce.huayi.entity.request.AddGoodsReq;
import com.commerce.huayi.entity.request.CategoryReq;
import com.commerce.huayi.entity.request.PrimaryKeyReq;
import com.commerce.huayi.entity.request.UpdateCategoryReq;
import com.commerce.huayi.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/admin/goodsCategory")
@Api(value = "后台产品分类管理")
public class GoodsAdminController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping(value = "/addCategory")
    @ApiOperation(value = "添加分类",notes = "添加产品分类")
    public ApiResponse addCategory(@Valid @RequestBody CategoryReq categoryReq, BindingResult bindingResult) {
        Integer result = goodsService.addCategory(categoryReq);
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/deleteCategory")
    @ApiOperation(value = "删除分类",notes = "删除产品分类")
    public ApiResponse deleteCategory(@Valid @RequestBody PrimaryKeyReq req, BindingResult bindingResult) {
        Integer result = goodsService.deleteCategory(req.getId());
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/updateCategory")
    @ApiOperation(value = "更新分类",notes = "更新产品分类")
    public ApiResponse updateCategory(@Valid @RequestBody UpdateCategoryReq req, BindingResult bindingResult) {
        Integer result = goodsService.updateCategory(req);
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/addGoods")
    @ApiOperation(value = "添加产品单元",notes = "添加产品单元")
    public ApiResponse addGoods(@Valid @RequestBody AddGoodsReq addGoodsReq, BindingResult bindingResult) {
        ApiResponseEnum responseEnum = goodsService.addGoods(addGoodsReq);
        if(ApiResponseEnum.SUCCESS == responseEnum) {
            return ApiResponse.returnSuccess();
        }
        return ApiResponse.returnFail(responseEnum);
    }

    @PostMapping(value = "/deleteGoods")
    @ApiOperation(value = "删除产品单元",notes = "删除产品单元")
    public ApiResponse deleteGoods(@Valid @RequestBody PrimaryKeyReq req, BindingResult bindingResult) {
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
                                     @RequestParam(value = "goodsImage",required = false) MultipartFile goodsImage) {
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

}