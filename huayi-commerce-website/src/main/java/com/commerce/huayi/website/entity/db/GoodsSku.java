package com.commerce.huayi.website.entity.db;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品库存单元SKU
 */
public class GoodsSku implements Serializable {
    //主键id
    private Long id;

    //sku编号,唯一
    private String skuNo;

    //sku名称(冗余spu_name)
    private String skuName;

    //售价
    private BigDecimal price;

    //库存
    private Integer stock;

    //spu_id
    private Long spuId;

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

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
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