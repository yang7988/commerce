package com.commerce.huayi.service.impl;

import com.commerce.huayi.entity.db.CompanyInfo;
import com.commerce.huayi.entity.request.CompanyInfoReq;
import com.commerce.huayi.entity.response.CompanyInfoVo;
import com.commerce.huayi.mapper.CompanyInfoMapper;
import com.commerce.huayi.service.CompanyInfoService;
import com.commerce.huayi.utils.BeanCopyUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyInfoServiceImpl.class);

    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    @Override
    public List<CompanyInfoVo> getCompanyInfo() {
        List<CompanyInfo> companyInfoList = companyInfoMapper.getCompanyInfo();
        if(CollectionUtils.isEmpty(companyInfoList)) {
            return null;
        }
        return BeanCopyUtil.copy(CompanyInfoVo.class, companyInfoList);
    }

    @Override
    public void addCompanyInfo(CompanyInfoReq companyInfoReq) {
        LOGGER.info("CompanyInfoServiceImpl->addCompanyInfo companyInfoReq:{}",companyInfoReq);
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setDelFlag("0");
        companyInfo.setCreateDate(new Date());
        companyInfo.setContent(companyInfoReq.getContent());
        companyInfoMapper.addCompanyInfo(companyInfo);
    }

    @Override
    public void delCompanyInfo(int id) {
        LOGGER.info("CompanyInfoServiceImpl->delCompanyInfo id:{}",id);
        companyInfoMapper.delCompanyInfo(id);
    }

}
