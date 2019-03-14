package com.commerce.huayi.service.impl;

import com.alibaba.fastjson.JSON;
import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.api.BusinessException;
import com.commerce.huayi.entity.db.GoodsCategory;
import com.commerce.huayi.entity.db.GoodsCategoryExample;
import com.commerce.huayi.entity.response.CategoryVo;
import com.commerce.huayi.mapper.GoodsCategoryMapper;
import com.commerce.huayi.service.GoodsService;
import com.commerce.huayi.utils.BeanCopyUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public ApiResponse getCategories(Long parentId) throws BusinessException {
        LOGGER.warn("getGoodsCategory========parentId====" + parentId);
        GoodsCategoryExample example = new GoodsCategoryExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        List<GoodsCategory> goodsCategories = goodsCategoryMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(goodsCategories)) {
            return ApiResponse.returnSuccess();
        }
        List<CategoryVo> categoryVos = BeanCopyUtil.copy(CategoryVo.class, goodsCategories);
        return ApiResponse.returnSuccess(categoryVos);
    }


    public static void main(String[] args) {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setId(1L);
        goodsCategory.setParentId(0L);
        goodsCategory.setCategoryName("头戴式耳机");
        goodsCategory.setCategoryDescription("用于头戴式耳机");
        goodsCategory.setCategoryImageKey("");
        goodsCategory.setCreateDate(new Date());
        goodsCategory.setUpdateDate(new Date());
        goodsCategory.setIsDelete((byte) 0);
        GoodsCategory goodsCategory1 = new GoodsCategory();
        goodsCategory1.setId(2L);
        goodsCategory1.setParentId(0L);
        goodsCategory1.setCategoryName("入耳式耳机");
        goodsCategory1.setCategoryDescription("用于入耳式耳机");
        goodsCategory1.setCategoryImageKey("");
        goodsCategory1.setCreateDate(new Date());
        goodsCategory1.setUpdateDate(new Date());
        goodsCategory1.setIsDelete((byte) 0);
        List<GoodsCategory> goodsCategories = Arrays.asList(goodsCategory, goodsCategory1);
        List<CategoryVo> categoryVos = BeanCopyUtil.copy(CategoryVo.class, goodsCategories);
        System.out.println(JSON.toJSONString(categoryVos));
    }
}