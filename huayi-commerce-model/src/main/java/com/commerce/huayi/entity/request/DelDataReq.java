package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "删除信息请求json对象")
public class DelDataReq {

    @ApiModelProperty(value = "数据主键id",example = "1",dataType = "int")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
