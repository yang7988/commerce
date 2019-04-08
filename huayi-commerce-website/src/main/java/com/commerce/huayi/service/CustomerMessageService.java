package com.commerce.huayi.service;

import com.commerce.huayi.entity.request.CustomerMessageReq;
import com.commerce.huayi.entity.request.PageReq;
import com.commerce.huayi.entity.response.CustomerMessagePageVo;

/**
 * 客户留言服务类
 * @date 2019-03-16
 * */
public interface CustomerMessageService {

    CustomerMessagePageVo getCustomerMessages(PageReq pageReq);

    void addCustomerMessage(CustomerMessageReq customerMessageReq);

}
