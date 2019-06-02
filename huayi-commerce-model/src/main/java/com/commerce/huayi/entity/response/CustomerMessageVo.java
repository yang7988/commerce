package com.commerce.huayi.entity.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 客户留言
 *
 * @date 2019-03-16
 */
@ApiModel(value = "客户留言信息明细response body")
public class CustomerMessageVo {

    @ApiModelProperty(value = "主键id", required = true)
    private String id;

    @ApiModelProperty(value = "姓名name", required = true)
    private String name;

    @ApiModelProperty(value = "公司company", required = true)
    private String company;

    @ApiModelProperty(value = "手机号mobilePhone", required = true)
    private String mobilePhone;

    @ApiModelProperty(value = "邮箱地址mailAddress", required = true)
    private String mailAddress;

    @ApiModelProperty(value = "传真fax", required = true)
    private String fax;

    @ApiModelProperty(value = "邮编postalCode", required = true)
    private String postalCode;

    @ApiModelProperty(value = "地址address", required = true)
    private String address;

    @ApiModelProperty(value = "意见opinion", required = true)
    private String opinion;

    @ApiModelProperty(value = "访问目的accessPurpose", required = true)
    private String accessPurpose;

    @ApiModelProperty(value = "目的是否达到（否 0 ，是 1，一部分 2，仅浏览 3）purposeFlag", required = true)
    private String purposeFlag;

    @ApiModelProperty(value = "是否提交服务解决问题 (是 1  否 0)dealFlag", required = true)
    private String dealFlag;

    @ApiModelProperty(value = "文档质量（非常好 3，好 2，平均 1，差 0）documentQuality", required = true)
    private String documentQuality;

    @ApiModelProperty(value = "创建时间createDate", required = true)
    private String createDate;

    @ApiModelProperty(value = "产品明细GoodsSpus", required = true)
    private List<GoodsSpuVo> GoodsSpus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<GoodsSpuVo> getGoodsSpus() {
        return GoodsSpus;
    }

    public void setGoodsSpus(List<GoodsSpuVo> goodsSpus) {
        GoodsSpus = goodsSpus;
    }

}
