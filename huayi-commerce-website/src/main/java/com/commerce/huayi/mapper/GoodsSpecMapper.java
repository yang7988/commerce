package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.GoodsSpec;
import com.commerce.huayi.entity.response.GoodsSpecValueVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GoodsSpecMapper extends Mapper<GoodsSpec> {

    List<GoodsSpecValueVo> getSpecInfos(@Param("startLine") int startLine, @Param("pageMaxSize") int endLine);

    int getSpecInfoCount();
}