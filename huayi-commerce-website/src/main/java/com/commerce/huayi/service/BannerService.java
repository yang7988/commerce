package com.commerce.huayi.service;

import com.commerce.huayi.entity.request.BannerReq;
import com.commerce.huayi.entity.request.DelDataReq;
import com.commerce.huayi.entity.response.BannerVo;

import java.util.List;

public interface BannerService {

    List<BannerVo> getBannerList();

    void addBanner(BannerReq bannerReq);

    void delBanner(DelDataReq delDataReq);

    void updateBanner(BannerReq bannerReq);

}
