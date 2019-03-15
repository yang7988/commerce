package com.commerce.huayi.service.impl;

import com.commerce.huayi.annotation.Translate;
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

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    @Translate
    public List<CategoryVo> getCategories(Long parentId) throws BusinessException {
        LOGGER.warn("getGoodsCategory========parentId====" + parentId);
        GoodsCategoryExample example = new GoodsCategoryExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        List<GoodsCategory> goodsCategories = goodsCategoryMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(goodsCategories)) {
            return null;
        }
        return BeanCopyUtil.copy(CategoryVo.class, goodsCategories);
    }



}