package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "某产品具体规格详情请求json对象")
public class GoodsSpecDetailsReq {

    @ApiModelProperty(value = "产品主键id", example = "1")
    @NotNull
    private Long id;

    @ApiModelProperty(value = "产品规格值主键id", example = "1")
    @NotNull
    private Long specValueId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpecValueId() {
        return specValueId;
    }

    public void setSpecValueId(Long specValueId) {
        this.specValueId = specValueId;
    }
}