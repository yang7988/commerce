package com.commerce.huayi.service.impl;

import com.commerce.huayi.entity.db.ContractInfo;
import com.commerce.huayi.entity.request.ContractInfoReq;
import com.commerce.huayi.entity.response.ContractInfoVo;
import com.commerce.huayi.mapper.ContractInfoMapper;
import com.commerce.huayi.service.ContractInfoService;
import com.commerce.huayi.utils.BeanCopyUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContractInfoServiceImpl implements ContractInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContractInfoServiceImpl.class);

    @Autowired
    private ContractInfoMapper contractInfoMapper;

    @Override
    public List<ContractInfoVo> getContractInfo() {

        List<ContractInfo> contractInfoList = contractInfoMapper.getContractInfo();
        if (CollectionUtils.isEmpty(contractInfoList)) {
            return null;
        }

        return BeanCopyUtil.copy(ContractInfoVo.class, contractInfoList);
    }

    @Override
    public void addContractInfo(ContractInfoReq contractInfoReq) {
        LOGGER.info("ContractInfoServiceImpl->addContractInfo contractInfoReq:{}", contractInfoReq);
        ContractInfo contractInfo = new ContractInfo();
        contractInfo.setDelFlag("0");
        contractInfo.setContent(contractInfoReq.getContent());
        contractInfoMapper.addContractInfo(contractInfo);
    }

    @Override
    public void delContractInfo(int id) {
        LOGGER.info("ContractInfoServiceImpl->delContractInfo id:{}", id);
        contractInfoMapper.delContractInfo(id);
    }

    @Override
    public void updateContractInfo(ContractInfoReq contractInfoReq) {
        LOGGER.info("ContractInfoServiceImpl->updateContractInfo contractInfoReq:{}", contractInfoReq);
        ContractInfo contractInfo = new ContractInfo();
        contractInfo.setId(contractInfoReq.getId());
        contractInfo.setContent(contractInfoReq.getContent());
        contractInfoMapper.updateContractInfo(contractInfo);
    }

}
