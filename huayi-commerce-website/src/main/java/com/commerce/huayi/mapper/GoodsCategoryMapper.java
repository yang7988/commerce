package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.GoodsCategory;
import com.commerce.huayi.entity.response.CategoryVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GoodsCategoryMapper extends Mapper<GoodsCategory> {

    List<CategoryVo> selectCategoryByPage(@Param("categoryId") Long categoryId,@Param("categoryName") String categoryName,
                                          @Param("offset") Integer offset,@Param("rowSize") Integer rowSize);

    Integer selectCategoryCountByPage(@Param("categoryId") Long categoryId,@Param("categoryName") String categoryName);
}