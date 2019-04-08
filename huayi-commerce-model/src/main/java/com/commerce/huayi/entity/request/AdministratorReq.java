package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "管理员信息请求json对象")
public class AdministratorReq implements Serializable {

    //登录名
    @ApiModelProperty(value = "登录名loginName",example = "huaYi123",dataType = "String")
    private String loginName;

    //用户名
    @ApiModelProperty(value = "用户名name",example = "张三",dataType = "String")
    private String name;

    //用户手机号
    @ApiModelProperty(value = "用户手机号mobilePhone",example = "15845784578",dataType = "String")
    private String mobilePhone;

    //登录密码
    @ApiModelProperty(value = "登录密码password",example = "8E32C1D89CAA28A23F43E16001B44EB7",dataType = "String")
    private String password;

    static final long serialVersionUID = 1L;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}