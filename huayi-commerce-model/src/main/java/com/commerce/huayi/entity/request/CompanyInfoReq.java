package com.commerce.huayi.entity.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 公司介绍信息
 * @date 2019-03-16
 *
 * */
@ApiModel(value = "公司介绍信息请求json对象")
public class CompanyInfoReq {

    @ApiModelProperty(value = "介绍内容content",example = "HuaYi公司，最牛的耳机公司",dataType = "String")
    private String content;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
