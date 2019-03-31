package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 新闻中心
 * @date 2019-03-16
 * */
@ApiModel(value = "新闻中心信息请求json对象")
public class NewsInfoReq {

    @ApiModelProperty(value = "主键id",example = "1",dataType = "int")
    private int id;

    @ApiModelProperty(value = "新闻标题title",example = "HuaYi公司耳机大卖",dataType = "String")
    private String title;

    @ApiModelProperty(value = "新闻内容content",example = "HuaYi公司耳机大卖",dataType = "String")
    private String content;

    @ApiModelProperty(value = "生效时间effectDate",example = "2019-03-22 22:00:00",dataType = "Date")
    private Date effectDate;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
