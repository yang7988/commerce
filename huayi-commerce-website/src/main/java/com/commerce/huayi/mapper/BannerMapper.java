package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerMapper {

    List<Banner> getBannerList();

    void addBanner(@Param("banner") Banner banner);

    void delBanner(@Param("id") int id);

    void updateBanner(@Param("banner") Banner banner);


}
