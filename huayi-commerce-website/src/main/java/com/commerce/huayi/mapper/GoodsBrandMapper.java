package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.GoodsBrand;

public interface GoodsBrandMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(GoodsBrand record);

    GoodsBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsBrand record);
}