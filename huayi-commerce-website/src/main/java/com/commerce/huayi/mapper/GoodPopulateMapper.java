package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.GoodPopulate;
import com.commerce.huayi.entity.response.GoodsSpuDetailsVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GoodPopulateMapper extends Mapper<GoodPopulate> {
    int selectPopulateGoodsCount(@Param("categoryId") Long id);

    List<GoodsSpuDetailsVo> selectPopulateGoodsByPage(@Param("categoryId") Long categoryId, @Param("offset") Integer offset,
                                                      @Param("rowSize") Integer rowSize);
}