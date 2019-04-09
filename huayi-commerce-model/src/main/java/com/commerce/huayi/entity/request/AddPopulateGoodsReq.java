package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class AddPopulateGoodsReq {

    @ApiModelProperty(value = "产品id",required = true,example = "1")
    @NotNull
    private Long goodsId;

    @ApiModelProperty(value = "产品的规格值id",required = true,example = "1")
    @NotNull
    private Long specValueId;

    @ApiModelProperty(value = "产品的分类id",required = true,example = "1")
    @NotNull
    private Long categoryId;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSpecValueId() {
        return specValueId;
    }

    public void setSpecValueId(Long specValueId) {
        this.specValueId = specValueId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}