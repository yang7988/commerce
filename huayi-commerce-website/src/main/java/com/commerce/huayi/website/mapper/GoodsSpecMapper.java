package com.commerce.huayi.website.mapper;

import com.commerce.huayi.website.entity.db.GoodsSpec;
import com.commerce.huayi.website.entity.db.GoodsSpecExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsSpecMapper {
    int countByExample(GoodsSpecExample example);

    int deleteByExample(GoodsSpecExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodsSpec record);

    int insertSelective(GoodsSpec record);

    List<GoodsSpec> selectByExample(GoodsSpecExample example);

    GoodsSpec selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GoodsSpec record, @Param("example") GoodsSpecExample example);

    int updateByExample(@Param("record") GoodsSpec record, @Param("example") GoodsSpecExample example);

    int updateByPrimaryKeySelective(GoodsSpec record);

    int updateByPrimaryKey(GoodsSpec record);
}