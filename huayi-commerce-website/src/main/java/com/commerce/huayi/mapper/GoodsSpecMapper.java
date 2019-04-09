package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.GoodsSpec;
import com.commerce.huayi.entity.response.GoodsSpecValueVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GoodsSpecMapper extends Mapper<GoodsSpec> {

    List<GoodsSpecValueVo> getSpecInfos(int startLine, int pageMaxSzie);

    int getSpecInfoCount();
}