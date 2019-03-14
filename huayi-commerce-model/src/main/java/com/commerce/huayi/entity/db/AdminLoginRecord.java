package com.commerce.huayi.entity.db;

import java.io.Serializable;
import java.util.Date;

public class AdminLoginRecord implements Serializable {
    //主键
    private Long id;

    //登陆用户ID
    private String loginUser;

    //登陆唯一标识
    private String loginKey;

    //登陆地址
    private String loginAdd;

    //登入时间
    private Date loginInDate;

    //登出时间
    private Date loginOutDate;

    static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getLoginKey() {
        return loginKey;
    }

    public void setLoginKey(String loginKey) {
        this.loginKey = loginKey;
    }

    public String getLoginAdd() {
        return loginAdd;
    }

    public void setLoginAdd(String loginAdd) {
        this.loginAdd = loginAdd;
    }

    public Date getLoginInDate() {
        return loginInDate;
    }

    public void setLoginInDate(Date loginInDate) {
        this.loginInDate = loginInDate;
    }

    public Date getLoginOutDate() {
        return loginOutDate;
    }

    public void setLoginOutDate(Date loginOutDate) {
        this.loginOutDate = loginOutDate;
    }
}