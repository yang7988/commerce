package com.commerce.huayi.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "官网banner信息response body")
public class BannerVo {

    @ApiModelProperty(value = "主键id", required = true)
    private int id;

    @ApiModelProperty(value = "访问基础地址url", required = true)
    private String url;

    @ApiModelProperty(value = "影像对应imageKey", required = true)
    private String imageKey;

    @ApiModelProperty(value = "创建时间createDate", required = true)
    private String createDate;

    @ApiModelProperty(value = "更新时间updateDate", required = true)
    private String updateDate;

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
}
