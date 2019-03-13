package com.commerce.huayi.website.entity.db;

import java.io.Serializable;
import java.util.Date;

public class GoodsBrand implements Serializable {
    //主键id
    private Long id;

    //品牌名称
    private String brandName;

    //品牌描述
    private String brandDescription;

    //品牌商标的图片地址
    private String brandLogoKey;

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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandDescription() {
        return brandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }

    public String getBrandLogoKey() {
        return brandLogoKey;
    }

    public void setBrandLogoKey(String brandLogoKey) {
        this.brandLogoKey = brandLogoKey;
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