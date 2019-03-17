package com.commerce.huayi.service.impl;

import com.commerce.huayi.entity.db.CustomerMessage;
import com.commerce.huayi.entity.request.CustomerMessageReq;
import com.commerce.huayi.entity.response.CustomerMessageVo;
import com.commerce.huayi.mapper.CustomerMessageMapper;
import com.commerce.huayi.service.CustomerMessageService;
import com.commerce.huayi.utils.BeanCopyUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerMessageServiceImpl implements CustomerMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerMessageServiceImpl.class);

    @Autowired
    private CustomerMessageMapper customerMessageMapper;

    @Override
    public List<CustomerMessageVo> getCustomerMessages() {

        List<CustomerMessage> customerMessageList = customerMessageMapper.getCustomerMessages();
        if(CollectionUtils.isEmpty(customerMessageList)) {
            return null;
        }

        return BeanCopyUtil.copy(CustomerMessageVo.class, customerMessageList);
    }

    @Override
    public void addCustomerMessage(CustomerMessageReq customerMessageReq) {

        LOGGER.info("CustomerMessageServiceImpl->addCustomerMessage:{}",customerMessageReq);
        CustomerMessage customerMessage = new CustomerMessage();

        customerMessageMapper.addCustomerMessage(customerMessage);
    }

}
