package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.GoodsSpuSpec;
import com.commerce.huayi.entity.db.GoodsSpuSpecExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsSpuSpecMapper {
    int countByExample(GoodsSpuSpecExample example);

    int deleteByExample(GoodsSpuSpecExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodsSpuSpec record);

    int insertSelective(GoodsSpuSpec record);

    List<GoodsSpuSpec> selectByExample(GoodsSpuSpecExample example);

    GoodsSpuSpec selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GoodsSpuSpec record, @Param("example") GoodsSpuSpecExample example);

    int updateByExample(@Param("record") GoodsSpuSpec record, @Param("example") GoodsSpuSpecExample example);

    int updateByPrimaryKeySelective(GoodsSpuSpec record);

    int updateByPrimaryKey(GoodsSpuSpec record);
}