package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "添加规格请求json对象")
public class AddSpuSpecReq {

    //规格名称
    @ApiModelProperty(value = "产品的规格名称",required = true,example = "color")
    @NotNull
    private String specName;

    //规格描述
    @ApiModelProperty(value = "产品的规格描述",required = true,example = "describe_color")
    @NotNull
    private String specDescription;

    //规格值
    @ApiModelProperty(value = "产品的规格具体值",required = true,example = "red")
    @NotNull
    private String specValue;

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