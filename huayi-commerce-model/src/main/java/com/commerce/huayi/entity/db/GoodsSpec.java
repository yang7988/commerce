package com.commerce.huayi.entity.db;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_goods_spec")
public class GoodsSpec implements Serializable {
    /**
     * 主键id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 规格编号
     */
    @Column(name = "spec_no")
    private String specNo;

    /**
     * 规格名称
     */
    @Column(name = "spec_name")
    private String specName;

    /**
     * 规格描述
     */
    @Column(name = "spec_description")
    private String specDescription;

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
     * 获取规格编号
     *
     * @return spec_no - 规格编号
     */
    public String getSpecNo() {
        return specNo;
    }

    /**
     * 设置规格编号
     *
     * @param specNo 规格编号
     */
    public void setSpecNo(String specNo) {
        this.specNo = specNo;
    }

    /**
     * 获取规格名称
     *
     * @return spec_name - 规格名称
     */
    public String getSpecName() {
        return specName;
    }

    /**
     * 设置规格名称
     *
     * @param specName 规格名称
     */
    public void setSpecName(String specName) {
        this.specName = specName;
    }

    /**
     * 获取规格描述
     *
     * @return spec_description - 规格描述
     */
    public String getSpecDescription() {
        return specDescription;
    }

    /**
     * 设置规格描述
     *
     * @param specDescription 规格描述
     */
    public void setSpecDescription(String specDescription) {
        this.specDescription = specDescription;
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
        sb.append(", specNo=").append(specNo);
        sb.append(", specName=").append(specName);
        sb.append(", specDescription=").append(specDescription);
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
        GoodsSpec other = (GoodsSpec) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSpecNo() == null ? other.getSpecNo() == null : this.getSpecNo().equals(other.getSpecNo()))
            && (this.getSpecName() == null ? other.getSpecName() == null : this.getSpecName().equals(other.getSpecName()))
            && (this.getSpecDescription() == null ? other.getSpecDescription() == null : this.getSpecDescription().equals(other.getSpecDescription()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSpecNo() == null) ? 0 : getSpecNo().hashCode());
        result = prime * result + ((getSpecName() == null) ? 0 : getSpecName().hashCode());
        result = prime * result + ((getSpecDescription() == null) ? 0 : getSpecDescription().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }
}