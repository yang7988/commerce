package com.commerce.huayi.entity.response;

import com.commerce.huayi.annotation.Translate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(value = "产品的response body")
public class GoodsSpuVo {

    @ApiModelProperty(value = "产品id",required = true,example = "1")
    private Long id;

    //商品编号，唯一
    @ApiModelProperty(value = "产品编号",required = true,example = "adasdkjas-132156s-aaa")
    private String spuNo;

    //商品名称
    @Translate(refTable = "tb_goods_spu",refColumn = "goods_name")
    @ApiModelProperty(value = "产品名称",required = true,example = "big_earphone")
    private String goodsName;

    //商品描述
    @Translate(refTable = "tb_goods_spu",refColumn = "goods_description")
    @ApiModelProperty(value = "产品描述",required = true,example = "big_earphone")
    private String goodsDescription;

    //商品的图片地址
    @ApiModelProperty(value = "产品图片key值",required = true,example = "FsdVQeZzIkAgoCpbvq81gGSeF3Y7")
    private String goodsImageKey;

    //最低售价
    @ApiModelProperty(value = "产品价格",required = true,example = "663.69")
    private BigDecimal lowPrice;

    //分类id
    @ApiModelProperty(value = "产品所属分类id",required = true,example = "10")
    private Long categoryId;

    //品牌id
    @ApiModelProperty(value = "产品所属品牌id",required = true,example = "0")
    private Long brandId;

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
}