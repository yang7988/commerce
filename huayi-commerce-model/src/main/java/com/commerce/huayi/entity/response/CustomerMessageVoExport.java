package com.commerce.huayi.entity.response;

import com.commerce.huayi.utils.ExcelField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 客户留言
 *
 * @date 2019-03-16
 */
@ApiModel(value = "客户留言信息明细导出")
public class CustomerMessageVoExport {

    private String id;

    private String name;

    private String company;

    private String mobilePhone;

    private String mailAddress;

    private String fax;

    private String postalCode;

    private String address;

    private String opinion;

    private String accessPurpose;

    private String purposeFlag;

    private String dealFlag;

    private String documentQuality;

    private String createDate;

    private String GoodsSpus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ExcelField(title = "姓名",align = 2, sort = 1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ExcelField(title = "公司",align = 2, sort = 2)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @ExcelField(title = "手机号",align = 2, sort = 3)
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @ExcelField(title = "邮箱地址",align = 2, sort = 4)
    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    @ExcelField(title = "传真",align = 2, sort = 5)
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @ExcelField(title = "邮编",align = 2, sort = 6)
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @ExcelField(title = "地址",align = 2, sort = 7)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ExcelField(title = "意见",align = 2, sort = 8)
    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    @ExcelField(title = "访问目的",align = 2, sort = 9)
    public String getAccessPurpose() {
        return accessPurpose;
    }

    public void setAccessPurpose(String accessPurpose) {
        this.accessPurpose = accessPurpose;
    }

    @ExcelField(title = "目的是否达到",align = 2, sort = 10)
    public String getPurposeFlag() {
        return purposeFlag;
    }

    public void setPurposeFlag(String purposeFlag) {
        this.purposeFlag = purposeFlag;
    }

    @ExcelField(title = "是否提交服务解决问题",align = 2, sort = 11)
    public String getDealFlag() {
        return dealFlag;
    }

    public void setDealFlag(String dealFlag) {
        this.dealFlag = dealFlag;
    }

    @ExcelField(title = "文档质量",align = 2, sort = 12)
    public String getDocumentQuality() {
        return documentQuality;
    }

    public void setDocumentQuality(String documentQuality) {
        this.documentQuality = documentQuality;
    }

    @ExcelField(title = "创建时间",align = 2, sort = 13)
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @ExcelField(title = "产品明细",align = 2, sort = 14)
    public String getGoodsSpus() {
        return GoodsSpus;
    }

    public void setGoodsSpus(String goodsSpus) {
        GoodsSpus = goodsSpus;
    }

}
