package com.commerce.huayi.service;

import com.commerce.huayi.entity.request.RecruitmentInfoReq;
import com.commerce.huayi.entity.response.RecruitmentInfoVo;

import java.util.List;

/**
 * 人才中心服务类
 * @date 2019-03-16
 * */
public interface RecruitmentInfoService {

    List<RecruitmentInfoVo> getRecruitmentInfos();

    void addRecruitmentInfo(RecruitmentInfoReq recruitmentInfoReq);

    void delRecruitmentInfo(int id);

    RecruitmentInfoVo getRecruitmentInfo(int id);

    void updateRecruitmentInfo(RecruitmentInfoReq recruitmentInfoReq);

}
