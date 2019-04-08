package com.commerce.huayi.service.impl;

import com.commerce.huayi.api.ApiResponseEnum;
import com.commerce.huayi.api.BusinessException;
import com.commerce.huayi.cache.JedisTemplate;
import com.commerce.huayi.cache.key.RedisKey;
import com.commerce.huayi.cache.key.RedisKeysPrefix;
import com.commerce.huayi.constant.Constant;
import com.commerce.huayi.constant.LanguageEnum;
import com.commerce.huayi.entity.db.*;
import com.commerce.huayi.entity.request.AddGoodsReq;
import com.commerce.huayi.entity.request.CategoryReq;
import com.commerce.huayi.entity.response.CategoryVo;
import com.commerce.huayi.entity.response.GoodsSpuDetailsVo;
import com.commerce.huayi.mapper.*;
import com.commerce.huayi.service.GoodsService;
import com.commerce.huayi.utils.BeanCopyUtil;
import org.apache.commons.codec.digest.DigestUtils;
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
        Example example = new Example(GoodsSpu.class);
        example.createCriteria().andEqualTo("categoryId", id);
        List<GoodsSpu> goodsSpus = goodsSpuMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(goodsSpus)) {
            return null;
        }
        List<GoodsSpuDetailsVo> goodsSpuDetailsVos = new ArrayList<>();
        for (GoodsSpu spu : goodsSpus) {
            Example spuSpecexample = new Example(GoodsSpuSpec.class);
            spuSpecexample.createCriteria().andEqualTo("spuId", spu.getId());
            List<GoodsSpuSpec> goodsSpuSpecs = goodsSpuSpecMapper.selectByExample(spuSpecexample);
            if (CollectionUtils.isNotEmpty(goodsSpuSpecs)) {
                goodsSpuSpecs.forEach(goodsSpuSpec -> goodsSpuDetailsVos.add(getSpuDeatis(spu, goodsSpuSpec)));
            }
        }
        return goodsSpuDetailsVos;
    }

    private GoodsSpuDetailsVo getSpuDeatis(GoodsSpu goodsSpu, GoodsSpuSpec goodsSpuSpec) {
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
        return jedisTemplate.hget(redisKey, imageKey);
    }

    @Override
    @Transactional
    public ApiResponseEnum addGoods(AddGoodsReq addGoodsReq) {
        Example spuExample = new Example(GoodsSpu.class);
        spuExample.createCriteria().andEqualTo("goodsName", addGoodsReq.getGoodsName());
        int count = goodsSpuMapper.selectCountByExample(spuExample);
        if (count > 0) {
            return ApiResponseEnum.GOODS_NAME_EXISTS;
        }
        //添加产品单元
        GoodsSpu goodsSpu = new GoodsSpu();
        BeanCopyUtil.copy(goodsSpu, addGoodsReq);
        goodsSpu.setSpuNo(DigestUtils.md5Hex(addGoodsReq.getGoodsName().concat(UUID.randomUUID().toString())));
        goodsSpu.setCreateDate(new Date());
        goodsSpu.setUpdateDate(new Date());
        goodsSpu.setIsDelete(Constant.NODELETE);
        goodsSpuMapper.insertSelective(goodsSpu);
        //添加产品规格
        Example specExample = new Example(GoodsSpec.class);
        specExample.createCriteria().andEqualTo("specName", addGoodsReq.getSpecName());

        int specCount = goodsSpecMapper.selectCountByExample(spuExample);
        GoodsSpec goodsSpec;
        if (specCount > 0) {
            goodsSpec = goodsSpecMapper.selectByExample(spuExample).get(0);
        } else {
            goodsSpec = new GoodsSpec();
            BeanCopyUtil.copy(goodsSpec, addGoodsReq);
            goodsSpec.setSpecNo(DigestUtils.md5Hex(addGoodsReq.getSpecName().concat(UUID.randomUUID().toString())));
            goodsSpec.setCreateDate(new Date());
            goodsSpec.setUpdateDate(new Date());
            goodsSpec.setIsDelete(Constant.NODELETE);
            goodsSpecMapper.insertSelective(goodsSpec);
        }

        Example specValueExample = new Example(GoodsSpecValue.class);
        specExample.createCriteria().andEqualTo("specValue", addGoodsReq.getSpecValue())
                .andEqualTo("specId", goodsSpec.getId());
        int specValueCount = goodsSpecValueMapper.selectCountByExample(specValueExample);
        //添加产品规格值
        GoodsSpecValue goodsSpecValue;
        if (specValueCount > 0) {
            goodsSpecValue = goodsSpecValueMapper.selectByExample(specValueExample).get(0);
        } else {
            goodsSpecValue = new GoodsSpecValue();
            goodsSpecValue.setSpecId(goodsSpec.getId());
            goodsSpecValue.setSpecValue(addGoodsReq.getSpecValue());
            goodsSpecValue.setCreateDate(new Date());
            goodsSpecValue.setUpdateDate(new Date());
            goodsSpecValue.setIsDelete(Constant.NODELETE);
            goodsSpecValueMapper.insertSelective(goodsSpecValue);
        }
        Example spuSpecValueExample = new Example(GoodsSpuSpec.class);
        specExample.createCriteria().andEqualTo("spuId", goodsSpu.getId())
                .andEqualTo("specValueId", goodsSpecValue.getId());
        int spuSpecCount = goodsSpuSpecMapper.selectCountByExample(spuSpecValueExample);
        if (spuSpecCount == 0) {
            //添加产品单元与产品规格关联关系
            GoodsSpuSpec goodsSpuSpec = new GoodsSpuSpec();
            goodsSpuSpec.setSpecValueId(goodsSpecValue.getId());
            goodsSpuSpec.setSpuId(goodsSpu.getId());
            goodsSpuSpec.setCreateDate(new Date());
            goodsSpuSpec.setUpdateDate(new Date());
            goodsSpuSpec.setIsDelete(Constant.NODELETE);
            goodsSpuSpecMapper.insertSelective(goodsSpuSpec);
        }
        List<String> languages = Stream.of(LanguageEnum.values()).map(LanguageEnum::getLanguage).collect(Collectors.toList());
        List<Map<String, String>> list = languages.stream().map(addGoodsReq::buildSql).collect(Collectors.toList());
        list = list.stream().filter(Objects::nonNull).collect(Collectors.toList());
        list.forEach(map -> map.forEach((key, value) -> {
            Map<String, String> sqlMap = new HashMap<>(1);
            sqlMap.put("sqlStatement", value);
            translateMapper.insertTranslateDict(sqlMap);
        }));
        return ApiResponseEnum.SUCCESS;
    }

    @Override
    public ApiResponseEnum deleteGoods(AddGoodsReq addGoodsReq) {
        GoodsSpu goodsSpu = new GoodsSpu();
        goodsSpu.setId(addGoodsReq.getGoodsId());
        goodsSpu.setUpdateDate(new Date());
        goodsSpu.setIsDelete(Constant.DELETED);
        return ApiResponseEnum.SUCCESS;
    }
}