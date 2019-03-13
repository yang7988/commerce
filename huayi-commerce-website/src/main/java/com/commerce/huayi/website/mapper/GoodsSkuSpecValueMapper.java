package com.commerce.huayi.website.mapper;

import com.commerce.huayi.entity.db.GoodsSkuSpecValue;
import com.commerce.huayi.entity.db.GoodsSkuSpecValueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsSkuSpecValueMapper {
    int countByExample(GoodsSkuSpecValueExample example);

    int deleteByExample(GoodsSkuSpecValueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodsSkuSpecValue record);

    int insertSelective(GoodsSkuSpecValue record);

    List<GoodsSkuSpecValue> selectByExample(GoodsSkuSpecValueExample example);

    GoodsSkuSpecValue selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GoodsSkuSpecValue record, @Param("example") GoodsSkuSpecValueExample example);

    int updateByExample(@Param("record") GoodsSkuSpecValue record, @Param("example") GoodsSkuSpecValueExample example);

    int updateByPrimaryKeySelective(GoodsSkuSpecValue record);

    int updateByPrimaryKey(GoodsSkuSpecValue record);
}