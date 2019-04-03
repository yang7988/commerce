package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.CompanyInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CompanyInfoMapper extends Mapper<CompanyInfo> {
    List<CompanyInfo> getCompanyInfo();

    void addCompanyInfo(@Param("companyInfo") CompanyInfo companyInfo);

    void delCompanyInfo(@Param("id") int id);

    void updateCompanyInfo(@Param("companyInfo") CompanyInfo companyInfo);
}