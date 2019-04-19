package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.GoodsSpu;
import com.commerce.huayi.entity.response.GoodsSpuDetailsVo;
import com.commerce.huayi.entity.response.GoodsSpuVo;
import com.commerce.huayi.pagination.Condition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsSpuMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(GoodsSpu record);

    GoodsSpu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsSpu record);

    List<GoodsSpuVo> getGoodsByCategoryId(@Param("categoryId") Long categoryId, @Param("offset") Integer offset,
                                          @Param("rowSize") Integer rowSize);

    Integer getGoodsCountByCategoryId(@Param("categoryId") Long categoryId);

    List<GoodsSpuDetailsVo> getGoodsBySpuId(@Param("spuId") Long spuId, @Param("offset") Integer offset,
                                            @Param("rowSize") Integer rowSize);

    Integer getGoodsCountByBySpuId(@Param("spuId") Long spuId);

    List<GoodsSpuVo> searchGoodsSpu(Condition condition);

    int selectCountByName(@Param("goodsName") String goodsName);
}