package com.commerce.huayi.entity.db;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_customer_message")
public class CustomerMessage implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 姓名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 所属公司
     */
    @Column(name = "company")
    private String company;

    /**
     * 电话
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;

    /**
     * 邮箱地址
     */
    @Column(name = "mail_address")
    private String mailAddress;

    /**
     * 传真
     */
    @Column(name = "fax")
    private String fax;

    /**
     * 邮政编码
     */
    @Column(name = "postal_code")
    private String postalCode;

    /**
     * 地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 意见
     */
    @Column(name = "opinion")
    private String opinion;

    /**
     * 访问目的
     */
    @Column(name = "access_purpose")
    private String accessPurpose;

    /**
     * 目的是否达到（是，否，一部分，仅浏览）
     */
    @Column(name = "purpose_flag")
    private String purposeFlag;

    /**
     * 是否提交服务解决问题（是，否）
     */
    @Column(name = "deal_flag")
    private String dealFlag;

    /**
     * 文档质量（非常好，好，平均，差）
     */
    @Column(name = "document_quality")
    private String documentQuality;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取所属公司
     *
     * @return company - 所属公司
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置所属公司
     *
     * @param company 所属公司
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 获取电话
     *
     * @return mobile_phone - 电话
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置电话
     *
     * @param mobilePhone 电话
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * 获取邮箱地址
     *
     * @return mail_address - 邮箱地址
     */
    public String getMailAddress() {
        return mailAddress;
    }

    /**
     * 设置邮箱地址
     *
     * @param mailAddress 邮箱地址
     */
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    /**
     * 获取传真
     *
     * @return fax - 传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * 设置传真
     *
     * @param fax 传真
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 获取邮政编码
     *
     * @return postal_code - 邮政编码
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * 设置邮政编码
     *
     * @param postalCode 邮政编码
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取意见
     *
     * @return opinion - 意见
     */
    public String getOpinion() {
        return opinion;
    }

    /**
     * 设置意见
     *
     * @param opinion 意见
     */
    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    /**
     * 获取访问目的
     *
     * @return access_purpose - 访问目的
     */
    public String getAccessPurpose() {
        return accessPurpose;
    }

    /**
     * 设置访问目的
     *
     * @param accessPurpose 访问目的
     */
    public void setAccessPurpose(String accessPurpose) {
        this.accessPurpose = accessPurpose;
    }

    /**
     * 获取目的是否达到（是，否，一部分，仅浏览）
     *
     * @return purpose_flag - 目的是否达到（是，否，一部分，仅浏览）
     */
    public String getPurposeFlag() {
        return purposeFlag;
    }

    /**
     * 设置目的是否达到（是，否，一部分，仅浏览）
     *
     * @param purposeFlag 目的是否达到（是，否，一部分，仅浏览）
     */
    public void setPurposeFlag(String purposeFlag) {
        this.purposeFlag = purposeFlag;
    }

    /**
     * 获取是否提交服务解决问题（是，否）
     *
     * @return deal_flag - 是否提交服务解决问题（是，否）
     */
    public String getDealFlag() {
        return dealFlag;
    }

    /**
     * 设置是否提交服务解决问题（是，否）
     *
     * @param dealFlag 是否提交服务解决问题（是，否）
     */
    public void setDealFlag(String dealFlag) {
        this.dealFlag = dealFlag;
    }

    /**
     * 获取文档质量（非常好，好，平均，差）
     *
     * @return document_quality - 文档质量（非常好，好，平均，差）
     */
    public String getDocumentQuality() {
        return documentQuality;
    }

    /**
     * 设置文档质量（非常好，好，平均，差）
     *
     * @param documentQuality 文档质量（非常好，好，平均，差）
     */
    public void setDocumentQuality(String documentQuality) {
        this.documentQuality = documentQuality;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", company=").append(company);
        sb.append(", mobilePhone=").append(mobilePhone);
        sb.append(", mailAddress=").append(mailAddress);
        sb.append(", fax=").append(fax);
        sb.append(", postalCode=").append(postalCode);
        sb.append(", address=").append(address);
        sb.append(", opinion=").append(opinion);
        sb.append(", accessPurpose=").append(accessPurpose);
        sb.append(", purposeFlag=").append(purposeFlag);
        sb.append(", dealFlag=").append(dealFlag);
        sb.append(", documentQuality=").append(documentQuality);
        sb.append(", createDate=").append(createDate);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CustomerMessage other = (CustomerMessage) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getCompany() == null ? other.getCompany() == null : this.getCompany().equals(other.getCompany()))
                && (this.getMobilePhone() == null ? other.getMobilePhone() == null : this.getMobilePhone().equals(other.getMobilePhone()))
                && (this.getMailAddress() == null ? other.getMailAddress() == null : this.getMailAddress().equals(other.getMailAddress()))
                && (this.getFax() == null ? other.getFax() == null : this.getFax().equals(other.getFax()))
                && (this.getPostalCode() == null ? other.getPostalCode() == null : this.getPostalCode().equals(other.getPostalCode()))
                && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
                && (this.getOpinion() == null ? other.getOpinion() == null : this.getOpinion().equals(other.getOpinion()))
                && (this.getAccessPurpose() == null ? other.getAccessPurpose() == null : this.getAccessPurpose().equals(other.getAccessPurpose()))
                && (this.getPurposeFlag() == null ? other.getPurposeFlag() == null : this.getPurposeFlag().equals(other.getPurposeFlag()))
                && (this.getDealFlag() == null ? other.getDealFlag() == null : this.getDealFlag().equals(other.getDealFlag()))
                && (this.getDocumentQuality() == null ? other.getDocumentQuality() == null : this.getDocumentQuality().equals(other.getDocumentQuality()))
                && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCompany() == null) ? 0 : getCompany().hashCode());
        result = prime * result + ((getMobilePhone() == null) ? 0 : getMobilePhone().hashCode());
        result = prime * result + ((getMailAddress() == null) ? 0 : getMailAddress().hashCode());
        result = prime * result + ((getFax() == null) ? 0 : getFax().hashCode());
        result = prime * result + ((getPostalCode() == null) ? 0 : getPostalCode().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getOpinion() == null) ? 0 : getOpinion().hashCode());
        result = prime * result + ((getAccessPurpose() == null) ? 0 : getAccessPurpose().hashCode());
        result = prime * result + ((getPurposeFlag() == null) ? 0 : getPurposeFlag().hashCode());
        result = prime * result + ((getDealFlag() == null) ? 0 : getDealFlag().hashCode());
        result = prime * result + ((getDocumentQuality() == null) ? 0 : getDocumentQuality().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        return result;
    }
}