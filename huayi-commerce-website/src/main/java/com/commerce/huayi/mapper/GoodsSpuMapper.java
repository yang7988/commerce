package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.GoodsSpu;
import com.commerce.huayi.entity.response.GoodsSpuDetailsVo;
import com.commerce.huayi.entity.response.GoodsSpuVo;
import com.commerce.huayi.pagination.Condition;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface GoodsSpuMapper extends Mapper<GoodsSpu> {


    List<GoodsSpuVo> getGoodsByCategoryId(@Param("categoryId") Long categoryId, @Param("offset") Integer offset,
                                          @Param("rowSize") Integer rowSize);

    Integer getGoodsCountByCategoryId(@Param("categoryId") Long categoryId);

    List<GoodsSpuDetailsVo> getGoodsBySpuId(@Param("spuId") Long spuId,@Param("offset") Integer offset,
                                                 @Param("rowSize") Integer rowSize);

    Integer getGoodsCountByBySpuId(@Param("spuId") Long spuId);

    List<GoodsSpuVo> searchGoodsSpu(Condition condition);
}