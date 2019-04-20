package com.commerce.huayi.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "status")
public class ApiStatusCodeVo {
    @ApiModelProperty(value = "系统自定义状态码", required = true)
    private int status;

    @ApiModelProperty(value = "系统自定义状态码英文描述", required = true)
    private String code;

    @ApiModelProperty(value = "系统自定义状态码描述", required = true)
    private String desc;

    public ApiStatusCodeVo() {
    }

    public ApiStatusCodeVo(int status, String code, String desc) {
        this.status = status;
        this.code = code;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}