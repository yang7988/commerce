package com.commerce.huayi.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 公司介绍信息
 * @date 2019-03-16
 *
 * */
@ApiModel(value = "公司介绍信息response body")
public class CompanyInfoVo {

    @ApiModelProperty(value = "主键id",required = true)
    private int id;

    @ApiModelProperty(value = "介绍内容content",required = true)
    private String content;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
