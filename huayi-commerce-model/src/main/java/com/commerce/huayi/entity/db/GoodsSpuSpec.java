package com.commerce.huayi.entity.db;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_goods_spu_spec")
public class GoodsSpuSpec implements Serializable {
    /**
     * 主键id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * spu_id
     */
    @Column(name = "spu_id")
    private Long spuId;

    /**
     * spec_id
     */
    @Column(name = "spec_value_id")
    private Long specValueId;

    /**
     * 产品规格图
     */
    @Column(name = "spec_image_key")
    private String specImageKey;

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
     * 获取spu_id
     *
     * @return spu_id - spu_id
     */
    public Long getSpuId() {
        return spuId;
    }

    /**
     * 设置spu_id
     *
     * @param spuId spu_id
     */
    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    /**
     * 获取spec_id
     *
     * @return spec_value_id - spec_id
     */
    public Long getSpecValueId() {
        return specValueId;
    }

    /**
     * 设置spec_id
     *
     * @param specValueId spec_id
     */
    public void setSpecValueId(Long specValueId) {
        this.specValueId = specValueId;
    }

    /**
     * 获取产品规格图
     *
     * @return spec_image_key - 产品规格图
     */
    public String getSpecImageKey() {
        return specImageKey;
    }

    /**
     * 设置产品规格图
     *
     * @param specImageKey 产品规格图
     */
    public void setSpecImageKey(String specImageKey) {
        this.specImageKey = specImageKey;
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
        sb.append(", spuId=").append(spuId);
        sb.append(", specValueId=").append(specValueId);
        sb.append(", specImageKey=").append(specImageKey);
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
        GoodsSpuSpec other = (GoodsSpuSpec) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getSpuId() == null ? other.getSpuId() == null : this.getSpuId().equals(other.getSpuId()))
                && (this.getSpecValueId() == null ? other.getSpecValueId() == null : this.getSpecValueId().equals(other.getSpecValueId()))
                && (this.getSpecImageKey() == null ? other.getSpecImageKey() == null : this.getSpecImageKey().equals(other.getSpecImageKey()))
                && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
                && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
                && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSpuId() == null) ? 0 : getSpuId().hashCode());
        result = prime * result + ((getSpecValueId() == null) ? 0 : getSpecValueId().hashCode());
        result = prime * result + ((getSpecImageKey() == null) ? 0 : getSpecImageKey().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }
}