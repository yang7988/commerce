package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.GoodsCategorySpec;
import com.commerce.huayi.entity.db.GoodsCategorySpecExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsCategorySpecMapper {
    int countByExample(GoodsCategorySpecExample example);

    int deleteByExample(GoodsCategorySpecExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodsCategorySpec record);

    int insertSelective(GoodsCategorySpec record);

    List<GoodsCategorySpec> selectByExample(GoodsCategorySpecExample example);

    GoodsCategorySpec selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GoodsCategorySpec record, @Param("example") GoodsCategorySpecExample example);

    int updateByExample(@Param("record") GoodsCategorySpec record, @Param("example") GoodsCategorySpecExample example);

    int updateByPrimaryKeySelective(GoodsCategorySpec record);

    int updateByPrimaryKey(GoodsCategorySpec record);
}