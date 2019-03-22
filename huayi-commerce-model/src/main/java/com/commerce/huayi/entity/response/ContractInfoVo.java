package com.commerce.huayi.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 联系我们
 * @date 2019-03-16
 * */
@ApiModel(value = "联系我们信息response body")
public class ContractInfoVo {

    @ApiModelProperty(value = "主键id",required = true)
    private int id;

    @ApiModelProperty(value = "联系我们内容content",required = true)
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
