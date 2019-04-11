package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.GoodsCategory;
import com.commerce.huayi.entity.response.CategoryVo;
import com.commerce.huayi.pagination.Condition;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GoodsCategoryMapper extends Mapper<GoodsCategory> {

    List<CategoryVo> selectCategoryByPage(Condition condition);

    Integer selectCategoryCount(Condition condition);
}