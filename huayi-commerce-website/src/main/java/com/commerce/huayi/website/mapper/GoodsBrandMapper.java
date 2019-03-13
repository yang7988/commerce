package com.commerce.huayi.website.mapper;

import com.commerce.huayi.website.entity.db.GoodsBrand;
import com.commerce.huayi.website.entity.db.GoodsBrandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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