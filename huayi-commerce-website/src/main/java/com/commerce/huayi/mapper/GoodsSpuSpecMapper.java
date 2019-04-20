package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.GoodsSpuSpec;
import com.commerce.huayi.entity.response.GoodsSpuDetailsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsSpuSpecMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(GoodsSpuSpec record);

    GoodsSpuSpec selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsSpuSpec record);

    List<GoodsSpuDetailsVo> selectGoodsSpecDetails(@Param("spuId") Long id, @Param("specValueId") Long specValueId);

    int selectCountBySpuIdAndSpecValueId(@Param("spuId") Long spuId, @Param("specValueId") Long specValueId);
}