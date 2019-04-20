package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "商品分类的请求json对象")
public class CategoryReq  {

    //父级分类id
    @ApiModelProperty(value = "分类父id",example = "0")
    @NotNull
    private Long parentId;

    //分类名称
    @ApiModelProperty(value = "分类名",example = "big_earphone")
    @NotNull
    private String categoryName;

    //分类描述
    @ApiModelProperty(value = "分类描述",example = "big_earphone")
    @NotNull
    private String categoryDescription;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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