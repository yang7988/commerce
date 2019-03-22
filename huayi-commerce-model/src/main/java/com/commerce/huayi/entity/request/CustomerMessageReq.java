package com.commerce.huayi.entity.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 客户留言
 * @date 2019-03-16
 * */
@ApiModel(value = "客户留言信息请求json对象")
public class CustomerMessageReq {

    @ApiModelProperty(value = "姓名name",example = "张三",dataType = "String")
    private String name;

    @ApiModelProperty(value = "公司company",example = "HuaYi公司",dataType = "String")
    private String company;

    @ApiModelProperty(value = "手机号mobilePhone",example = "18987655432",dataType = "String")
    private String mobilePhone;

    @ApiModelProperty(value = "邮箱地址mailAddress",example = "nidebidb@163.com",dataType = "String")
    private String mailAddress;

    @ApiModelProperty(value = "传真fax",example = "456789099",dataType = "String")
    private String fax;

    @ApiModelProperty(value = "邮编postalCode",example = "580000",dataType = "String")
    private String postalCode;

    @ApiModelProperty(value = "地址address",example = "广东省深圳市",dataType = "String")
    private String address;

    @ApiModelProperty(value = "意见opinion",example = "十分完美，没有意见",dataType = "String")
    private String opinion;

    @ApiModelProperty(value = "访问目的accessPurpose",example = "HuaYi公司",dataType = "String")
    private String accessPurpose;

    @ApiModelProperty(value = "目的是否达到（否 0 ，是 1，一部分 2，仅浏览 3）purposeFlag",example = "2",dataType = "String")
    private String purposeFlag;

    @ApiModelProperty(value = "是否提交服务解决问题 (是 1  否 0)dealFlag",example = "1",dataType = "String")
    private String dealFlag;

    @ApiModelProperty(value = "文档质量（非常好 3，好 2，平均 1，差 0）documentQuality",example = "3",dataType = "String")
    private String documentQuality;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getAccessPurpose() {
        return accessPurpose;
    }

    public void setAccessPurpose(String accessPurpose) {
        this.accessPurpose = accessPurpose;
    }

    public String getPurposeFlag() {
        return purposeFlag;
    }

    public void setPurposeFlag(String purposeFlag) {
        this.purposeFlag = purposeFlag;
    }

    public String getDealFlag() {
        return dealFlag;
    }

    public void setDealFlag(String dealFlag) {
        this.dealFlag = dealFlag;
    }

    public String getDocumentQuality() {
        return documentQuality;
    }

    public void setDocumentQuality(String documentQuality) {
        this.documentQuality = documentQuality;
    }


}
