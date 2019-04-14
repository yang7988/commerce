package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "官网banner信息请求json对象")
public class BannerReq {

    @ApiModelProperty(value = "主键id",example = "1",dataType = "int")
    private int id;

    @ApiModelProperty(value = "基础url",example = "http://ppoltuvha.bkt.clouddn.com",dataType = "String")
    private String url;

    @ApiModelProperty(value = "影像对应imageKey",example = "FHGJTUDJBNEPORGORWG0987098JKLHB",dataType = "String")
    private String imageKey;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }

}
