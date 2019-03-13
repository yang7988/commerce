package com.commerce.huayi.website.entity.db;

import java.io.Serializable;
import java.util.Date;

public class GoodsSkuSpecValue implements Serializable {
    //主键id
    private Long id;

    //sku_id
    private Long spuId;

    //规格值id
    private Long specValueId;

    //创建时间
    private Date createDate;

    //更新时间
    private Date updateDate;

    //是否删除(0未删除1已删除)
    private Byte isDelete;

    static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public Long getSpecValueId() {
        return specValueId;
    }

    public void setSpecValueId(Long specValueId) {
        this.specValueId = specValueId;
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

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}