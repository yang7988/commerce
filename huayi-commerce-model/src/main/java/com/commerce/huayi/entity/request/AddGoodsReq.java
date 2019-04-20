package com.commerce.huayi.entity.request;

import com.commerce.huayi.annotation.Dictionary;
import com.commerce.huayi.annotation.Pretreatment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@ApiModel(value = "产品添加的请求json对象")
@Pretreatment
@Dictionary
public class AddGoodsReq  {

    //商品名称
    @ApiModelProperty(value = "产品名称",required = true,example = "blue_earphone")
    @NotNull
    @Pretreatment
    private String goodsName;

    //商品描述
    @ApiModelProperty(value = "产品描述",required = true,example = "blue_earphone")
    @NotNull
    @Pretreatment
    private String goodsDescription;

    //商品描述
    @ApiModelProperty(value = "产品图片url地址",required = true,example = "http://localhost/image/earphone")
    @NotNull
    private String goodsImageKey;

    //最低售价
    @ApiModelProperty(value = "产品价格",example = "66.36")
    private BigDecimal price;

    //分类id
    @ApiModelProperty(value = "产品所属分类id",required = true,example = "10")
    @NotNull
    private Long categoryId;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public String getGoodsImageKey() {
        return goodsImageKey;
    }

    public void setGoodsImageKey(String goodsImageKey) {
        this.goodsImageKey = goodsImageKey;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }



}