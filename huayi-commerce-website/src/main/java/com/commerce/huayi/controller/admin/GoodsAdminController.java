package com.commerce.huayi.controller.admin;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.api.ApiResponseEnum;
import com.commerce.huayi.entity.request.AddGoodsReq;
import com.commerce.huayi.entity.request.CategoryReq;
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
    public ApiResponse deleteCategory(@Valid @RequestBody CategoryReq categoryReq, BindingResult bindingResult) {
        Integer result = goodsService.deleteCategory(categoryReq);
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/updateCategory")
    @ApiOperation(value = "更新分类",notes = "更新产品分类")
    public ApiResponse updateCategory(@Valid @RequestBody CategoryReq categoryReq, BindingResult bindingResult) {
        Integer result = goodsService.updateCategory(categoryReq);
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
    public ApiResponse deleteGoods(@Valid @RequestBody AddGoodsReq addGoodsReq, BindingResult bindingResult) {
        ApiResponseEnum responseEnum = goodsService.deleteGoods(addGoodsReq);
        if(ApiResponseEnum.SUCCESS == responseEnum) {
            return ApiResponse.returnSuccess();
        }
        return ApiResponse.returnFail(responseEnum);
    }

    public ApiResponse updateGoods() {
        return null;
    }


    @PostMapping(value = "/image/addGoodsImage")
    @ApiOperation(value = "删除产品单元",notes = "删除产品单元")
    public ApiResponse addGoodsImage(AddGoodsReq addGoodsReq,
                                     @RequestParam(value = "goodsImage",required = false) MultipartFile goodsImage) {
        if(addGoodsReq.getGoodsId() == null || addGoodsReq.getCategoryId() == null) {
            return ApiResponse.returnFail(ApiResponseEnum.PARAMETER_CANT_BE_EMPTY);
        }
        byte[] bytes;
        try {
            bytes = goodsImage.getBytes();
        } catch (IOException e) {
            bytes = null;
        }
        ApiResponseEnum responseEnum = goodsService.addGoodsImage(addGoodsReq.getCategoryId(),addGoodsReq.getGoodsId(),bytes);
        if(ApiResponseEnum.SUCCESS == responseEnum) {
            return ApiResponse.returnSuccess();
        }
        return ApiResponse.returnFail(responseEnum);
    }

}