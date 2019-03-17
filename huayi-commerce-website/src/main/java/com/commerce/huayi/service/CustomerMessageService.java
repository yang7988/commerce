package com.commerce.huayi.service;

import com.commerce.huayi.entity.request.CustomerMessageReq;
import com.commerce.huayi.entity.response.CustomerMessageVo;

import java.util.List;

/**
 * 客户留言服务类
 * @date 2019-03-16
 * */
public interface CustomerMessageService {

    List<CustomerMessageVo> getCustomerMessages();

    void addCustomerMessage(CustomerMessageReq customerMessageReq);

}
