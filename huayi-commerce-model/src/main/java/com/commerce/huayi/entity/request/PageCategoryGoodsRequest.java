package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class PageCategoryGoodsRequest extends PageRequest {

    @ApiModelProperty(value = "数据主键id",example = "1")
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}