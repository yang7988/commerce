package com.commerce.huayi.service;

import com.commerce.huayi.entity.request.NewsInfoReq;
import com.commerce.huayi.entity.response.NewsInfoVo;

import java.util.List;

/**
 * 新闻中心服务类
 * @date 2019-03-16
 * */
public interface NewsInfoService {

    List<NewsInfoVo> getNewsInfos();

    void addNewsInfo(NewsInfoReq newsInfoReq);

    void delNewsInfo(int id);

}
