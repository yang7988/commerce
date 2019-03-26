package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.CustomerMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMessageMapper {

    List<CustomerMessage> getCustomerMessages(@Param("startLine") int startLine, @Param("pageMaxSize") int endLine);

    int getCustomerMessagesTotalCount();

    void addCustomerMessage(@Param("customerMessage") CustomerMessage customerMessage);

}
