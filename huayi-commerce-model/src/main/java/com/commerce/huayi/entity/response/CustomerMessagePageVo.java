package com.commerce.huayi.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 客户留言
 * @date 2019-03-16
 * */
@ApiModel(value = "客户留言信息response body")
public class CustomerMessagePageVo {

    @ApiModelProperty(value = "数据总条数totalCount",required = true)
    private int totalCount;

    @ApiModelProperty(value = "留言明细列表customerMessageVoList",required = true)
    private List<CustomerMessageVo> customerMessageVoList;


    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<CustomerMessageVo> getCustomerMessageVoList() {
        return customerMessageVoList;
    }

    public void setCustomerMessageVoList(List<CustomerMessageVo> customerMessageVoList) {
        this.customerMessageVoList = customerMessageVoList;
    }
}
