package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.NewsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsInfoMapper {
    List<NewsInfo> getNewsInfos(@Param("type") String type, @Param("startLine") int startLine, @Param("pageMaxSize") int endLine);

    int getNewsInfoTotalCount(@Param("type") String type);

    void addNewsInfo(@Param("newsInfo") NewsInfo newsInfo);

    void delNewsInfo(@Param("id") int id);

    NewsInfo getNewsInfo(@Param("id") int id);

    void updateNewsInfo(@Param("newsInfo") NewsInfo newsInfo);
}