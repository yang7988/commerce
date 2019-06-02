package com.commerce.huayi.service.impl;

import com.commerce.huayi.entity.db.RecruitmentInfo;
import com.commerce.huayi.entity.request.RecruitmentInfoReq;
import com.commerce.huayi.entity.response.RecruitmentInfoVo;
import com.commerce.huayi.mapper.RecruitmentInfoMapper;
import com.commerce.huayi.service.RecruitmentInfoService;
import com.commerce.huayi.utils.BeanCopyUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RecruitmentInfoServiceImpl implements RecruitmentInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecruitmentInfoServiceImpl.class);

    @Autowired
    private RecruitmentInfoMapper recruitmentInfoMapper;

    @Override
    public List<RecruitmentInfoVo> getRecruitmentInfos() {

        List<RecruitmentInfo> recruitmentInfoList = recruitmentInfoMapper.getRecruitmentInfos();
        if (CollectionUtils.isEmpty(recruitmentInfoList)) {
            return null;
        }

        return BeanCopyUtil.copy(RecruitmentInfoVo.class, recruitmentInfoList);
    }

    @Override
    public void addRecruitmentInfo(RecruitmentInfoReq recruitmentInfoReq) {
        LOGGER.info("RecruitmentInfoServiceImpl->addRecruitmentInfo recruitmentInfoReq:{}", recruitmentInfoReq);
        RecruitmentInfo recruitmentInfo = new RecruitmentInfo();
        recruitmentInfo.setDelFlag("0");
        recruitmentInfo.setTitle(recruitmentInfoReq.getTitle());
        recruitmentInfo.setContent(recruitmentInfoReq.getContent());
        recruitmentInfoMapper.addRecruitmentInfo(recruitmentInfo);
    }

    @Override
    public void delRecruitmentInfo(int id) {
        LOGGER.info("RecruitmentInfoServiceImpl->delRecruitmentInfo id:{}", id);
        recruitmentInfoMapper.delRecruitmentInfo(id);
    }

    @Override
    public RecruitmentInfoVo getRecruitmentInfo(int id) {
        LOGGER.info("RecruitmentInfoServiceImpl->getRecruitmentInfo id:{}", id);
        RecruitmentInfo recruitmentInfo = recruitmentInfoMapper.getRecruitmentInfo(id);
        if (null == recruitmentInfo) {
            return null;
        }
        RecruitmentInfoVo recruitmentInfoVo = new RecruitmentInfoVo();
        recruitmentInfoVo.setId(recruitmentInfo.getId());
        recruitmentInfoVo.setTitle(recruitmentInfo.getTitle());
        recruitmentInfoVo.setContent(recruitmentInfo.getContent());
        recruitmentInfoVo.setCreateDate(recruitmentInfo.getCreateDate());
        return recruitmentInfoVo;
    }

    @Override
    public void updateRecruitmentInfo(RecruitmentInfoReq recruitmentInfoReq) {
        LOGGER.info("RecruitmentInfoServiceImpl->updateRecruitmentInfo recruitmentInfoReq:{}", recruitmentInfoReq);
        RecruitmentInfo recruitmentInfo = new RecruitmentInfo();
        recruitmentInfo.setId(recruitmentInfoReq.getId());
        recruitmentInfo.setTitle(recruitmentInfoReq.getTitle());
        recruitmentInfo.setContent(recruitmentInfoReq.getContent());
        recruitmentInfoMapper.updateRecruitmentInfo(recruitmentInfo);
    }

}
