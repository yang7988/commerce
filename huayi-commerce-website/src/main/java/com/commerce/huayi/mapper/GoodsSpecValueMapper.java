package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.GoodsSpecValue;
import org.apache.ibatis.annotations.Param;

public interface GoodsSpecValueMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(GoodsSpecValue record);

    GoodsSpecValue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsSpecValue record);

    int selectCountBySpecIdAndValue(@Param("specId") Long specId, @Param("specValue") String specValue);
}