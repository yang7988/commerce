package com.commerce.huayi.service;

import com.commerce.huayi.entity.request.ContractInfoReq;
import com.commerce.huayi.entity.response.ContractInfoVo;

import java.util.List;

/**
 * 联系我们服务类
 * @date 2019-03-16
 * */
public interface ContractInfoService {

    List<ContractInfoVo> getContractInfo();

    void addContractInfo(ContractInfoReq contractInfoReq);

    void delContractInfo(int id);

    void updateContractInfo(ContractInfoReq contractInfoReq);

}
