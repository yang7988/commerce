package com.commerce.huayi.service.impl;

import com.commerce.huayi.api.BusinessException;
import com.commerce.huayi.constant.Constant;
import com.commerce.huayi.entity.db.*;
import com.commerce.huayi.entity.request.CategoryReq;
import com.commerce.huayi.entity.response.CategoryVo;
import com.commerce.huayi.entity.response.GoodsSpuDetailsVo;
import com.commerce.huayi.mapper.*;
import com.commerce.huayi.service.GoodsService;
import com.commerce.huayi.utils.BeanCopyUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    private GoodsCategorySpecMapper goodsCategorySpecMapper;

    @Autowired
    private GoodsSpuMapper goodsSpuMapper;

    @Autowired
    private GoodsSpecMapper goodsSpecMapper;

    @Autowired
    private GoodsSpuSpecMapper goodsSpuSpecMapper;

    @Autowired
    private GoodsSpecValueMapper goodsSpecValueMapper;

    @Override
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

    @Override
    public List<GoodsSpuDetailsVo> categoryGoods(Long id) throws BusinessException {
        GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(id);
        if (goodsCategory == null) {
            return null;
        }
        GoodsCategorySpecExample goodsCategorySpecExample = new GoodsCategorySpecExample();
        goodsCategorySpecExample.createCriteria().andCategoryIdEqualTo(goodsCategory.getId());
        List<GoodsCategorySpec> goodsCategorySpecs = goodsCategorySpecMapper.selectByExample(goodsCategorySpecExample);
        if(CollectionUtils.isEmpty(goodsCategorySpecs)) {
            return null;
        }
        List<Long> specValueIds = goodsCategorySpecs.stream().map(GoodsCategorySpec::getSpecValueId).collect(Collectors.toList());
        GoodsSpuSpecExample goodsSpuSpecExample = new GoodsSpuSpecExample();
        goodsSpuSpecExample.createCriteria().andSpecValueIdIn(specValueIds);
        List<GoodsSpuSpec> goodsSpuSpecs = goodsSpuSpecMapper.selectByExample(goodsSpuSpecExample);

        List<GoodsSpuDetailsVo> goodsSpuDetailsVos = new ArrayList<>();
        if(CollectionUtils.isEmpty(goodsSpuSpecs)) {
            return null;
        }
        goodsSpuSpecs.forEach(goodsSpuSpec -> goodsSpuDetailsVos.add(getSpuDeatis(goodsSpuSpec)));
        return goodsSpuDetailsVos;
    }

    private GoodsSpuDetailsVo getSpuDeatis(GoodsSpuSpec goodsSpuSpec) {
        GoodsSpu goodsSpu = goodsSpuMapper.selectByPrimaryKey(goodsSpuSpec.getSpuId());
        GoodsSpuDetailsVo goodsSpuDetailsVo = BeanCopyUtil.copy(GoodsSpuDetailsVo.class, goodsSpu);
        GoodsSpecValue goodsSpecValue = goodsSpecValueMapper.selectByPrimaryKey(goodsSpuSpec.getSpecValueId());
        BeanCopyUtil.copy(goodsSpuDetailsVo, goodsSpecValue);
        GoodsSpec goodsSpec = goodsSpecMapper.selectByPrimaryKey(goodsSpecValue.getSpecId());
        BeanCopyUtil.copy(goodsSpuDetailsVo, goodsSpec);
        return goodsSpuDetailsVo;
    }

    @Override
    public Integer addCategory(CategoryReq categoryReq) {
        GoodsCategory goodsCategory = BeanCopyUtil.copy(GoodsCategory.class, categoryReq);
        goodsCategory.setIsDelete(Constant.NODELETE);
        goodsCategory.setCreateDate(new Date());
        goodsCategory.setUpdateDate(new Date());
        return goodsCategoryMapper.insertSelective(goodsCategory);
    }

    @Override
    public Integer deleteCategory(CategoryReq categoryReq) {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setIsDelete(Constant.DELETED);
        goodsCategory.setId(categoryReq.getId());
        return goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory);
    }

    @Override
    public Integer updateCategory(CategoryReq categoryReq) {
        GoodsCategory goodsCategory = BeanCopyUtil.copy(GoodsCategory.class, categoryReq);
        goodsCategory.setUpdateDate(new Date());
        return goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory);
    }
}