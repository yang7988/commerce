package com.commerce.huayi.entity.db;

import java.util.Date;

/**
 * 客户留言
 * @date 2019-03-16
 * */
public class CustomerMessage {

    private int id;

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

    private Date createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
