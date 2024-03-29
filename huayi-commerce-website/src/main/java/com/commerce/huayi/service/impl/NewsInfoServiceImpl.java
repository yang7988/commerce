package com.commerce.huayi.service.impl;

import com.commerce.huayi.entity.db.NewsInfo;
import com.commerce.huayi.entity.request.NewsInfoReq;
import com.commerce.huayi.entity.request.NewsListReq;
import com.commerce.huayi.entity.response.NewsInfoVo;
import com.commerce.huayi.mapper.NewsInfoMapper;
import com.commerce.huayi.pagination.Page;
import com.commerce.huayi.service.NewsInfoService;
import com.commerce.huayi.utils.BeanCopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsInfoServiceImpl implements NewsInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsInfoServiceImpl.class);

    @Autowired
    private NewsInfoMapper newsInfoMapper;

    @Override
    public Page<NewsInfoVo> getNewsInfos(NewsListReq pageRequest) {
        int count = newsInfoMapper.getNewsInfoTotalCount(pageRequest.getType());
        Page<NewsInfoVo> page = Page.create(pageRequest.getPageIndex(), pageRequest.getPageMaxSize(), count);
        if (count <= 0) {
            return page;
        }
        List<NewsInfo> newsInfoList = newsInfoMapper.getNewsInfos(pageRequest.getType(), page.getOffset(), page.getPageMaxSize());
        page.setList(BeanCopyUtil.copy(NewsInfoVo.class, newsInfoList));
        return page;
    }

    @Override
    public NewsInfoVo getNewsInfo(int id) {
        LOGGER.info("NewsInfoServiceImpl->getNewsInfo id:{}", id);
        NewsInfo newsInfo = newsInfoMapper.getNewsInfo(id);
        if (null == newsInfo) {
            return null;
        }
        NewsInfoVo newsInfoVo = new NewsInfoVo();
        newsInfoVo.setId(newsInfo.getId());
        newsInfoVo.setTitle(newsInfo.getTitle());
        newsInfoVo.setType(newsInfo.getType());
        newsInfoVo.setContent(newsInfo.getContent());
        newsInfoVo.setCreateDate(newsInfo.getCreateDate());
        return newsInfoVo;
    }

    @Override
    public void addNewsInfo(NewsInfoReq newsInfoReq) {
        LOGGER.info("NewsInfoServiceImpl->addNewsInfo newsInfoReq:{}", newsInfoReq);
        NewsInfo newsInfo = new NewsInfo();
        newsInfo.setDelFlag("0");
        newsInfo.setTitle(newsInfoReq.getTitle());
        newsInfo.setType(newsInfoReq.getType());
        newsInfo.setContent(newsInfoReq.getContent());
        newsInfoMapper.addNewsInfo(newsInfo);
    }

    @Override
    public void delNewsInfo(int id) {
        LOGGER.info("NewsInfoServiceImpl->delNewsInfo id:{}", id);
        newsInfoMapper.delNewsInfo(id);
    }

    @Override
    public void updateNewsInfo(NewsInfoReq newsInfoReq) {
        LOGGER.info("NewsInfoServiceImpl->updateNewsInfo newsInfoReq:{}", newsInfoReq);
        NewsInfo newsInfo = new NewsInfo();
        newsInfo.setDelFlag("0");
        newsInfo.setId(newsInfoReq.getId());
        newsInfo.setTitle(newsInfoReq.getTitle());
        newsInfo.setType(newsInfoReq.getType());
        newsInfo.setContent(newsInfoReq.getContent());
        newsInfoMapper.updateNewsInfo(newsInfo);
    }

}
