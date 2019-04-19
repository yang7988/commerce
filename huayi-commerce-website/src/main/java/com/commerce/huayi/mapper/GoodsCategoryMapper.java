package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.GoodsCategory;
import com.commerce.huayi.entity.response.CategoryVo;
import com.commerce.huayi.pagination.Condition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(GoodsCategory record);

    GoodsCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsCategory record);

    List<CategoryVo> selectCategoryByPage(Condition condition);

    Integer selectCategoryCount(Condition condition);

    int selectCountByName(@Param("categoryName") String categoryName);
}