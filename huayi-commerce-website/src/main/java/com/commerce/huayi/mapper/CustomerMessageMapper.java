package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.CustomerMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMessageMapper {

    List<CustomerMessage> getCustomerMessages();

    void addCustomerMessage(@Param("customerMessage") CustomerMessage customerMessage);

}
