package com.commerce.huayi.website.entity.db;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品规规格属性值
 */
public class GoodsSpecValue implements Serializable {
    //逐渐id
    private Long id;

    //规格id
    private Long specId;

    //规格值
    private String specValue;

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

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public String getSpecValue() {
        return specValue;
    }

    public void setSpecValue(String specValue) {
        this.specValue = specValue;
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