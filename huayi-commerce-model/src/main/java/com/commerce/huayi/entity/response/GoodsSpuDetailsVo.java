package com.commerce.huayi.entity.response;

import com.commerce.huayi.annotation.Translate;

import java.math.BigDecimal;

public class GoodsSpuDetailsVo {
    private Long id;

    //商品编号，唯一
    private String spuNo;

    //商品名称
    @Translate(refTable = "tb_goods_spu",refColumn = "goods_name")
    private String goodsName;

    //商品描述
    @Translate(refTable = "tb_goods_spu",refColumn = "goods_description")
    private String goodsDescription;

    //商品的图片地址
    private String goodsImageKey;

    //最低售价
    private BigDecimal lowPrice;

    //分类id
    private Long categoryId;

    //品牌id
    private Long brandId;

    //商品的规格id
    private Long specId;

    //商品的规格编号
    private String specNo;

    //规格名称
    @Translate(refTable = "tb_goods_spec",refColumn = "spec_name")
    private String specName;

    //规格描述
    @Translate(refTable = "tb_goods_spec",refColumn = "spec_description")
    private String specDescription;

    //规格值
    @Translate(refTable = "tb_goods_spec_value",refColumn = "spec_value")
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
}