package com.commerce.huayi.entity.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 联系我们
 * @date 2019-03-16
 * */
@ApiModel(value = "联系我们信息请求json对象")
public class ContractInfoReq {

    @ApiModelProperty(value = "主键id",example = "1",dataType = "int")
    private int id;

    @ApiModelProperty(value = "联系我们内容content",example = "HuaYi公司",dataType = "String")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
