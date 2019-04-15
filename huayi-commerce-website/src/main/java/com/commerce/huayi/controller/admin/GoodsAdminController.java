package com.commerce.huayi.controller.admin;

import com.commerce.huayi.annotation.Pretreatment;
import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.api.ApiResponseEnum;
import com.commerce.huayi.entity.request.*;
import com.commerce.huayi.entity.response.GoodsSpecValueVo;
import com.commerce.huayi.entity.response.GoodsSpuVo;
import com.commerce.huayi.pagination.Page;
import com.commerce.huayi.service.GoodsService;
import com.commerce.huayi.utils.ServletUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin/goods")
@Api(tags = "后台产品管理")
public class GoodsAdminController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping(value = "/addCategory")
    @ApiOperation(value = "添加分类",notes = "添加产品分类")
    @Pretreatment
    public ApiResponse addCategory(@RequestBody CategoryReq categoryReq) {
        ApiResponseEnum responseEnum = goodsService.addCategory(categoryReq);
        if(ApiResponseEnum.SUCCESS == responseEnum) {
            return ApiResponse.returnSuccess();
        }
        return ApiResponse.returnFail(responseEnum);
    }

    @PostMapping(value = "/deleteCategory")
    @ApiOperation(value = "删除分类",notes = "删除产品分类")
    public ApiResponse deleteCategory(@RequestBody PrimaryKeyRequest req) {
        Integer result = goodsService.deleteCategory(req.getId());
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/updateCategory")
    @ApiOperation(value = "更新分类",notes = "更新产品分类")
    public ApiResponse updateCategory(@RequestBody UpdateCategoryReq req) {
        ApiResponseEnum responseEnum = goodsService.updateCategory(req,ServletUtils.language().getLanguage());
        if(ApiResponseEnum.SUCCESS == responseEnum) {
            return ApiResponse.returnSuccess();
        }
        return ApiResponse.returnFail(responseEnum);
    }

    @PostMapping(value = "/addGoods")
    @ApiOperation(value = "添加产品", notes = "添加产品")
    @Pretreatment
    public ApiResponse<GoodsSpuVo> addGoods(@RequestBody AddGoodsReq addGoodsReq) {
        GoodsSpuVo goodsSpuVo = goodsService.addGoods(addGoodsReq);
        if (goodsSpuVo == null) {
            return ApiResponse.returnFail(ApiResponseEnum.GOODS_NAME_EXISTS);
        }
        return ApiResponse.returnSuccess(goodsSpuVo);
    }
    @PostMapping(value = "/updateGoods")
    @ApiOperation(value = "跟新产品", notes = "跟新产品")
    public ApiResponse updateGoods(@RequestBody UpdateGoodsReq req) {
        ApiResponseEnum responseEnum = goodsService.updateGoods(req, ServletUtils.language().getLanguage());
        if(ApiResponseEnum.SUCCESS == responseEnum) {
            return ApiResponse.returnSuccess();
        }
        return ApiResponse.returnFail(responseEnum);
    }

    @PostMapping(value = "/addGoodsSpec")
    @ApiOperation(value = "为某一种产品添加不同的规格,以及添加该规格图", notes = "添加产品单元")
    public ApiResponse addGoodsSpec(@RequestBody AddGoodsSpecReq req) {
        ApiResponseEnum responseEnum = goodsService.addGoodsSpec(req);
        if(ApiResponseEnum.SUCCESS == responseEnum) {
            return ApiResponse.returnSuccess();
        }
        return ApiResponse.returnFail(responseEnum);
    }

    @PostMapping(value = "/deleteGoods")
    @ApiOperation(value = "删除产品单元",notes = "删除产品单元")
    public ApiResponse deleteGoods(@RequestBody PrimaryKeyRequest req) {
        ApiResponseEnum responseEnum = goodsService.deleteGoods(req.getId());
        if(ApiResponseEnum.SUCCESS == responseEnum) {
            return ApiResponse.returnSuccess();
        }
        return ApiResponse.returnFail(responseEnum);
    }

    @PostMapping(value = "/addSpecInfo")
    @ApiOperation(value = "添加规格",notes = "添加产品规格")
    @Pretreatment
    public ApiResponse addSpecInfo(@RequestBody AddSpuSpecReq req) {
        goodsService.addSpecInfo(req);
        return ApiResponse.returnSuccess();
    }

    @PostMapping(value = "/updateSpecInfo")
    @ApiOperation(value = "更新规格",notes = "更新产品规格")
    @Pretreatment
    public ApiResponse updateSpecInfo(@RequestBody UpdateSpuSpecReq req) {
        ApiResponseEnum responseEnum = goodsService.updateSpecInfo(req,ServletUtils.language().getLanguage());
        if(ApiResponseEnum.SUCCESS == responseEnum) {
            return ApiResponse.returnSuccess();
        }
        return ApiResponse.returnFail(responseEnum);
    }

    @PostMapping(value = "/getSpecInfoList")
    @ApiOperation(value = "获取产品规格列表",notes = "获取产品规格列表")
    public ApiResponse<Page<GoodsSpecValueVo>> getSpecInfoList(@RequestBody PageRequest pageRequest) {
        return ApiResponse.returnSuccess(goodsService.getSpecInfoList(pageRequest));
    }

    /*@PostMapping(value = "/addPopulateGoods")
    @ApiOperation(value = "添加热门主推产品",notes = "添加热门主推产品")
    public ApiResponse addPopulateGoods(@RequestBody AddPopulateGoodsReq req) {
        ApiResponseEnum responseEnum = goodsService.addPopulateGoods(req);
        if(ApiResponseEnum.SUCCESS == responseEnum) {
            return ApiResponse.returnSuccess();
        }
        return ApiResponse.returnFail(responseEnum);
    }*/

    /*@PostMapping(value = "/delPopulateGoods")
    @ApiOperation(value = "移除热门主推产品",notes = "移除热门主推产品")
    public ApiResponse delPopulateGoods(@RequestBody AddPopulateGoodsReq req) {
        ApiResponseEnum responseEnum = goodsService.delPopulateGoods(req);
        if(ApiResponseEnum.SUCCESS == responseEnum) {
            return ApiResponse.returnSuccess();
        }
        return ApiResponse.returnFail(responseEnum);
    }*/
}