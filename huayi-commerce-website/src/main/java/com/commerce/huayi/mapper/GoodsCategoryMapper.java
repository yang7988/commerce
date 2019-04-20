package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.GoodsCategory;
import com.commerce.huayi.entity.response.CategoryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(GoodsCategory record);

    GoodsCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsCategory record);

    List<CategoryVo> selectCategoryByPage(Map<String, Object> criterion);

    Integer selectCategoryCount(Map<String, Object> criterion);

    int selectCountByName(@Param("categoryName") String categoryName);
}