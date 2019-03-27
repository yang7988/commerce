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

    @ApiModelProperty(value = "数据总条数count",required = true)
    private int count;

    @ApiModelProperty(value = "留言明细列表list",required = true)
    private List<CustomerMessageVo> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CustomerMessageVo> getList() {
        return list;
    }

    public void setList(List<CustomerMessageVo> list) {
        this.list = list;
    }
}
