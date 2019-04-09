package com.commerce.huayi.entity.response;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

public class GoodsSpecValueVo {

    //规格描述
    @ApiModelProperty(value = "产品的规格id",required = true)
    @NotNull
    private Long specId;

    //规格名称
    @ApiModelProperty(value = "产品的规格描述",required = true)
    @NotNull
    private String specName;

    //规格描述
    @ApiModelProperty(value = "产品的规格描述",required = true)
    @NotNull
    private String specDescription;

    //规格值
    @ApiModelProperty(value = "产品的规格具体值",required = true)
    @NotNull
    private String specValue;

    //规格值id
    @ApiModelProperty(value = "规格值id",required = true)
    @NotNull
    private Long specValueId;

    private Map<String,String> optionals = new HashMap<>();

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
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

    public Long getSpecValueId() {
        return specValueId;
    }

    public void setSpecValueId(Long specValueId) {
        this.specValueId = specValueId;
    }

    public Map<String, String> getOptionals() {
        return optionals;
    }
}