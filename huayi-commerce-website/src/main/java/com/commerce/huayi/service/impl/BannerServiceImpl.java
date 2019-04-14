package com.commerce.huayi.service.impl;

import com.commerce.huayi.entity.db.Banner;
import com.commerce.huayi.entity.request.BannerReq;
import com.commerce.huayi.entity.request.DelDataReq;
import com.commerce.huayi.entity.response.BannerVo;
import com.commerce.huayi.mapper.BannerMapper;
import com.commerce.huayi.service.BannerService;
import com.commerce.huayi.utils.BeanCopyUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<BannerVo> getBannerList() {
        List<Banner> bannerList = bannerMapper.getBannerList();
        if(CollectionUtils.isEmpty(bannerList)) {
            return null;
        }
        return BeanCopyUtil.copy(BannerVo.class, bannerList);
    }

    @Override
    public void addBanner(BannerReq bannerReq) {
        Banner banner = new Banner();
        banner.setImageKey(bannerReq.getImageKey());
        banner.setUrl(bannerReq.getUrl());
        banner.setDelFlag("0");
        bannerMapper.addBanner(banner);
    }

    @Override
    public void delBanner(DelDataReq delDataReq) {
        bannerMapper.delBanner(delDataReq.getId());
    }

    @Override
    public void updateBanner(BannerReq bannerReq) {
        Banner banner = new Banner();
        banner.setId(bannerReq.getId());
        banner.setImageKey(bannerReq.getImageKey());
        banner.setUrl(bannerReq.getUrl());
        bannerMapper.updateBanner(banner);
    }

}
