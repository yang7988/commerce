package com.commerce.huayi.service.impl;

import com.commerce.huayi.annotation.Dictionary;
import com.commerce.huayi.api.ApiResponseEnum;
import com.commerce.huayi.api.BusinessException;
import com.commerce.huayi.cache.JedisTemplate;
import com.commerce.huayi.cache.key.RedisKey;
import com.commerce.huayi.cache.key.RedisKeysPrefix;
import com.commerce.huayi.constant.Constant;
import com.commerce.huayi.constant.LanguageEnum;
import com.commerce.huayi.entity.db.*;
import com.commerce.huayi.entity.request.*;
import com.commerce.huayi.entity.response.CategoryVo;
import com.commerce.huayi.entity.response.GoodsSpecValueVo;
import com.commerce.huayi.entity.response.GoodsSpuDetailsVo;
import com.commerce.huayi.entity.response.GoodsSpuVo;
import com.commerce.huayi.mapper.*;
import com.commerce.huayi.pagination.Condition;
import com.commerce.huayi.pagination.Page;
import com.commerce.huayi.service.GoodsService;
import com.commerce.huayi.utils.BeanCopyUtil;
import com.commerce.huayi.utils.ObjectUtil;
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
    private GoodPopulateMapper goodPopulateMapper;

    @Autowired
    private JedisTemplate jedisTemplate;

    @Autowired
    private TranslateMapper translateMapper;

    @Override
    public Page<CategoryVo> getCategories(Long id,String name, int pageIndex, int pageMaxSize) throws BusinessException {
        Condition condition = Condition.create();
        Map<String, Object> criterion = condition.getCriterion();
        criterion.put("categoryId",id);
        criterion.put("categoryName",name);
        //查询数据库count
        Integer count = goodsCategoryMapper.selectCategoryCount(condition);
        Page<CategoryVo> page = Page.create(pageIndex, pageMaxSize, count);
        if (count <= 0) {
            return page;
        }
        //分页条件
        condition.setOffset(page.getOffset());
        condition.setRowSize(page.getPageMaxSize());
        List<CategoryVo> categoryVos = goodsCategoryMapper.selectCategoryByPage(condition);
        page.setList(categoryVos);
        return page;
    }

    @Override
    public Page<GoodsSpuVo> categoryGoods(Long id, int pageIndex, int pageMaxSize) throws BusinessException {
        Integer count = goodsSpuMapper.getGoodsCountByCategoryId(id);
        Page<GoodsSpuVo> page = Page.create(pageIndex, pageMaxSize, count);
        if(count <= 0) {
            return page;
        }
        List<GoodsSpuVo> list = goodsSpuMapper.getGoodsByCategoryId(id, page.getOffset(), page.getPageMaxSize());
        page.setList(list);
        return page;
    }

    @Override
    @Transactional
    @Dictionary
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
        categoryReq.setDictId(goodsCategory.getId());
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
    public ApiResponseEnum updateCategory(UpdateCategoryReq req,String language) {
        GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(req.getDictId());
        if (goodsCategory == null) {
            return ApiResponseEnum.SUCCESS;
        }
        GoodsCategory updateCategory = new GoodsCategory();
        updateCategory.setId(goodsCategory.getId());
        updateCategory.setUpdateDate(new Date());
        String tableName = "tb_goods_category_".concat(language);
        if (StringUtils.isNotBlank(req.getCategoryName())) {
            this.updateDict(tableName, "category_name_translate",
                    "category_name", req.getCategoryName(), goodsCategory.getCategoryName());
        }
        if (StringUtils.isNotBlank(req.getCategoryDescription())) {
            this.updateDict(tableName, "category_description_translate",
                    "category_description", req.getCategoryDescription(), goodsCategory.getCategoryDescription());
        }
        goodsCategoryMapper.updateByPrimaryKeySelective(updateCategory);
        return ApiResponseEnum.SUCCESS;
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
    @Dictionary
    public GoodsSpuVo addGoods(AddGoodsReq addGoodsReq) {
        if(StringUtils.isBlank(addGoodsReq.getGoodsName())) {
            return null;
        }
        Example spuExample = new Example(GoodsSpu.class);
        spuExample.createCriteria().andEqualTo("goodsName", addGoodsReq.getGoodsName());
        int count = goodsSpuMapper.selectCountByExample(spuExample);
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
        addGoodsReq.setDictId(goodsSpu.getId());
        return BeanCopyUtil.copy(GoodsSpuVo.class, goodsSpu);
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
    @Dictionary
    public AddSpuSpecValueReq addSpecInfo(AddSpuSpecReq addSpuSpecReq) {
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
        addSpuSpecReq.setDictId(goodsSpec.getId());
        Example specValexample = new Example(GoodsSpecValue.class);
        specValexample.createCriteria().andEqualTo("specId", goodsSpec.getId())
                .andEqualTo("specValue",addSpuSpecReq.getSpecValue());
        int countByExample = goodsSpecValueMapper.selectCountByExample(specValexample);
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
        AddSpuSpecValueReq specValueReq = BeanCopyUtil.copy(AddSpuSpecValueReq.class, addSpuSpecReq);
        specValueReq.setDictId(goodsSpecValue.getId());
        return specValueReq;
    }

    @Override
    public Page<GoodsSpecValueVo> getSpecInfoList(PageRequest pageRequest) {
        int count = goodsSpecMapper.getSpecInfoCount();
        Page<GoodsSpecValueVo> page = Page.create(pageRequest.getPageIndex(),pageRequest.getPageMaxSize(),count);
        if(count <= 0) {
            return page;
        }
        List<GoodsSpecValueVo> goodsSpecValueVos = goodsSpecMapper.getSpecInfos(page.getOffset(), page.getPageMaxSize());
        List<String> languages = Stream.of(LanguageEnum.values()).map(LanguageEnum::getLanguage).collect(Collectors.toList());
        goodsSpecValueVos.forEach(vo -> languages.forEach(language -> getSpecTranslate(vo,language)));
        page.setList(goodsSpecValueVos);
        return page;

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

    @Override
    public Page<GoodsSpuDetailsVo> populateGoods(Long id, int pageIndex, int pageMaxSize) {
        int count = goodPopulateMapper.selectPopulateGoodsCount(id);
        Page<GoodsSpuDetailsVo> page = Page.create(pageIndex, pageMaxSize, count);
        if(count <= 0) {
            return page;
        }
        List<GoodsSpuDetailsVo> list = goodPopulateMapper.selectPopulateGoodsByPage(id, page.getOffset(), page.getPageMaxSize());
        page.setList(list);
        return page;

    }

    @Override
    @Transactional
    public ApiResponseEnum addPopulateGoods(AddPopulateGoodsReq req) {
        Example example = new Example(GoodPopulate.class);
        example.createCriteria().andEqualTo("categoryId", req.getCategoryId())
                .andEqualTo("spuId", req.getGoodsId())
                .andEqualTo("specValueId", req.getSpecValueId())
                .andEqualTo("isDelete",Constant.NODELETE);
        int count = goodPopulateMapper.selectCountByExample(example);
        if(count > 0) {
            return ApiResponseEnum.SUCCESS;
        }
        GoodPopulate goodPopulate = new GoodPopulate();
        goodPopulate.setCategoryId(req.getCategoryId());
        goodPopulate.setSpuId(req.getGoodsId());
        goodPopulate.setSpecValueId(req.getSpecValueId());
        goodPopulate.setCreateDate(new Date());
        goodPopulate.setUpdateDate(new Date());
        goodPopulate.setIsDelete(Constant.NODELETE);
        goodPopulateMapper.insertSelective(goodPopulate);
        return ApiResponseEnum.SUCCESS;
    }

    @Override
    @Transactional
    public ApiResponseEnum delPopulateGoods(AddPopulateGoodsReq req) {
        Example example = new Example(GoodPopulate.class);
        example.createCriteria().andEqualTo("categoryId", req.getCategoryId())
                .andEqualTo("spuId", req.getGoodsId())
                .andEqualTo("specValueId", req.getSpecValueId())
                .andEqualTo("isDelete", Constant.DELETED);
        int count = goodPopulateMapper.selectCountByExample(example);
        if (count == 0) {
            return ApiResponseEnum.SUCCESS;
        }
        example.clear();
        example = new Example(GoodPopulate.class);
        example.createCriteria().andEqualTo("categoryId", req.getCategoryId())
                .andEqualTo("spuId", req.getGoodsId())
                .andEqualTo("specValueId", req.getSpecValueId());
        GoodPopulate goodPopulate = new GoodPopulate();
        goodPopulate.setIsDelete(Constant.DELETED);
        goodPopulateMapper.updateByExampleSelective(goodPopulate, example);
        return ApiResponseEnum.SUCCESS;
    }

    @Override
    public List<GoodsSpuVo> search(String keyWord, String language) {
        keyWord = ObjectUtil.processsenseKeyword(keyWord);
        Condition condition = Condition.create();
        Map<String, Object> criterion = condition.getCriterion();
        criterion.put("searchKeyWord",keyWord);
        criterion.put("translate_table_name", "tb_goods_spu_".concat(language));
        return goodsSpuMapper.searchGoodsSpu(condition);
    }

    @Override
    public Page<GoodsSpuDetailsVo> goodsDetails(Long id, int pageIndex, int pageMaxSize) {
        Integer count = goodsSpuMapper.getGoodsCountByBySpuId(id);
        Page<GoodsSpuDetailsVo> page = Page.create(pageIndex, pageMaxSize, count);
        if(count <= 0) {
            return page;
        }
        List<GoodsSpuDetailsVo> list = goodsSpuMapper.getGoodsBySpuId(id, page.getOffset(), page.getPageMaxSize());
        page.setList(list);
        return page;
    }

    @Override
    @Transactional
    public ApiResponseEnum addGoodsSpec(AddGoodsSpecReq req) {
        Example example = new Example(GoodsSpuSpec.class);
        example.createCriteria().andEqualTo("spuId",req.getId())
                .andEqualTo("specValueId",req.getSpecValueId());
        int count = goodsSpuSpecMapper.selectCountByExample(example);
        if(count <= 0) {
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
        return goodsSpuSpecMapper.selectGoodsSpecDetails(id,specValueId);
    }

    @Override
    @Transactional
    public ApiResponseEnum updateGoods(UpdateGoodsReq req, String language) {
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
        String tableName = "tb_goods_spu_".concat(language);
        if (StringUtils.isNotBlank(req.getGoodsName())) {
            this.updateDict(tableName, "goods_name_translate",
                    "goods_name", req.getGoodsName(), goodsSpu.getGoodsName());
        }
        if (StringUtils.isNotBlank(req.getGoodsDescription())) {
            this.updateDict(tableName, "goods_description_translate",
                    "goods_description", req.getGoodsName(), goodsSpu.getGoodsName());
        }
        goodsSpuMapper.updateByPrimaryKeySelective(updateGoods);
        return ApiResponseEnum.SUCCESS;
    }

    private void updateDict(String table, String translateColumn, String whereColumn, String reqVal, String updateVal) {
        TranslateEntityExample example = new TranslateEntityExample(table, translateColumn, whereColumn, updateVal);
        List<TranslateEntity> translateEntities = translateMapper.selectByKey(example);
        if (CollectionUtils.isEmpty(translateEntities)) {
            return;
        }
        TranslateEntity translateEntity = translateEntities.get(0);
        if (reqVal.equalsIgnoreCase(translateEntity.getTranslateResult())) {
            return;
        }
        TranslateEntityExample translateExample = new TranslateEntityExample(table,
                translateColumn, reqVal, whereColumn, updateVal);
        translateMapper.updateByKey(translateExample);
        RedisKey redisKey = new RedisKey(RedisKeysPrefix.I18N_KEY,table);
        jedisTemplate.hset(redisKey, translateColumn.concat(":").concat(updateVal),reqVal);
    }

    @Override
    @Transactional
    public ApiResponseEnum updateSpecInfo(UpdateSpuSpecReq req, String language) {
        GoodsSpec goodsSpec = goodsSpecMapper.selectByPrimaryKey(req.getId());
        if (goodsSpec == null) {
            return ApiResponseEnum.SUCCESS;
        }
        GoodsSpec updateSpec = new GoodsSpec();
        updateSpec.setId(goodsSpec.getId());
        updateSpec.setUpdateDate(new Date());

        String tableName = "tb_goods_spec_".concat(language);
        if (StringUtils.isNotBlank(req.getSpecName())) {
            this.updateDict(tableName, "spec_name_translate",
                    "spec_name", req.getSpecName(), updateSpec.getSpecName());
        }
        if (StringUtils.isNotBlank(req.getSpecDescription())) {
            this.updateDict(tableName, "spec_description_translate",
                    "spec_description", req.getSpecDescription(), updateSpec.getSpecDescription());
        }
        GoodsSpecValue goodsSpecValue = goodsSpecValueMapper.selectByPrimaryKey(req.getSpecValueId());
        goodsSpecMapper.updateByPrimaryKeySelective(updateSpec);
        if(goodsSpecValue == null) {
            return ApiResponseEnum.SUCCESS;
        }
        String table = "tb_goods_spec_value_".concat(language);
        GoodsSpecValue updateSpecValue = new GoodsSpecValue();
        updateSpecValue.setId(goodsSpecValue.getId());
        updateSpecValue.setUpdateDate(new Date());
        if(StringUtils.isNotBlank(req.getSpecValue())) {
            this.updateDict(table, "spec_value_translate",
                    "spec_value", req.getSpecValue(), goodsSpecValue.getSpecValue());
        }
        goodsSpecValueMapper.updateByPrimaryKeySelective(updateSpecValue);
        return ApiResponseEnum.SUCCESS;
    }
}