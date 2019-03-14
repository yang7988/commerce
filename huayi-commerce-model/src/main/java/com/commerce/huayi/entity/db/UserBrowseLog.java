package com.commerce.huayi.entity.db;

import java.io.Serializable;
import java.util.Date;

public class UserBrowseLog implements Serializable {
    //主键ID
    private Integer id;

    //用户所在地址
    private String address;

    //浏览模块
    private String module;

    //创建时间
    private Date createDate;

    static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}