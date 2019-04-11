package com.commerce.huayi.controller.website;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.entity.request.PageCategoryGoodsRequest;
import com.commerce.huayi.entity.request.PrimaryKeyRequest;
import com.commerce.huayi.entity.request.SearchGoodsReq;
import com.commerce.huayi.entity.response.CategoryVo;
import com.commerce.huayi.entity.response.GoodsSpuDetailsVo;
import com.commerce.huayi.pagination.Page;
import com.commerce.huayi.service.GoodsService;
import com.commerce.huayi.utils.ServletUtils;
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
    public ApiResponse<Page<CategoryVo>> categories(@RequestBody PageCategoryGoodsRequest req) {
        Page<CategoryVo> page = goodsService.getCategories(req.getId(), req.getName(), req.getPageIndex(), req.getPageMaxSize());
        return ApiResponse.returnSuccess(page);
    }

    @PostMapping(value = "/categoryGoods")
    @ApiOperation(value = "获取分类的全部产品",notes = "获取分类的全部产品此接口不分页")
    public ApiResponse<Page<GoodsSpuDetailsVo>> categoryGoods(@RequestBody PageCategoryGoodsRequest req){
        Page<GoodsSpuDetailsVo> page = goodsService.categoryGoods(req.getId(), req.getPageIndex(), req.getPageMaxSize());
        return ApiResponse.returnSuccess(page);
    }


    @RequestMapping(value = { "/image/{goodsId}" }, method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE )
    @ResponseBody
    @ApiOperation(value = "获取产品图片",notes = "获取产品单元图片GET请求")
    public byte[] getImg(@PathVariable Long goodsId) throws IOException {
        return goodsService.getGoodsImage(goodsId);
    }

    @PostMapping(value = "/category/populate/goods")
    @ApiOperation(value = "获取分类的主推热门产品",notes = "获取分类的主推热门产品")
    public ApiResponse<Page<GoodsSpuDetailsVo>> populateGoods(@RequestBody PageCategoryGoodsRequest req){
        return ApiResponse.returnSuccess(goodsService.populateGoods(req.getId(),req.getPageIndex(),req.getPageMaxSize()));
    }

    @PostMapping(value = "/search")
    @ApiOperation(value = "按产品名模糊搜索产品",notes = "按产品名模糊搜索产品")
    public ApiResponse<List<GoodsSpuDetailsVo>> search(@RequestBody SearchGoodsReq req) {
        return ApiResponse.returnSuccess(goodsService.search(req.getKeyWord(), ServletUtils.language().getLanguage()));
    }
}