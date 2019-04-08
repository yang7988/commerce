package com.commerce.huayi.entity.db;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_goods_category")
public class GoodsCategory implements Serializable {
    /**
     * 主键id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 父级分类id
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 是否可以展开0不能展开1能展开
     */
    @Column(name = "is_open")
    private Byte isOpen;

    /**
     * 分类名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 分类描述
     */
    @Column(name = "category_description")
    private String categoryDescription;

    /**
     * 产品分类的图片地址
     */
    @Column(name = "category_image_key")
    private String categoryImageKey;

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
     * 获取父级分类id
     *
     * @return parent_id - 父级分类id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父级分类id
     *
     * @param parentId 父级分类id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取是否可以展开0不能展开1能展开
     *
     * @return is_open - 是否可以展开0不能展开1能展开
     */
    public Byte getIsOpen() {
        return isOpen;
    }

    /**
     * 设置是否可以展开0不能展开1能展开
     *
     * @param isOpen 是否可以展开0不能展开1能展开
     */
    public void setIsOpen(Byte isOpen) {
        this.isOpen = isOpen;
    }

    /**
     * 获取分类名称
     *
     * @return category_name - 分类名称
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 设置分类名称
     *
     * @param categoryName 分类名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 获取分类描述
     *
     * @return category_description - 分类描述
     */
    public String getCategoryDescription() {
        return categoryDescription;
    }

    /**
     * 设置分类描述
     *
     * @param categoryDescription 分类描述
     */
    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    /**
     * 获取产品分类的图片地址
     *
     * @return category_image_key - 产品分类的图片地址
     */
    public String getCategoryImageKey() {
        return categoryImageKey;
    }

    /**
     * 设置产品分类的图片地址
     *
     * @param categoryImageKey 产品分类的图片地址
     */
    public void setCategoryImageKey(String categoryImageKey) {
        this.categoryImageKey = categoryImageKey;
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
        sb.append(", parentId=").append(parentId);
        sb.append(", isOpen=").append(isOpen);
        sb.append(", categoryName=").append(categoryName);
        sb.append(", categoryDescription=").append(categoryDescription);
        sb.append(", categoryImageKey=").append(categoryImageKey);
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
        GoodsCategory other = (GoodsCategory) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getIsOpen() == null ? other.getIsOpen() == null : this.getIsOpen().equals(other.getIsOpen()))
            && (this.getCategoryName() == null ? other.getCategoryName() == null : this.getCategoryName().equals(other.getCategoryName()))
            && (this.getCategoryDescription() == null ? other.getCategoryDescription() == null : this.getCategoryDescription().equals(other.getCategoryDescription()))
            && (this.getCategoryImageKey() == null ? other.getCategoryImageKey() == null : this.getCategoryImageKey().equals(other.getCategoryImageKey()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getIsOpen() == null) ? 0 : getIsOpen().hashCode());
        result = prime * result + ((getCategoryName() == null) ? 0 : getCategoryName().hashCode());
        result = prime * result + ((getCategoryDescription() == null) ? 0 : getCategoryDescription().hashCode());
        result = prime * result + ((getCategoryImageKey() == null) ? 0 : getCategoryImageKey().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }
}