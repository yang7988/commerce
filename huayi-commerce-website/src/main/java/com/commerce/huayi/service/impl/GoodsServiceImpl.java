package com.commerce.huayi.service.impl;

import com.commerce.huayi.api.ApiResponseEnum;
import com.commerce.huayi.api.BusinessException;
import com.commerce.huayi.cache.JedisTemplate;
import com.commerce.huayi.cache.enums.JedisStatus;
import com.commerce.huayi.cache.key.RedisKey;
import com.commerce.huayi.cache.key.RedisKeysPrefix;
import com.commerce.huayi.constant.Constant;
import com.commerce.huayi.constant.LanguageEnum;
import com.commerce.huayi.entity.db.*;
import com.commerce.huayi.entity.request.*;
import com.commerce.huayi.entity.response.CategoryVo;
import com.commerce.huayi.entity.response.GoodsSpecValuePageVo;
import com.commerce.huayi.entity.response.GoodsSpecValueVo;
import com.commerce.huayi.entity.response.GoodsSpuDetailsVo;
import com.commerce.huayi.mapper.*;
import com.commerce.huayi.service.GoodsService;
import com.commerce.huayi.utils.BeanCopyUtil;
import com.commerce.huayi.utils.PageUtils;
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
        goodsSpuDetailsVo.setSpecId(goodsSpec.getId());
        goodsSpuDetailsVo.setSpecValueId(goodsSpecValue.getId());
        return goodsSpuDetailsVo;
    }

    @Override
    @Transactional
    public ApiResponseEnum addCategory(CategoryReq categoryReq) {
        Example example = new Example(GoodsCategory.class);
        example.createCriteria().andEqualTo("categoryName", categoryReq.getCategoryName());
        int count = goodsCategoryMapper.selectCountByExample(example);
        if (count > 0) {
            return ApiResponseEnum.GOODS_CATEGORY_EXISTS;
        }
        GoodsCategory goodsCategory = BeanCopyUtil.copy(GoodsCategory.class, categoryReq);
        goodsCategory.setIsDelete(Constant.NODELETE);
        goodsCategory.setCreateDate(new Date());
        goodsCategory.setUpdateDate(new Date());
        goodsCategoryMapper.insertSelective(goodsCategory);
        List<String> languages = Stream.of(LanguageEnum.values()).map(LanguageEnum::getLanguage).collect(Collectors.toList());
        List<Map<String, String>> list = languages.stream().map(categoryReq::buildSql).collect(Collectors.toList());
        list = list.stream().filter(Objects::nonNull).collect(Collectors.toList());
        list.forEach(map -> translateMapper.insertTranslateDict(map));
        return ApiResponseEnum.SUCCESS;
    }

    @Override
    public Integer deleteCategory(Long id) {
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setIsDelete(Constant.DELETED);
        goodsCategory.setId(id);
        return goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory);
    }

    @Override
    public Integer updateCategory(UpdateCategoryReq req) {
        GoodsCategory goodsCategory = BeanCopyUtil.copy(GoodsCategory.class, req);
        goodsCategory.setUpdateDate(new Date());
        return goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory);
    }

    @Override
    public byte[] getGoodsImage(Long goodsId) {
        if(goodsId == null) {
            return null;
        }
        GoodsSpu goodsSpu = goodsSpuMapper.selectByPrimaryKey(goodsId);
        GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(goodsSpu.getCategoryId());
        RedisKey redisKey = new RedisKey(RedisKeysPrefix.IMAGE_KEY, goodsCategory.getCategoryName());
        return jedisTemplate.hget(redisKey, goodsSpu.getGoodsImageKey());
    }

    @Override
    @Transactional
    public ApiResponseEnum addGoods(AddGoodsReq addGoodsReq) {
        if(StringUtils.isBlank(addGoodsReq.getGoodsName())) {
            return ApiResponseEnum.PARAMETER_CANT_BE_EMPTY;
        }
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

        GoodsSpuSpec goodsSpuSpec = new GoodsSpuSpec();
        goodsSpuSpec.setSpuId(goodsSpu.getId());
        goodsSpuSpec.setSpecValueId(addGoodsReq.getSpecValueId());
        goodsSpuSpec.setCreateDate(new Date());
        goodsSpuSpec.setUpdateDate(new Date());
        goodsSpuSpec.setIsDelete(Constant.NODELETE);
        goodsSpuSpecMapper.insertSelective(goodsSpuSpec);
        List<String> languages = Stream.of(LanguageEnum.values()).map(LanguageEnum::getLanguage).collect(Collectors.toList());
        List<Map<String, String>> list = languages.stream().map(addGoodsReq::buildSql).collect(Collectors.toList());
        list = list.stream().filter(Objects::nonNull).collect(Collectors.toList());
        list.forEach(map -> translateMapper.insertTranslateDict(map));
        return ApiResponseEnum.SUCCESS;
    }

    @Override
    public ApiResponseEnum deleteGoods(Long goodsId) {
        GoodsSpu goodsSpu = new GoodsSpu();
        goodsSpu.setId(goodsId);
        goodsSpu.setUpdateDate(new Date());
        goodsSpu.setIsDelete(Constant.DELETED);
        return ApiResponseEnum.SUCCESS;
    }

    @Override
    @Transactional
    public ApiResponseEnum addGoodsImage(Long goodsId,byte[] bytes) {
        if(bytes == null) {
            return ApiResponseEnum.GOODS_IMAGE_ABSENCE;
        }
        GoodsSpu goodsSpu = goodsSpuMapper.selectByPrimaryKey(goodsId);
        if(goodsSpu == null) {
           return ApiResponseEnum.GOODS_NOT_EXISTS;
        }
        String imageKey = goodsSpu.getSpuNo().concat(":").concat(goodsSpu.getGoodsName());
        goodsSpu.setGoodsImageKey(imageKey);
        //jedis保存产品单元图片
        GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(goodsSpu.getCategoryId());
        RedisKey redisKey = new RedisKey(RedisKeysPrefix.IMAGE_KEY, goodsCategory.getCategoryName());
        JedisStatus jedisStatus = jedisTemplate.hset(redisKey, imageKey, bytes);
        if(JedisStatus.OK == jedisStatus) {
            goodsSpuMapper.updateByPrimaryKeySelective(goodsSpu);
        }
        return ApiResponseEnum.SUCCESS;
    }


    @Override
    @Transactional
    public ApiResponseEnum addSpecInfo(AddSpuSpecReq addSpuSpecReq) {
        List<String> languages = Stream.of(LanguageEnum.values()).map(LanguageEnum::getLanguage).collect(Collectors.toList());
        List<Map<String, String>> list = languages.stream().map(addSpuSpecReq::buildSql).collect(Collectors.toList());
        list = list.stream().filter(Objects::nonNull).collect(Collectors.toList());
        list.forEach(map -> map.forEach((key, value) -> {
            Map<String, String> sqlMap = new HashMap<>(1);
            sqlMap.put("sqlStatement", value);
            translateMapper.insertTranslateDict(sqlMap);
        }));
        Example example = new Example(GoodsSpec.class);
        example.createCriteria().andEqualTo("specName", addSpuSpecReq.getSpecName());
        int count = goodsSpecMapper.selectCountByExample(example);
        GoodsSpec goodsSpec;
        if (count > 0) {
            goodsSpec = goodsSpecMapper.selectByExample(example).get(0);
        } else {
            goodsSpec = BeanCopyUtil.copy(GoodsSpec.class, addSpuSpecReq);
            goodsSpec.setSpecNo(DigestUtils.md5Hex(addSpuSpecReq.getSpecName().concat(UUID.randomUUID().toString())));
            goodsSpec.setCreateDate(new Date());
            goodsSpec.setUpdateDate(new Date());
            goodsSpec.setIsDelete(Constant.NODELETE);
            goodsSpecMapper.insertSelective(goodsSpec);
        }
        Example specValexample = new Example(GoodsSpecValue.class);
        specValexample.createCriteria().andEqualTo("specId", goodsSpec.getId());
        int countByExample = goodsSpecValueMapper.selectCountByExample(specValexample);
        if (countByExample > 0) {
            return ApiResponseEnum.SUCCESS;
        }
        GoodsSpecValue goodsSpecValue = new GoodsSpecValue();
        goodsSpecValue.setSpecId(goodsSpec.getId());
        goodsSpecValue.setSpecValue(addSpuSpecReq.getSpecValue());
        goodsSpecValue.setCreateDate(new Date());
        goodsSpecValue.setUpdateDate(new Date());
        goodsSpecValue.setIsDelete(Constant.NODELETE);
        goodsSpecValueMapper.insertSelective(goodsSpecValue);
        return ApiResponseEnum.SUCCESS;
    }

    @Override
    public GoodsSpecValuePageVo getSpecInfoList(PageReq pageReq) {
        GoodsSpecValuePageVo pageVo = new GoodsSpecValuePageVo();
        int pageMaxSzie = pageReq.getPageMaxSize();
        if (pageMaxSzie <= 0) {
            pageMaxSzie = 10;
        }
        int pageIndex = pageReq.getPageIndex();
        int startLine = PageUtils.pageNumCastToRowNum(pageIndex, pageMaxSzie);

        List<GoodsSpecValueVo> goodsSpecValueVos = goodsSpecMapper.getSpecInfos(startLine, pageMaxSzie);
        if (CollectionUtils.isEmpty(goodsSpecValueVos)) {
            return null;
        }

        List<String> languages = Stream.of(LanguageEnum.values()).map(LanguageEnum::getLanguage).collect(Collectors.toList());
        goodsSpecValueVos.forEach(vo -> languages.forEach(language -> getSpecTranslate(vo,language)));
        pageVo.setCount(goodsSpecMapper.getSpecInfoCount());
        pageVo.setList(goodsSpecValueVos);
        return pageVo;
    }

    private void getSpecTranslate(GoodsSpecValueVo goodsSpecValueVo, String language) {
        String specTranslateTable = "tb_goods_spec_";
        String specValTranslateTable = "tb_goods_spec_value_";
        String specName = "spec_name";
        String specDescription = "spec_description";
        String specVal = "spec_value";
        RedisKey specNameKey = new RedisKey(RedisKeysPrefix.I18N_KEY, specTranslateTable.concat(language));
        String specNameTranslate = jedisTemplate.hget(specNameKey, specName.concat(Constant.TRANSLATE_FIELD_SUFFIX).concat(":")
                .concat(goodsSpecValueVo.getSpecName()), String.class);
        if(StringUtils.isNotBlank(specNameTranslate)) {
            goodsSpecValueVo.getTranslation().put(specName.concat("_").concat(language), specNameTranslate);
        }
        RedisKey specDescriptionKey = new RedisKey(RedisKeysPrefix.I18N_KEY, specTranslateTable.concat(language));
        String specDescriptionTranslate = jedisTemplate.hget(specDescriptionKey, specDescription.concat(Constant.TRANSLATE_FIELD_SUFFIX).concat(":")
                .concat(goodsSpecValueVo.getSpecDescription()), String.class);
        if(StringUtils.isNotBlank(specDescriptionTranslate)) {
            goodsSpecValueVo.getTranslation().put(specDescription.concat("_").concat(language), specDescriptionTranslate);
        }
        RedisKey specValKey = new RedisKey(RedisKeysPrefix.I18N_KEY, specValTranslateTable.concat(language));
        String specValTranslate = jedisTemplate.hget(specValKey, specVal.concat(Constant.TRANSLATE_FIELD_SUFFIX).concat(":")
                .concat(goodsSpecValueVo.getSpecValue()), String.class);
        if(StringUtils.isNotBlank(specValTranslate)) {
            goodsSpecValueVo.getTranslation().put(specVal.concat("_").concat(language), specValTranslate);
        }
    }
}