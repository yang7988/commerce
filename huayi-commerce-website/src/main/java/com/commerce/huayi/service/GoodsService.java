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
     *
     * @param id 产品分类id()
     * @return 返回ApiResponse接口响应对象
     * @throws BusinessException 抛出业务异常
     */
    Page<CategoryVo> getCategories(Long id, String name,int pageIndex, int pageMaxSize) throws BusinessException;

    Page<GoodsSpuVo> categoryGoods(Long id, int pageIndex, int pageMaxSize)  throws BusinessException;

    ApiResponseEnum addCategory(CategoryReq categoryReq);

    Integer deleteCategory(Long id);

    Integer updateCategory(UpdateCategoryReq req);

    byte[] getGoodsImage(Long goodsId);

    GoodsSpuVo addGoods(AddGoodsReq addGoodsReq);

    ApiResponseEnum deleteGoods(Long goodsId);

    ApiResponseEnum addSpecInfo(AddSpuSpecReq addSpuSpecReq);

    Page<GoodsSpecValueVo> getSpecInfoList(PageRequest pageRequest);

    Page<GoodsSpuDetailsVo> populateGoods(Long id, int pageIndex, int pageMaxSize);

    ApiResponseEnum addPopulateGoods(AddPopulateGoodsReq req);

    ApiResponseEnum delPopulateGoods(AddPopulateGoodsReq req);

    List<GoodsSpuVo> search(String keyWord,String language);

    Page<GoodsSpuDetailsVo> goodsDetails(Long id, int pageIndex, int pageMaxSize);

    ApiResponseEnum addGoodsSpec(AddGoodsSpecReq req);

    GoodsSpuDetailsVo goodsSpecDetails(Long id, Long specValueId);

    ApiResponseEnum updateGoods(UpdateGoodsReq req,String language);
}
