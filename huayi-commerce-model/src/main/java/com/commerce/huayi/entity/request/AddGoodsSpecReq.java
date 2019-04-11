package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "为某一产品添加规格请求json对象")
public class AddGoodsSpecReq {

    @NotNull
    @ApiModelProperty(value = "产品id",required = true,example = "1")
    private Long id;

    @NotNull
    @ApiModelProperty(value = "产品规格id",required = true,example = "1")
    private Long specId;

    @NotNull
    @ApiModelProperty(value = "产品规格值id",required = true,example = "1")
    private Long specValueId;

    @NotNull
    @ApiModelProperty(value = "产品该规格图片地址",required = true,example = "1")
    private String goodsSpecImageKey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public Long getSpecValueId() {
        return specValueId;
    }

    public void setSpecValueId(Long specValueId) {
        this.specValueId = specValueId;
    }

    public String getGoodsSpecImageKey() {
        return goodsSpecImageKey;
    }

    public void setGoodsSpecImageKey(String goodsSpecImageKey) {
        this.goodsSpecImageKey = goodsSpecImageKey;
    }
}