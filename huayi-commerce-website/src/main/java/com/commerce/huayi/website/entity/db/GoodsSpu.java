package com.commerce.huayi.website.entity.db;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品单元
 */
public class GoodsSpu implements Serializable {
    //主键id
    private Long id;

    //商品编号，唯一
    private String spuNo;

    //商品名称
    private String goodsName;

    //最低售价
    private BigDecimal lowPrice;

    //分类id
    private Long categoryId;

    //品牌id
    private Long brandId;

    //创建时间
    private Date createDate;

    //更新时间
    private Date updateDate;

    static final long serialVersionUID = 1L;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}