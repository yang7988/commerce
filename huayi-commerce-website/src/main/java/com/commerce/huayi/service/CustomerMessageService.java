package com.commerce.huayi.service;

import com.commerce.huayi.entity.request.CustomerMessageReq;
import com.commerce.huayi.entity.request.PageRequest;
import com.commerce.huayi.entity.response.CustomerMessageVo;
import com.commerce.huayi.pagination.Page;

/**
 * 客户留言服务类
 *
 * @date 2019-03-16
 */
public interface CustomerMessageService {

    Page<CustomerMessageVo> getCustomerMessages(PageRequest pageRequest);

    void addCustomerMessage(CustomerMessageReq customerMessageReq);

}
