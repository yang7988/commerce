package com.commerce.huayi.website.entity.db;

import java.io.Serializable;
import java.util.Date;

public class GoodsCategory implements Serializable {
    //主键id
    private Long id;

    //父级分类id
    private Long parentId;

    //分类名称
    private String categoryName;

    //分类描述
    private String categoryDescription;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
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