package com.commerce.huayi.website.mapper;

import com.commerce.huayi.entity.db.GoodsSpecValue;
import com.commerce.huayi.entity.db.GoodsSpecValueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsSpecValueMapper {
    int countByExample(GoodsSpecValueExample example);

    int deleteByExample(GoodsSpecValueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodsSpecValue record);

    int insertSelective(GoodsSpecValue record);

    List<GoodsSpecValue> selectByExample(GoodsSpecValueExample example);

    GoodsSpecValue selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GoodsSpecValue record, @Param("example") GoodsSpecValueExample example);

    int updateByExample(@Param("record") GoodsSpecValue record, @Param("example") GoodsSpecValueExample example);

    int updateByPrimaryKeySelective(GoodsSpecValue record);

    int updateByPrimaryKey(GoodsSpecValue record);
}