package com.commerce.huayi.entity.db;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_goods_brand")
public class GoodsBrand implements Serializable {
    /**
     * 主键id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 品牌名称
     */
    @Column(name = "brand_name")
    private String brandName;

    /**
     * 品牌描述
     */
    @Column(name = "brand_description")
    private String brandDescription;

    /**
     * 品牌商标的图片地址
     */
    @Column(name = "brand_logo_key")
    private String brandLogoKey;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 是否删除(0未删除1已删除)
     */
    @Column(name = "is_delete")
    private Byte isDelete;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取品牌名称
     *
     * @return brand_name - 品牌名称
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * 设置品牌名称
     *
     * @param brandName 品牌名称
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * 获取品牌描述
     *
     * @return brand_description - 品牌描述
     */
    public String getBrandDescription() {
        return brandDescription;
    }

    /**
     * 设置品牌描述
     *
     * @param brandDescription 品牌描述
     */
    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }

    /**
     * 获取品牌商标的图片地址
     *
     * @return brand_logo_key - 品牌商标的图片地址
     */
    public String getBrandLogoKey() {
        return brandLogoKey;
    }

    /**
     * 设置品牌商标的图片地址
     *
     * @param brandLogoKey 品牌商标的图片地址
     */
    public void setBrandLogoKey(String brandLogoKey) {
        this.brandLogoKey = brandLogoKey;
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

    /**
     * 获取更新时间
     *
     * @return update_date - 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取是否删除(0未删除1已删除)
     *
     * @return is_delete - 是否删除(0未删除1已删除)
     */
    public Byte getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除(0未删除1已删除)
     *
     * @param isDelete 是否删除(0未删除1已删除)
     */
    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", brandName=").append(brandName);
        sb.append(", brandDescription=").append(brandDescription);
        sb.append(", brandLogoKey=").append(brandLogoKey);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", isDelete=").append(isDelete);
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
        GoodsBrand other = (GoodsBrand) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBrandName() == null ? other.getBrandName() == null : this.getBrandName().equals(other.getBrandName()))
            && (this.getBrandDescription() == null ? other.getBrandDescription() == null : this.getBrandDescription().equals(other.getBrandDescription()))
            && (this.getBrandLogoKey() == null ? other.getBrandLogoKey() == null : this.getBrandLogoKey().equals(other.getBrandLogoKey()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBrandName() == null) ? 0 : getBrandName().hashCode());
        result = prime * result + ((getBrandDescription() == null) ? 0 : getBrandDescription().hashCode());
        result = prime * result + ((getBrandLogoKey() == null) ? 0 : getBrandLogoKey().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }
}