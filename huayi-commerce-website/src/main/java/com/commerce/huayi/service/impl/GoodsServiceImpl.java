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
import com.commerce.huayi.entity.response.GoodsSpecValueVo;
import com.commerce.huayi.entity.response.GoodsSpuDetailsVo;
import com.commerce.huayi.mapper.*;
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
    private TranslateMapper translateMapper;

    @Autowired
    private JedisTemplate jedisTemplate;

    @Override
    public Page<CategoryVo> getCategories(Long id,String name, int pageIndex, int pageMaxSize) throws BusinessException {
        Integer count = goodsCategoryMapper.selectCategoryCountByPage(id, name);
        Page<CategoryVo> page = Page.create(pageIndex, pageMaxSize, count);
        if (count <= 0) {
            return page;
        }
        List<CategoryVo> categoryVos = goodsCategoryMapper.selectCategoryByPage(id, name, page.getOffset(), page.getPageMaxSize());
        page.setList(categoryVos);
        return page;
    }

    @Override
    public Page<GoodsSpuDetailsVo> categoryGoods(Long id, int pageIndex, int pageMaxSize) throws BusinessException {
        Integer count = goodsSpuMapper.getGoodsCountByCategoryId(id);
        Page<GoodsSpuDetailsVo> page = Page.create(pageIndex, pageMaxSize, count);
        if(count <= 0) {
            return page;
        }
        List<GoodsSpuDetailsVo> list = goodsSpuMapper.getGoodsByCategoryId(id, page.getOffset(), page.getPageMaxSize());
        page.setList(list);
        return page;
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
        goodsSpu.setGoodsImageKey(addGoodsReq.getGoodsImageKey());
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
    public List<GoodsSpuDetailsVo> populateGoods(Long id) {
        Example example = new Example(GoodPopulate.class);
        example.createCriteria().andEqualTo("categoryId", id);
        List<GoodPopulate> goodPopulates = goodPopulateMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(goodPopulates)) {
            return null;
        }
        List<GoodsSpuDetailsVo> goodsSpuDetailsVos = new ArrayList<>();
        for (GoodPopulate goodPopulate : goodPopulates) {
            GoodsSpu goodsSpu = goodsSpuMapper.selectByPrimaryKey(goodPopulate.getSpuId());
            GoodsSpuDetailsVo detailsVo = BeanCopyUtil.copy(GoodsSpuDetailsVo.class, goodsSpu);
            GoodsSpecValue goodsSpecValue = goodsSpecValueMapper.selectByPrimaryKey(goodPopulate.getSpecValueId());
            BeanCopyUtil.copy(detailsVo, goodsSpecValue);
            GoodsSpec goodsSpec = goodsSpecMapper.selectByPrimaryKey(goodsSpecValue.getSpecId());
            BeanCopyUtil.copy(detailsVo, goodsSpec);
            goodsSpuDetailsVos.add(detailsVo);
        }
        return goodsSpuDetailsVos;
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
    public List<GoodsSpuDetailsVo> search(String keyWord, String language) {
        keyWord = ObjectUtil.processsenseKeyword(keyWord);
        List<GoodsSpuDetailsVo> goodsSpuDetailsVos = new ArrayList<>();
        List<GoodsSpu> goodsSpus = goodsSpuMapper.search(keyWord);
        if (CollectionUtils.isNotEmpty(goodsSpus)) {
            packageGoodsSpu(goodsSpuDetailsVos, goodsSpus);
            return goodsSpuDetailsVos;
        }
        StringBuilder stringBuilder = new StringBuilder("select goods_name,goods_name_translate,goods_description," +
                "goods_description_translate from tb_goods_spu_");
        stringBuilder.append(language).append(" where goods_name like '%").append(keyWord).append("%'");
        stringBuilder.append(" or ").append(" goods_name_translate like '%").append(keyWord).append("%'");
        stringBuilder.append(" or ").append(" goods_description like '%").append(keyWord).append("%'");
        stringBuilder.append(" or ").append(" goods_description_translate like '%").append(keyWord).append("%'");
        String sql = stringBuilder.toString();

        Map<String, String> sqlMap = new HashMap<>(1);
        sqlMap.put("sqlStatement", sql);
        List<Map<String, String>> resultMap = goodsSpuMapper.searchBySql(sqlMap);
        if(CollectionUtils.isEmpty(resultMap)) {
            return goodsSpuDetailsVos;
        }
        List<GoodsSpu> goodsSpusList = new ArrayList<>();
        for (Map<String, String> map : resultMap) {
            String goodsName = map.get("goods_name");
            String goodsDescription = map.get("goods_description");
            Example example = new Example(GoodsSpu.class);
            example.createCriteria().andEqualTo("goodsName", goodsName)
                    .orEqualTo("goodsDescription", goodsDescription);
            List<GoodsSpu> spuList = goodsSpuMapper.selectByExample(example);
            goodsSpusList.addAll(spuList);
        }
        packageGoodsSpu(goodsSpuDetailsVos, goodsSpusList);
        return goodsSpuDetailsVos;
    }

    private void packageGoodsSpu(List<GoodsSpuDetailsVo> goodsSpuDetailsVos, List<GoodsSpu> goodsSpus) {
        for (GoodsSpu spu : goodsSpus) {
            Example spuSpecexample = new Example(GoodsSpuSpec.class);
            spuSpecexample.createCriteria().andEqualTo("spuId", spu.getId());
            List<GoodsSpuSpec> goodsSpuSpecs = goodsSpuSpecMapper.selectByExample(spuSpecexample);
            if (CollectionUtils.isNotEmpty(goodsSpuSpecs)) {
                goodsSpuSpecs.forEach(goodsSpuSpec -> goodsSpuDetailsVos.add(getSpuDeatis(spu, goodsSpuSpec)));
            }
        }
    }

}