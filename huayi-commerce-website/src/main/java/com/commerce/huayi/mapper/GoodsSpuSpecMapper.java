package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.GoodsSpuSpec;
import com.commerce.huayi.entity.response.GoodsSpuDetailsVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface GoodsSpuSpecMapper extends Mapper<GoodsSpuSpec> {

    GoodsSpuDetailsVo selectGoodsSpecDetails(@Param("spuId") Long id, @Param("specValueId") Long specValueId);
}