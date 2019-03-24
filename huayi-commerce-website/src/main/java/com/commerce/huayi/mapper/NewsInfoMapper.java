package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.NewsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsInfoMapper {

    List<NewsInfo> getNewsInfos();

    void addNewsInfo(@Param("newsInfo") NewsInfo newsInfo);

    void delNewsInfo(@Param("id") int id);

}
