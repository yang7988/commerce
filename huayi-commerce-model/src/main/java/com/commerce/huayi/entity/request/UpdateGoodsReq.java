package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel(value = "更新产品的请求json对象")
public class UpdateGoodsReq {

    @ApiModelProperty(value = "产品id",required = true,example = "1")
    @NotNull
    private Long id;

    @ApiModelProperty(value = "产品名称",example = "blue_earphone")

    private String goodsName;

    //商品描述
    @ApiModelProperty(value = "产品描述",example = "blue_earphone")
    private String goodsDescription;

    //商品描述
    @ApiModelProperty(value = "产品图片url地址",example = "http://localhost/image/earphone")
    private String goodsImageKey;

    //最低售价
    @ApiModelProperty(value = "产品价格",example = "66.36")
    private BigDecimal price;

    //分类id
    @ApiModelProperty(value = "产品所属分类id",example = "10")
    private Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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