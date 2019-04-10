package com.commerce.huayi.service;

import com.commerce.huayi.entity.request.CustomerMessageReq;
import com.commerce.huayi.entity.request.PageRequest;
import com.commerce.huayi.entity.response.CustomerMessagePageVo;

/**
 * 客户留言服务类
 * @date 2019-03-16
 * */
public interface CustomerMessageService {

    CustomerMessagePageVo getCustomerMessages(PageRequest pageRequest);

    void addCustomerMessage(CustomerMessageReq customerMessageReq);

}
