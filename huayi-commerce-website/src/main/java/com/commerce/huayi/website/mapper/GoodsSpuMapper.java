package com.commerce.huayi.website.mapper;

import com.commerce.huayi.entity.db.GoodsSpu;
import com.commerce.huayi.entity.db.GoodsSpuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsSpuMapper {
    int countByExample(GoodsSpuExample example);

    int deleteByExample(GoodsSpuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodsSpu record);

    int insertSelective(GoodsSpu record);

    List<GoodsSpu> selectByExample(GoodsSpuExample example);

    GoodsSpu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GoodsSpu record, @Param("example") GoodsSpuExample example);

    int updateByExample(@Param("record") GoodsSpu record, @Param("example") GoodsSpuExample example);

    int updateByPrimaryKeySelective(GoodsSpu record);

    int updateByPrimaryKey(GoodsSpu record);
}