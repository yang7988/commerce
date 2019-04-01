package com.commerce.huayi.service;

import com.commerce.huayi.entity.request.NewsInfoReq;
import com.commerce.huayi.entity.request.PageReq;
import com.commerce.huayi.entity.response.NewsInfoPageVo;
import com.commerce.huayi.entity.response.NewsInfoVo;

/**
 * 新闻中心服务类
 * @date 2019-03-16
 * */
public interface NewsInfoService {

    NewsInfoPageVo getNewsInfos(PageReq pageReq);

    NewsInfoVo getNewsInfo(int id);

    void addNewsInfo(NewsInfoReq newsInfoReq);

    void delNewsInfo(int id);

    void updateNewsInfo(NewsInfoReq newsInfoReq);

}
