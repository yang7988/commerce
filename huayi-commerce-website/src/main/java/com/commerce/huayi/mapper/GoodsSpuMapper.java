package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.GoodsSpu;
import com.commerce.huayi.entity.response.GoodsSpuDetailsVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface GoodsSpuMapper extends Mapper<GoodsSpu> {

    List<GoodsSpu> search(@Param("keyWord") String keyWord);

    List<Map<String,String>> searchBySql(Map<String, String> sqlMap);

    List<GoodsSpuDetailsVo> getGoodsByCategoryId(@Param("categoryId") Long categoryId);

    Integer getGoodsCountByCategoryId(@Param("categoryId") Long categoryId);
}