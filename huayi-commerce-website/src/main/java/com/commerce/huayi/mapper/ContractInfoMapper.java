package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.ContractInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContractInfoMapper {
    List<ContractInfo> getContractInfo();

    void addContractInfo(@Param("contractInfo") ContractInfo contractInfo);

    void delContractInfo(@Param("id") int id);

    void updateContractInfo(@Param("contractInfo") ContractInfo contractInfo);
}