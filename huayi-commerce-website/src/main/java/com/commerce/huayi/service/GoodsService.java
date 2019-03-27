package com.commerce.huayi.service;

import com.commerce.huayi.api.BusinessException;
import com.commerce.huayi.entity.request.CategoryReq;
import com.commerce.huayi.entity.response.CategoryVo;
import com.commerce.huayi.entity.response.GoodsSpuDetailsVo;

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

    Integer addCategory(CategoryReq categoryReq);

    Integer deleteCategory(CategoryReq categoryReq);
}
