package com.commerce.huayi.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(value = "产品详情的response body")
public class GoodsSpuDetailsVo {

    @ApiModelProperty(value = "产品id", required = true, example = "1")
    private Long id;

    //商品编号，唯一
    @ApiModelProperty(value = "产品编号", required = true, example = "adasdkjas-132156s-aaa")
    private String spuNo;

    //商品名称
    @ApiModelProperty(value = "产品名称", required = true, example = "big_earphone")
    private String goodsName;

    //商品描述
    @ApiModelProperty(value = "产品描述", required = true, example = "big_earphone")
    private String goodsDescription;

    //商品的图片地址
    @ApiModelProperty(value = "产品图片key值", required = true, example = "FsdVQeZzIkAgoCpbvq81gGSeF3Y7")
    private String goodsImageKey;

    //最低售价
    @ApiModelProperty(value = "产品价格", required = true, example = "663.69")
    private BigDecimal lowPrice;

    //分类id
    @ApiModelProperty(value = "产品所属分类id", required = true, example = "10")
    private Long categoryId;

    //品牌id
    @ApiModelProperty(value = "产品所属品牌id", required = true, example = "0")
    private Long brandId;

    //商品的规格id
    @ApiModelProperty(value = "产品的规格id", required = true, example = "1")
    private Long specId;

    //商品的规格编号
    @ApiModelProperty(value = "产品的规格编号", required = true, example = "Abc-1234567")
    private String specNo;

    //规格名称
    @ApiModelProperty(value = "产品的规格名称", required = true, example = "color")
    private String specName;

    //规格描述
    @ApiModelProperty(value = "产品的规格描述", required = true, example = "color")
    private String specDescription;

    //商品的规格值id
    @ApiModelProperty(value = "产品的规格值id", required = true, example = "1")
    private Long specValueId;

    //规格值
    @ApiModelProperty(value = "产品的规格具体值", required = true, example = "red")
    private String specValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpuNo() {
        return spuNo;
    }

    public void setSpuNo(String spuNo) {
        this.spuNo = spuNo;
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

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public String getSpecNo() {
        return specNo;
    }

    public void setSpecNo(String specNo) {
        this.specNo = specNo;
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
}