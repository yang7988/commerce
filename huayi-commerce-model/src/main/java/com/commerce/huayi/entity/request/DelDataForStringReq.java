package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "删除数据json对象")
public class DelDataForStringReq {

    @ApiModelProperty(value = "数据主键id", example = "BIBUVIOEBRFIEBU132132", dataType = "String")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
