package com.commerce.huayi.service;

import com.commerce.huayi.entity.request.CompanyInfoReq;
import com.commerce.huayi.entity.response.CompanyInfoVo;

import java.util.List;

/**
 * 公司介绍服务类
 * @date 2019-03-16
 * */
public interface CompanyInfoService {

    List<CompanyInfoVo> getCompanyInfo();

    void addCompanyInfo(CompanyInfoReq companyInfoReq);

    void delCompanyInfo(int id);

    void updateCompanyInfo(CompanyInfoReq companyInfoReq);

}
