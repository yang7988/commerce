package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "商品分类更新的请求json对象")
public class UpdateCategoryReq {

    @ApiModelProperty(value = "数据主键分类id", example = "1")
    @NotNull
    private Long id;

    @ApiModelProperty(value = "分类名", example = "big_earphone")
    private String categoryName;

    //分类描述
    @ApiModelProperty(value = "分类描述", example = "big_earphone")
    private String categoryDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}