package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "获取分类的商品数据分页请求json对象")
public class PageCategoryGoodsRequest extends PageRequest {

    @ApiModelProperty(value = "分类id",example = "1")
    @NotNull
    private Long id;

    @ApiModelProperty(value = "分类名可选",example = "1")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}