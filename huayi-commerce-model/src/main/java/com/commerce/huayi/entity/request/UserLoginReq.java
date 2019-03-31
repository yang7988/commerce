package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
/**
 * 用户登录
 * @date 2019-03-16
 * */
@ApiModel(value = "管理员登录请求json对象")
public class UserLoginReq {

    @ApiModelProperty(value = "管理员登录用户名userName",example = "huaYi123",dataType = "String")
    @NotNull
    private String userName;

    @ApiModelProperty(value = "管理员登录密码password",example = "2E55D340CF2FA25B9EC418CD20F4752C",dataType = "String")
    @NotNull
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}