package com.commerce.huayi.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "图片上传返回的json对象")
public class ImageUploadVo {

    //分类名称
    @ApiModelProperty(value = "图片上传后的url",example = "http://pgd3zoxnk.bkt.clouddn.com/FgaeuBs1QwDZUd9UO4betMtgZOMs")
    private String imageKey;

    //分类名称
    @ApiModelProperty(value = "访问图片的host域名",example = "http://pgd3zoxnk.bkt.clouddn.com")
    private String imageHost = "http://pgd3zoxnk.bkt.clouddn.com";

    @ApiModelProperty(value = "图片上传的时间戳")
    private Long imageUploadTime;

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }

    public Long getImageUploadTime() {
        return imageUploadTime;
    }

    public void setImageUploadTime(Long imageUploadTime) {
        this.imageUploadTime = imageUploadTime;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }
}