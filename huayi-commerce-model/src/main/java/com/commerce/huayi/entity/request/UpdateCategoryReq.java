package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
@ApiModel(value = "商品分类更新的请求json对象")
public class UpdateCategoryReq extends CategoryReq {

    @ApiModelProperty(value = "数据主键分类id",example = "1")
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}