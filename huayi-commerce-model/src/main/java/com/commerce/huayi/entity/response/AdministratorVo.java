package com.commerce.huayi.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "管理员信息response body")
public class AdministratorVo implements Serializable {
    //主键
    @ApiModelProperty(value = "主键id", required = true)
    private Integer id;

    //登录名
    @ApiModelProperty(value = "登录名loginName", required = true)
    private String loginName;

    //用户名
    @ApiModelProperty(value = "用户名name", required = true)
    private String name;

    //用户手机号
    @ApiModelProperty(value = "用户手机号mobilePhone", required = true)
    private String mobilePhone;

    //用户状态(0:锁定 1:正常)
    @ApiModelProperty(value = "用户状态(0:锁定 1:正常)status", required = true)
    private String status;


    // 管理员权限(0:超级管理员 1:高级管理员 2：普通业务员）
    @ApiModelProperty(value = "管理员权限(0:超级管理员 1:高级管理员 2：普通业务员）adminRole", required = true)
    private String adminRole;

    //创建人
    @ApiModelProperty(value = "创建人createBy", required = true)
    private String createBy;

    //创建时间
    @ApiModelProperty(value = "创建时间createDate", required = true)
    private String createDate;

    //更新时间
    @ApiModelProperty(value = "更新时间updateDate", required = false)
    private String updateDate;

    //删除标识(0未删除1已删除)
    @ApiModelProperty(value = "删除标识(0未删除1已删除)isDelete", required = true)
    private Byte isDelete;

    //更新人
    @ApiModelProperty(value = "更新人updateBy", required = false)
    private String updateBy;

    // 登录成功对应token
    @ApiModelProperty(value = "登录成功对应token", required = false)
    private String token;

    static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }

}