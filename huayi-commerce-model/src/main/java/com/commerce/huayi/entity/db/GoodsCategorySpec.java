package com.commerce.huayi.entity.db;

import java.io.Serializable;
import java.util.Date;

public class GoodsCategorySpec implements Serializable {
    //主键id
    private Long id;

    //商品分类id
    private Long categoryId;

    //规格id
    private Long specId;

    //规格值id
    private Long specValueId;

    //是否删除0未删除1已删除
    private Byte isDelete;

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public Long getSpecValueId() {
        return specValueId;
    }

    public void setSpecValueId(Long specValueId) {
        this.specValueId = specValueId;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
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