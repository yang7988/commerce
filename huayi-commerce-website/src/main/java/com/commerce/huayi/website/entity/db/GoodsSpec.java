package com.commerce.huayi.website.entity.db;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品规格
 */
public class GoodsSpec implements Serializable {
    //主键id
    private Long id;

    //规格编号
    private String specNo;

    //规格名称
    private String specName;

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