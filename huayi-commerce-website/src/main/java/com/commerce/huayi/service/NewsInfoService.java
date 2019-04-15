package com.commerce.huayi.service;

import com.commerce.huayi.entity.request.NewsInfoReq;
import com.commerce.huayi.entity.request.NewsListReq;
import com.commerce.huayi.entity.response.NewsInfoVo;
import com.commerce.huayi.pagination.Page;

/**
 * 新闻中心服务类
 * @date 2019-03-16
 * */
public interface NewsInfoService {

    Page<NewsInfoVo> getNewsInfos(NewsListReq pageRequest);

    NewsInfoVo getNewsInfo(int id);

    void addNewsInfo(NewsInfoReq newsInfoReq);

    void delNewsInfo(int id);

    void updateNewsInfo(NewsInfoReq newsInfoReq);

}
