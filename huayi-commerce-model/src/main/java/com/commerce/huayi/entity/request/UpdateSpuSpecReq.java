package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "更新规格请求json对象")
public class UpdateSpuSpecReq {
    @NotNull
    @ApiModelProperty(value = "产品的规格id", required = true, example = "1")
    private Long id;

    @NotNull
    @ApiModelProperty(value = "产品的规格值id", required = true, example = "1")
    private Long specValueId;

    //规格名称
    @ApiModelProperty(value = "产品的规格名称", required = true, example = "color")
    private String specName;

    //规格描述
    @ApiModelProperty(value = "产品的规格描述", required = true, example = "describe_color")
    private String specDescription;

    //规格值
    @ApiModelProperty(value = "产品的规格具体值", required = true, example = "red")
    private String specValue;

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

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getSpecDescription() {
        return specDescription;
    }

    public void setSpecDescription(String specDescription) {
        this.specDescription = specDescription;
    }

    public String getSpecValue() {
        return specValue;
    }

    public void setSpecValue(String specValue) {
        this.specValue = specValue;
    }
}