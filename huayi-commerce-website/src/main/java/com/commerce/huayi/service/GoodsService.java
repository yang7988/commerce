package com.commerce.huayi.service;

import com.commerce.huayi.api.ApiResponseEnum;
import com.commerce.huayi.api.BusinessException;
import com.commerce.huayi.entity.request.*;
import com.commerce.huayi.entity.response.CategoryVo;
import com.commerce.huayi.entity.response.GoodsSpecValueVo;
import com.commerce.huayi.entity.response.GoodsSpuDetailsVo;
import com.commerce.huayi.entity.response.GoodsSpuVo;
import com.commerce.huayi.pagination.Page;

import java.util.List;

/**
 * 产品服务类
 */
public interface GoodsService {

    /**
     * @param id 产品分类id()
     * @return 返回ApiResponse接口响应对象
     * @throws BusinessException 抛出业务异常
     */
    List<CategoryVo> getCategories(Long id, String name) throws BusinessException;

    Page<GoodsSpuVo> categoryGoods(Long id, int pageIndex, int pageMaxSize) throws BusinessException;

    ApiResponseEnum addCategory(CategoryReq categoryReq);

    Integer deleteCategory(Long id);

    ApiResponseEnum updateCategory(UpdateCategoryReq req);

    byte[] getGoodsImage(Long goodsId);

    GoodsSpuVo addGoods(AddGoodsReq addGoodsReq);

    ApiResponseEnum deleteGoods(Long goodsId);

    AddSpuSpecValueReq addSpecInfo(AddSpuSpecReq addSpuSpecReq);

    Page<GoodsSpecValueVo> getSpecInfoList(PageRequest pageRequest);

    Page<GoodsSpuVo> search(String keyWord,int pageIndex, int pageMaxSize);

    Page<GoodsSpuDetailsVo> goodsDetails(Long id, int pageIndex, int pageMaxSize);

    GoodsSpuDetailsVo goodsSpecDetails(Long id, Long specValueId);

    ApiResponseEnum updateGoods(UpdateGoodsReq req);

    ApiResponseEnum updateSpecInfo(UpdateSpuSpecReq req);

    ApiResponseEnum addGoodsSpec(AddGoodsSpecReq req);
}
