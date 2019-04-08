package com.commerce.huayi.entity.db;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "tb_goods_spu")
public class GoodsSpu implements Serializable {
    /**
     * 主键id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 商品编号，唯一
     */
    @Column(name = "spu_no")
    private String spuNo;

    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品描述
     */
    @Column(name = "goods_description")
    private String goodsDescription;

    /**
     * 商品的图片地址
     */
    @Column(name = "goods_image_key")
    private String goodsImageKey;

    /**
     * 最低售价
     */
    @Column(name = "low_price")
    private BigDecimal lowPrice;

    /**
     * 分类id
     */
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 品牌id
     */
    @Column(name = "brand_id")
    private Long brandId;

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
     * 获取商品编号，唯一
     *
     * @return spu_no - 商品编号，唯一
     */
    public String getSpuNo() {
        return spuNo;
    }

    /**
     * 设置商品编号，唯一
     *
     * @param spuNo 商品编号，唯一
     */
    public void setSpuNo(String spuNo) {
        this.spuNo = spuNo;
    }

    /**
     * 获取商品名称
     *
     * @return goods_name - 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置商品名称
     *
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取商品描述
     *
     * @return goods_description - 商品描述
     */
    public String getGoodsDescription() {
        return goodsDescription;
    }

    /**
     * 设置商品描述
     *
     * @param goodsDescription 商品描述
     */
    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    /**
     * 获取商品的图片地址
     *
     * @return goods_image_key - 商品的图片地址
     */
    public String getGoodsImageKey() {
        return goodsImageKey;
    }

    /**
     * 设置商品的图片地址
     *
     * @param goodsImageKey 商品的图片地址
     */
    public void setGoodsImageKey(String goodsImageKey) {
        this.goodsImageKey = goodsImageKey;
    }

    /**
     * 获取最低售价
     *
     * @return low_price - 最低售价
     */
    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    /**
     * 设置最低售价
     *
     * @param lowPrice 最低售价
     */
    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    /**
     * 获取分类id
     *
     * @return category_id - 分类id
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * 设置分类id
     *
     * @param categoryId 分类id
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取品牌id
     *
     * @return brand_id - 品牌id
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * 设置品牌id
     *
     * @param brandId 品牌id
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
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
        sb.append(", spuNo=").append(spuNo);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsDescription=").append(goodsDescription);
        sb.append(", goodsImageKey=").append(goodsImageKey);
        sb.append(", lowPrice=").append(lowPrice);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", brandId=").append(brandId);
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
        GoodsSpu other = (GoodsSpu) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSpuNo() == null ? other.getSpuNo() == null : this.getSpuNo().equals(other.getSpuNo()))
            && (this.getGoodsName() == null ? other.getGoodsName() == null : this.getGoodsName().equals(other.getGoodsName()))
            && (this.getGoodsDescription() == null ? other.getGoodsDescription() == null : this.getGoodsDescription().equals(other.getGoodsDescription()))
            && (this.getGoodsImageKey() == null ? other.getGoodsImageKey() == null : this.getGoodsImageKey().equals(other.getGoodsImageKey()))
            && (this.getLowPrice() == null ? other.getLowPrice() == null : this.getLowPrice().equals(other.getLowPrice()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getBrandId() == null ? other.getBrandId() == null : this.getBrandId().equals(other.getBrandId()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSpuNo() == null) ? 0 : getSpuNo().hashCode());
        result = prime * result + ((getGoodsName() == null) ? 0 : getGoodsName().hashCode());
        result = prime * result + ((getGoodsDescription() == null) ? 0 : getGoodsDescription().hashCode());
        result = prime * result + ((getGoodsImageKey() == null) ? 0 : getGoodsImageKey().hashCode());
        result = prime * result + ((getLowPrice() == null) ? 0 : getLowPrice().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getBrandId() == null) ? 0 : getBrandId().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }
}