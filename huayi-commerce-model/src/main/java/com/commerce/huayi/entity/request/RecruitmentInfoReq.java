package com.commerce.huayi.entity.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 招聘信息
 *
 * @date 2019-03-16
 */
@ApiModel(value = "招聘信息请求json对象")
public class RecruitmentInfoReq {

    @ApiModelProperty(value = "主键id", example = "1", dataType = "int")
    private int id;

    @ApiModelProperty(value = "招聘信息标题title", example = "HuaYi公司招贤纳士", dataType = "String")
    private String title;

    @ApiModelProperty(value = "招聘信息内容content", example = "HuaYi公司招贤纳士", dataType = "String")
    private String content;


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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
