package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.GoodsBrand;
import com.commerce.huayi.entity.db.GoodsBrandExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsBrandMapper {
    int countByExample(GoodsBrandExample example);

    int deleteByExample(GoodsBrandExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodsBrand record);

    int insertSelective(GoodsBrand record);

    List<GoodsBrand> selectByExample(GoodsBrandExample example);

    GoodsBrand selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GoodsBrand record, @Param("example") GoodsBrandExample example);

    int updateByExample(@Param("record") GoodsBrand record, @Param("example") GoodsBrandExample example);

    int updateByPrimaryKeySelective(GoodsBrand record);

    int updateByPrimaryKey(GoodsBrand record);
}