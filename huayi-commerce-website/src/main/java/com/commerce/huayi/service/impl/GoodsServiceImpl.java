package com.commerce.huayi.service.impl;

import com.commerce.huayi.api.BusinessException;
import com.commerce.huayi.cache.JedisTemplate;
import com.commerce.huayi.cache.key.RedisKey;
import com.commerce.huayi.cache.key.RedisKeysPrefix;
import com.commerce.huayi.constant.Constant;
import com.commerce.huayi.constant.LanguageEnum;
import com.commerce.huayi.entity.db.*;
import com.commerce.huayi.entity.request.CategoryReq;
import com.commerce.huayi.entity.response.CategoryVo;
import com.commerce.huayi.entity.response.GoodsSpuDetailsVo;
import com.commerce.huayi.mapper.*;
import com.commerce.huayi.service.GoodsService;
import com.commerce.huayi.utils.BeanCopyUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Autowired
    private TranslateMapper translateMapper;

    @Autowired
    private JedisTemplate jedisTemplate;

    @Override
    public List<CategoryVo> getCategories(Long parentId) throws BusinessException {
        LOGGER.warn("getGoodsCategory========parentId====" + parentId);
        Example example = new Example(GoodsCategory.class);
        example.createCriteria().andEqualTo("parentId", parentId);
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
        Example goodsCategorySpecExample = new Example(GoodsCategorySpec.class);
        goodsCategorySpecExample.createCriteria().andEqualTo("id", id);
        List<GoodsCategorySpec> goodsCategorySpecs = goodsCategorySpecMapper.selectByExample(goodsCategorySpecExample);
        if(CollectionUtils.isEmpty(goodsCategorySpecs)) {
            return null;
        }
        List<Long> specValueIds = goodsCategorySpecs.stream().map(GoodsCategorySpec::getSpecValueId).collect(Collectors.toList());
        Example goodsSpuSpecExample = new Example(GoodsSpuSpec.class);
        goodsSpuSpecExample.createCriteria().andIn("specValueId", specValueIds);
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
    @Transactional
    public Integer addCategory(CategoryReq categoryReq) {
        List<String> languages = Stream.of(LanguageEnum.values()).map(LanguageEnum::getLanguage).collect(Collectors.toList());
        List<Map<String, String>> list = languages.stream().map(categoryReq::buildSql).collect(Collectors.toList());
        list = list.stream().filter(Objects::nonNull).collect(Collectors.toList());
        list.forEach(map -> translateMapper.insertTranslateDict(map));
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

    @Override
    public byte[] getGoodsImage(String category,String imageKey) {
        if(StringUtils.isBlank(category) || StringUtils.isBlank(imageKey)) {
            return null;
        }
        RedisKey redisKey = new RedisKey(RedisKeysPrefix.IMAGE_KEY, category);
        byte[] bytes = jedisTemplate.hget(redisKey, imageKey);
        return bytes;
    }


}