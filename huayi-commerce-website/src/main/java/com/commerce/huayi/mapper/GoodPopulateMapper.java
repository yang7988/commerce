package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.GoodPopulate;
import com.commerce.huayi.entity.response.GoodsSpuDetailsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodPopulateMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(GoodPopulate record);

    GoodPopulate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodPopulate record);

    int selectPopulateGoodsCount(@Param("categoryId") Long id);

    List<GoodsSpuDetailsVo> selectPopulateGoodsByPage(@Param("categoryId") Long categoryId, @Param("offset") Integer offset,
                                                      @Param("rowSize") Integer rowSize);
}