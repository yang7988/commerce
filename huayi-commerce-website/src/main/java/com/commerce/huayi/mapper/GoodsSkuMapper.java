package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.GoodsSku;
import com.commerce.huayi.entity.db.GoodsSkuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsSkuMapper {
    int countByExample(GoodsSkuExample example);

    int deleteByExample(GoodsSkuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodsSku record);

    int insertSelective(GoodsSku record);

    List<GoodsSku> selectByExample(GoodsSkuExample example);

    GoodsSku selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GoodsSku record, @Param("example") GoodsSkuExample example);

    int updateByExample(@Param("record") GoodsSku record, @Param("example") GoodsSkuExample example);

    int updateByPrimaryKeySelective(GoodsSku record);

    int updateByPrimaryKey(GoodsSku record);
}