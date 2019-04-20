package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@ApiModel(value = "产品添加的请求json对象")
public class AddGoodsReq {

    //商品名称
    @ApiModelProperty(value = "产品名称", required = true, example = "blue_earphone")
    @NotNull
    private String goodsName;

    //商品描述
    @ApiModelProperty(value = "产品描述", required = true, example = "blue_earphone")
    @NotNull
    private String goodsDescription;

    //商品描述
    @ApiModelProperty(value = "产品图片url地址", required = true, example = "http://localhost/image/earphone")
    @NotNull
    private String goodsImageKey;

    //最低售价
    @ApiModelProperty(value = "产品价格", example = "66.36")
    private BigDecimal price;

    //分类id
    @ApiModelProperty(value = "产品所属分类id", required = true, example = "10")
    @NotNull
    private Long categoryId;

    @ApiModelProperty(value = "产品规格请求列表", required = false, example = "[\n" +
            "        {\n" +
            "            \"specId\":1,\n" +
            "            \"specValueId\":2,\n" +
            "            \"goodsSpecImageKey\":\"123454asdfasda\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"specId\":3,\n" +
            "            \"specValueId\":4,\n" +
            "            \"goodsSpecImageKey\":\"jklsdfasjfdas\"\n" +
            "        }\n" +
            "    ]")
    private List<AddGoodsSpecReq> specRequest;

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

    public List<AddGoodsSpecReq> getSpecRequest() {
        return specRequest;
    }

    public void setSpecRequest(List<AddGoodsSpecReq> specRequest) {
        this.specRequest = specRequest;
    }
}