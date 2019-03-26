package com.commerce.huayi.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 新闻中心
 * @date 2019-03-16
 * */
@ApiModel(value = "新闻中心信息response body")
public class NewsInfoVo {

    @ApiModelProperty(value = "主键id",required = true)
    private int id;

    @ApiModelProperty(value = "标题title",required = true)
    private String title;

    @ApiModelProperty(value = "内容明细content",required = true)
    private String content;

    @ApiModelProperty(value = "创建时间createDate",required = true)
    private Date createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
