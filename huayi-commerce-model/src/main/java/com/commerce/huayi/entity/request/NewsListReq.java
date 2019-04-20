package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新闻中心
 *
 * @date 2019-03-16
 */
@ApiModel(value = "新闻中心列表请求json对象")
public class NewsListReq extends PageRequest {

    @ApiModelProperty(value = "新闻类型type", example = "1:公司 2:行业", dataType = "String")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
