package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.GoodsSpec;
import com.commerce.huayi.entity.response.GoodsSpecValueVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsSpecMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(GoodsSpec record);

    GoodsSpec selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsSpec record);

    List<GoodsSpecValueVo> getSpecInfos(@Param("startLine") int startLine, @Param("pageMaxSize") int endLine);

    int getSpecInfoCount();

    int selectCountByName(@Param("specName") String specName);

    List<GoodsSpec> selectByName(@Param("specName") String specName);
}