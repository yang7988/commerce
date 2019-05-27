package com.commerce.huayi.service.impl;

import com.commerce.huayi.api.ApiResponseEnum;
import com.commerce.huayi.api.BusinessException;
import com.commerce.huayi.cache.JedisTemplate;
import com.commerce.huayi.cache.key.RedisKey;
import com.commerce.huayi.cache.key.RedisKeysPrefix;
import com.commerce.huayi.constant.Constant;
import com.commerce.huayi.entity.db.*;
import com.commerce.huayi.entity.request.*;
import com.commerce.huayi.entity.response.CategoryVo;
import com.commerce.huayi.entity.response.GoodsSpecValueVo;
import com.commerce.huayi.entity.response.GoodsSpuDetailsVo;
import com.commerce.huayi.entity.response.GoodsSpuVo;
import com.commerce.huayi.mapper.*;
import com.commerce.huayi.pagination.Page;
import com.commerce.huayi.service.GoodsService;
import com.commerce.huayi.utils.BeanCopyUtil;
import com.commerce.huayi.utils.ObjectUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class GoodsServiceImpl implements GoodsService {

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
    private GoodPopulateMapper goodPopulateMapper;

    @Autowired
    private JedisTemplate jedisTemplate;

    @Override
    public List<CategoryVo> getCategories(Long id, String name) throws BusinessException {

        return goodsCategoryMapper.selectCategory(id,name);

    }

    @Override
    public Page<GoodsSpuVo> categoryGoods(Long id, int pageIndex, int pageMaxSize) throws BusinessException {
        Integer count = goodsSpuMapper.getGoodsCountByCategoryId(id);
        Page<GoodsSpuVo> page = Page.create(pageIndex, pageMaxSize, count);
        if (count <= 0) {
            return page;
        }
        List<GoodsSpuVo> list = goodsSpuMapper.getGoodsByCategoryId(id, page.getOffset(), page.getPageMaxSize());
        page.setList(list);
        return page;
    }

    @Override
    @Transactional
    public ApiResponseEnum addCategory(CategoryReq categoryReq) {
        int count = goodsCategoryMapper.selectCountByName(categoryReq.getCategoryName());
        if (count > 0) {
            return ApiResponseEnum.GOODS_CATEGORY_EXISTS;
        }
        GoodsCategory goodsCategory = BeanCopyUtil.copy(GoodsCategory.class, categoryReq);
        goodsCategory.setIsDelete(Constant.NODELETE);
        goodsCategory.setCreateDate(new Date());
        goodsCategory.setUpdateDate(new Date());
        goodsCategoryMapper.insertSelective(goodsCategory);
        //增加字典翻译
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
    @Transactional
    public ApiResponseEnum updateCategory(UpdateCategoryReq req) {
        GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(req.getId());
        if (goodsCategory == null) {
            return ApiResponseEnum.SUCCESS;
        }
        GoodsCategory updateCategory = new GoodsCategory();
        updateCategory.setId(goodsCategory.getId());
        updateCategory.setUpdateDate(new Date());
        updateCategory.setCategoryName(req.getCategoryName());
        updateCategory.setCategoryDescription(req.getCategoryDescription());
        goodsCategoryMapper.updateByPrimaryKeySelective(updateCategory);
        return ApiResponseEnum.SUCCESS;
    }

    @Override
    public byte[] getGoodsImage(Long goodsId) {
        if (goodsId == null) {
            return null;
        }
        GoodsSpu goodsSpu = goodsSpuMapper.selectByPrimaryKey(goodsId);
        GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(goodsSpu.getCategoryId());
        RedisKey redisKey = new RedisKey(RedisKeysPrefix.IMAGE_KEY, goodsCategory.getCategoryName());
        return jedisTemplate.hget(redisKey, goodsSpu.getGoodsImageKey());
    }

    @Override
    @Transactional
    public GoodsSpuVo addGoods(AddGoodsReq addGoodsReq) {
        if (StringUtils.isBlank(addGoodsReq.getGoodsName())) {
            return null;
        }
        int count = goodsSpuMapper.selectCountByName(addGoodsReq.getGoodsName());
        if (count > 0) {
            return null;
        }
        //添加产品单元
        GoodsSpu goodsSpu = new GoodsSpu();
        BeanCopyUtil.copy(goodsSpu, addGoodsReq);
        goodsSpu.setSpuNo(DigestUtils.md5Hex(addGoodsReq.getGoodsName().concat(UUID.randomUUID().toString())));
        goodsSpu.setGoodsImageKey(addGoodsReq.getGoodsImageKey());
        goodsSpu.setCreateDate(new Date());
        goodsSpu.setUpdateDate(new Date());
        goodsSpu.setIsDelete(Constant.NODELETE);
        goodsSpuMapper.insertSelective(goodsSpu);
        GoodsSpuVo goodsSpuVo = BeanCopyUtil.copy(GoodsSpuVo.class, goodsSpu);
        if (CollectionUtils.isEmpty(addGoodsReq.getSpecRequest())) {
            return goodsSpuVo;
        }
        Long goodsSpuId = goodsSpu.getId();
        for (AddGoodsSpecReq addGoodsSpecReq : addGoodsReq.getSpecRequest()) {
            int specCount = goodsSpuSpecMapper.selectCountBySpuIdAndSpecValueId(goodsSpuId, addGoodsSpecReq.getSpecValueId());
            if (specCount > 0) {
                continue;
            }
            GoodsSpuSpec goodsSpuSpec = new GoodsSpuSpec();
            goodsSpuSpec.setSpuId(goodsSpuId);
            goodsSpuSpec.setSpecValueId(addGoodsSpecReq.getSpecValueId());
            goodsSpuSpec.setSpecImageKey(addGoodsSpecReq.getGoodsSpecImageKey());
            goodsSpuSpec.setCreateDate(new Date());
            goodsSpuSpec.setUpdateDate(new Date());
            goodsSpuSpec.setIsDelete(Constant.NODELETE);
            goodsSpuSpecMapper.insertSelective(goodsSpuSpec);
        }
        return goodsSpuVo;
    }

    @Override
    @Transactional
    public ApiResponseEnum deleteGoods(Long goodsId) {
        GoodsSpu goodsSpu = new GoodsSpu();
        goodsSpu.setId(goodsId);
        goodsSpu.setUpdateDate(new Date());
        goodsSpu.setIsDelete(Constant.DELETED);
        goodsSpuMapper.updateByPrimaryKeySelective(goodsSpu);
        return ApiResponseEnum.SUCCESS;
    }


    @Override
    @Transactional
    public AddSpuSpecValueReq addSpecInfo(AddSpuSpecReq addSpuSpecReq) {
        int count = goodsSpecMapper.selectCountByName(addSpuSpecReq.getSpecName());
        GoodsSpec goodsSpec;
        if (count > 0) {
            goodsSpec = goodsSpecMapper.selectByName(addSpuSpecReq.getSpecName()).get(0);
        } else {
            goodsSpec = BeanCopyUtil.copy(GoodsSpec.class, addSpuSpecReq);
            goodsSpec.setSpecNo(DigestUtils.md5Hex(addSpuSpecReq.getSpecName().concat(UUID.randomUUID().toString())));
            goodsSpec.setCreateDate(new Date());
            goodsSpec.setUpdateDate(new Date());
            goodsSpec.setIsDelete(Constant.NODELETE);
            goodsSpecMapper.insertSelective(goodsSpec);
        }
        int countByExample = goodsSpecValueMapper.selectCountBySpecIdAndValue(goodsSpec.getId(), addSpuSpecReq.getSpecValue());
        if (countByExample > 0) {
            return null;
        }
        GoodsSpecValue goodsSpecValue = new GoodsSpecValue();
        goodsSpecValue.setSpecId(goodsSpec.getId());
        goodsSpecValue.setSpecValue(addSpuSpecReq.getSpecValue());
        goodsSpecValue.setCreateDate(new Date());
        goodsSpecValue.setUpdateDate(new Date());
        goodsSpecValue.setIsDelete(Constant.NODELETE);
        goodsSpecValueMapper.insertSelective(goodsSpecValue);
        return BeanCopyUtil.copy(AddSpuSpecValueReq.class, addSpuSpecReq);
    }

    @Override
    public Page<GoodsSpecValueVo> getSpecInfoList(PageRequest pageRequest) {
        int count = goodsSpecMapper.getSpecInfoCount();
        Page<GoodsSpecValueVo> page = Page.create(pageRequest.getPageIndex(), pageRequest.getPageMaxSize(), count);
        if (count <= 0) {
            return page;
        }
        List<GoodsSpecValueVo> goodsSpecValueVos = goodsSpecMapper.getSpecInfos(page.getOffset(), page.getPageMaxSize());
        page.setList(goodsSpecValueVos);
        return page;

    }


    @Override
    public Page<GoodsSpuVo> search(String keyWord,int pageIndex, int pageMaxSize) {
        keyWord = ObjectUtil.processsenseKeyword(keyWord);

        int count = goodsSpuMapper.searchCountGoodsSpu(keyWord);
        Page<GoodsSpuVo> page = Page.create(pageIndex, pageMaxSize, count);
        if (count <= 0) {
            return page;
        }
        List<GoodsSpuVo> goodsSpuVos = goodsSpuMapper.searchGoodsSpu(keyWord,page.getOffset(),page.getPageMaxSize());
        page.setList(goodsSpuVos);
        return page;
    }

    @Override
    public Page<GoodsSpuDetailsVo> goodsDetails(Long id, int pageIndex, int pageMaxSize) {
        Integer count = goodsSpuMapper.getGoodsCountByBySpuId(id);
        Page<GoodsSpuDetailsVo> page = Page.create(pageIndex, pageMaxSize, count);
        if (count <= 0) {
            return page;
        }
        List<GoodsSpuDetailsVo> list = goodsSpuMapper.getGoodsBySpuId(id, page.getOffset(), page.getPageMaxSize());
        page.setList(list);
        return page;
    }

    @Override
    @Transactional
    public ApiResponseEnum addGoodsSpec(AddGoodsSpecReq req) {
        int count = goodsSpuSpecMapper.selectCountBySpuIdAndSpecValueId(req.getId(), req.getSpecValueId());
        if (count <= 0) {
            return ApiResponseEnum.SUCCESS;
        }
        GoodsSpuSpec goodsSpuSpec = new GoodsSpuSpec();
        goodsSpuSpec.setSpuId(req.getId());
        goodsSpuSpec.setSpecValueId(req.getSpecValueId());
        goodsSpuSpec.setSpecImageKey(req.getGoodsSpecImageKey());
        goodsSpuSpec.setCreateDate(new Date());
        goodsSpuSpec.setUpdateDate(new Date());
        goodsSpuSpec.setIsDelete(Constant.NODELETE);
        goodsSpuSpecMapper.insertSelective(goodsSpuSpec);
        return ApiResponseEnum.SUCCESS;
    }

    @Override
    public GoodsSpuDetailsVo goodsSpecDetails(Long id, Long specValueId) {
        List<GoodsSpuDetailsVo> spuDetailsVos = goodsSpuSpecMapper.selectGoodsSpecDetails(id, specValueId);
        return CollectionUtils.isEmpty(spuDetailsVos) ? null : spuDetailsVos.get(0);
    }

    @Override
    @Transactional
    public ApiResponseEnum updateGoods(UpdateGoodsReq req) {
        GoodsSpu goodsSpu = goodsSpuMapper.selectByPrimaryKey(req.getId());
        if (goodsSpu == null) {
            return ApiResponseEnum.SUCCESS;
        }
        GoodsSpu updateGoods = new GoodsSpu();
        updateGoods.setId(goodsSpu.getId());
        updateGoods.setUpdateDate(new Date());
        if (StringUtils.isNotBlank(req.getGoodsImageKey())) {
            updateGoods.setGoodsImageKey(req.getGoodsImageKey());
        }
        if (req.getPrice() != null) {
            updateGoods.setLowPrice(req.getPrice());
        }
        if (req.getCategoryId() != null) {
            updateGoods.setCategoryId(req.getCategoryId());
        }
        if (StringUtils.isNotBlank(req.getGoodsName())) {
            updateGoods.setGoodsName(req.getGoodsName());
        }
        if (StringUtils.isNotBlank(req.getGoodsDescription())) {
            updateGoods.setGoodsDescription(req.getGoodsDescription());
        }
        goodsSpuMapper.updateByPrimaryKeySelective(updateGoods);
        return ApiResponseEnum.SUCCESS;
    }

    @Override
    @Transactional
    public ApiResponseEnum updateSpecInfo(UpdateSpuSpecReq req) {
        GoodsSpec goodsSpec = goodsSpecMapper.selectByPrimaryKey(req.getSpecId());
        if (goodsSpec == null) {
            return ApiResponseEnum.SUCCESS;
        }
        GoodsSpec updateSpec = new GoodsSpec();
        updateSpec.setId(goodsSpec.getId());
        updateSpec.setUpdateDate(new Date());
        updateSpec.setSpecName(req.getSpecName());
        updateSpec.setSpecDescription(req.getSpecDescription());
        goodsSpecMapper.updateByPrimaryKeySelective(updateSpec);

        GoodsSpecValue goodsSpecValue = goodsSpecValueMapper.selectByPrimaryKey(req.getSpecValueId());

        if (goodsSpecValue == null) {
            return ApiResponseEnum.SUCCESS;
        }
        GoodsSpecValue updateSpecValue = new GoodsSpecValue();
        updateSpecValue.setId(goodsSpecValue.getId());
        updateSpecValue.setUpdateDate(new Date());
        updateSpecValue.setSpecValue(req.getSpecValue());
        goodsSpecValueMapper.updateByPrimaryKeySelective(updateSpecValue);
        return ApiResponseEnum.SUCCESS;
    }
}