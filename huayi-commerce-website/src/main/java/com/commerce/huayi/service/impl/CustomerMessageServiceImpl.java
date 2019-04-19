package com.commerce.huayi.service.impl;

import com.commerce.huayi.entity.db.CustomerMessage;
import com.commerce.huayi.entity.request.CustomerMessageReq;
import com.commerce.huayi.entity.request.PageRequest;
import com.commerce.huayi.entity.response.CustomerMessageVo;
import com.commerce.huayi.mapper.CustomerMessageMapper;
import com.commerce.huayi.pagination.Page;
import com.commerce.huayi.service.CustomerMessageService;
import com.commerce.huayi.utils.BeanCopyUtil;
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
    public Page<CustomerMessageVo> getCustomerMessages(PageRequest pageRequest) {

        int count = customerMessageMapper.getCustomerMessagesTotalCount();
        Page<CustomerMessageVo> page = Page.create(pageRequest.getPageIndex(), pageRequest.getPageMaxSize(), count);
        if (count <= 0) {
            return page;
        }
        List<CustomerMessage> customerMessageList = customerMessageMapper.getCustomerMessages(page.getOffset(), page.getPageMaxSize());
        page.setList(BeanCopyUtil.copy(CustomerMessageVo.class, customerMessageList));
        return page;
    }

    @Override
    public void addCustomerMessage(CustomerMessageReq customerMessageReq) {

        LOGGER.info("CustomerMessageServiceImpl->addCustomerMessage:{}", customerMessageReq);
        CustomerMessage customerMessage = new CustomerMessage();

        customerMessageMapper.addCustomerMessage(customerMessage);
    }

}
