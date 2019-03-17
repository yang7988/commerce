package com.commerce.huayi.service.impl;

import com.commerce.huayi.entity.db.NewsInfo;
import com.commerce.huayi.entity.request.NewsInfoReq;
import com.commerce.huayi.entity.response.NewsInfoVo;
import com.commerce.huayi.mapper.NewsInfoMapper;
import com.commerce.huayi.service.NewsInfoService;
import com.commerce.huayi.utils.BeanCopyUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NewsInfoServiceImpl implements NewsInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsInfoServiceImpl.class);

    @Autowired
    private NewsInfoMapper newsInfoMapper;

    @Override
    public List<NewsInfoVo> getNewsInfos() {

        List<NewsInfo> newsInfoList = newsInfoMapper.getNewsInfos();
        if(CollectionUtils.isEmpty(newsInfoList)) {
            return null;
        }

        return BeanCopyUtil.copy(NewsInfoVo.class, newsInfoList);
    }

    @Override
    public void addNewsInfo(NewsInfoReq newsInfoReq) {
        LOGGER.info("NewsInfoServiceImpl->addNewsInfo newsInfoReq:{}",newsInfoReq);
        NewsInfo newsInfo = new NewsInfo();
        newsInfo.setDelFlag("0");
        newsInfo.setCreateDate(new Date());
        newsInfo.setTitle(newsInfoReq.getTitle());
        newsInfo.setContent(newsInfoReq.getContent());
        newsInfo.setEffectDate(newsInfoReq.getEffectDate());
        newsInfoMapper.addNewsInfo(newsInfo);
    }

    @Override
    public void delNewsInfo(String id) {
        LOGGER.info("NewsInfoServiceImpl->delNewsInfo id:{}",id);
        newsInfoMapper.delNewsInfo(id);
    }

}
