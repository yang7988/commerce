package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.CustomerMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMessageMapper {
    List<CustomerMessage> getCustomerMessages(@Param("startLine") int startLine, @Param("pageMaxSize") int endLine);

    int getCustomerMessagesTotalCount();

    void addCustomerMessage(@Param("customerMessage") CustomerMessage customerMessage);

    void addCustomerMessageForGoods(@Param("mId") String mId,@Param("gId") String gId);

    List<Long> getGoodIdsForCustomerMessagesId(@Param("mId") String mId);

    List<CustomerMessage> getExportCustomerMessages();

    void delCustomerMessage(@Param("id") String id);


}