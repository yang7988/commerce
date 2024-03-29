package com.commerce.huayi.entity.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 招聘信息
 *
 * @date 2019-03-16
 */
@ApiModel(value = "人才中心信息response body")
public class RecruitmentInfoVo {

    @ApiModelProperty(value = "主键id", required = true)
    private int id;

    @ApiModelProperty(value = "标题title", required = true)
    private String title;

    @ApiModelProperty(value = "明细内容content", required = true)
    private String content;

    @ApiModelProperty(value = "创建时间createDate", required = true)
    private String createDate;

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

}
