package com.commerce.huayi.entity.db;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_goods_category_spec")
public class GoodsCategorySpec implements Serializable {
    /**
     * 主键id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 商品分类id
     */
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 规格id
     */
    @Column(name = "spec_id")
    private Long specId;

    /**
     * 规格值id
     */
    @Column(name = "spec_value_id")
    private Long specValueId;

    /**
     * 是否删除0未删除1已删除
     */
    @Column(name = "is_delete")
    private Byte isDelete;

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
     * 获取商品分类id
     *
     * @return category_id - 商品分类id
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * 设置商品分类id
     *
     * @param categoryId 商品分类id
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取规格id
     *
     * @return spec_id - 规格id
     */
    public Long getSpecId() {
        return specId;
    }

    /**
     * 设置规格id
     *
     * @param specId 规格id
     */
    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    /**
     * 获取规格值id
     *
     * @return spec_value_id - 规格值id
     */
    public Long getSpecValueId() {
        return specValueId;
    }

    /**
     * 设置规格值id
     *
     * @param specValueId 规格值id
     */
    public void setSpecValueId(Long specValueId) {
        this.specValueId = specValueId;
    }

    /**
     * 获取是否删除0未删除1已删除
     *
     * @return is_delete - 是否删除0未删除1已删除
     */
    public Byte getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除0未删除1已删除
     *
     * @param isDelete 是否删除0未删除1已删除
     */
    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", specId=").append(specId);
        sb.append(", specValueId=").append(specValueId);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
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
        GoodsCategorySpec other = (GoodsCategorySpec) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getSpecId() == null ? other.getSpecId() == null : this.getSpecId().equals(other.getSpecId()))
            && (this.getSpecValueId() == null ? other.getSpecValueId() == null : this.getSpecValueId().equals(other.getSpecValueId()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getSpecId() == null) ? 0 : getSpecId().hashCode());
        result = prime * result + ((getSpecValueId() == null) ? 0 : getSpecValueId().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        return result;
    }
}