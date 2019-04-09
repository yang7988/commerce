package com.commerce.huayi.entity.response;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class GoodsSpecValuePageVo {
    @ApiModelProperty(value = "数据总条数count",required = true,example = "100")
    private int count;

    @ApiModelProperty(value = "规格明细列表list",required = true)
    private List<GoodsSpecValueVo> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<GoodsSpecValueVo> getList() {
        return list;
    }

    public void setList(List<GoodsSpecValueVo> list) {
        this.list = list;
    }
}