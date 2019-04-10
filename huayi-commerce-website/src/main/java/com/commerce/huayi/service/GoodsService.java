package com.commerce.huayi.service;

import com.commerce.huayi.api.ApiResponseEnum;
import com.commerce.huayi.api.BusinessException;
import com.commerce.huayi.entity.request.*;
import com.commerce.huayi.entity.response.CategoryVo;
import com.commerce.huayi.entity.response.GoodsSpecValuePageVo;
import com.commerce.huayi.entity.response.GoodsSpuDetailsVo;
import com.commerce.huayi.pagination.Page;

import java.util.List;

/**
 * 产品服务类
 */
public interface GoodsService {

    /**
     *
     * @param parentId 产品分类父id(一级分类parentId为0)
     * @return 返回ApiResponse接口响应对象
     * @throws BusinessException 抛出业务异常
     */
    List<CategoryVo> getCategories(Long parentId) throws BusinessException;

    List<GoodsSpuDetailsVo> categoryGoods(Long id)  throws BusinessException;

    ApiResponseEnum addCategory(CategoryReq categoryReq);

    Integer deleteCategory(Long id);

    Integer updateCategory(UpdateCategoryReq req);

    byte[] getGoodsImage(Long goodsId);

    ApiResponseEnum addGoods(AddGoodsReq addGoodsReq);

    ApiResponseEnum deleteGoods(Long goodsId);

    ApiResponseEnum addGoodsImage(Long goodsId,byte[] bytes);

    ApiResponseEnum addSpecInfo(AddSpuSpecReq addSpuSpecReq);

    GoodsSpecValuePageVo getSpecInfoList(PageRequest pageRequest);

    List<GoodsSpuDetailsVo> populateGoods(Long id);

    ApiResponseEnum addPopulateGoods(AddPopulateGoodsReq req);

    ApiResponseEnum delPopulateGoods(AddPopulateGoodsReq req);

    List<GoodsSpuDetailsVo> search(String keyWord,String language);

    Page<GoodsSpuDetailsVo> pageCategoryGoods(Long id, int pageIndex, int pageMaxSize);
}
