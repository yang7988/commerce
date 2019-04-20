package com.commerce.huayi.entity.request;

import com.commerce.huayi.annotation.Dictionary;
import com.commerce.huayi.annotation.Pretreatment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@ApiModel(value = "添加规格请求json对象")
@Pretreatment
@Dictionary
public class AddSpuSpecReq {

    //规格名称
    @ApiModelProperty(value = "产品的规格名称",required = true,example = "color")
    @NotNull
    @Pretreatment
    private String specName;

    //规格描述
    @ApiModelProperty(value = "产品的规格描述",required = true,example = "describe_color")
    @NotNull
    @Pretreatment
    private String specDescription;

    //规格值
    @ApiModelProperty(value = "产品的规格具体值",required = true,example = "red")
    @NotNull
    @Pretreatment
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