package com.commerce.huayi.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 新闻中心
 * @date 2019-03-16
 * */
@ApiModel(value = "新闻中心信息response body")
public class NewsInfoPageVo {

    @ApiModelProperty(value = "数据总条数count",required = true)
    private int count;

    @ApiModelProperty(value = "新闻中心信息明细列表list",required = true)
    private List<NewsInfoVo> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<NewsInfoVo> getList() {
        return list;
    }

    public void setList(List<NewsInfoVo> list) {
        this.list = list;
    }
}
