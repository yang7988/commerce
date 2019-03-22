package com.commerce.huayi.entity.response;

import com.commerce.huayi.annotation.Translate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "商品分类response body")
public class CategoryVo {

    //主键id
    @ApiModelProperty(value = "分类id",required = true)
    private Long id;

    //父级分类id
    @ApiModelProperty(value = "父级分类id",required = true)
    private Long parentId;

    //能否展开
    @ApiModelProperty(value = "分类是否有可以展开的子分类",required = true)
    private Byte isOpen;
    //分类名称
    @Translate(refTable = "tb_goods_category",refColumn = "category_name")
    @ApiModelProperty(value = "分类名",required = true)
    private String categoryName;

    //分类描述
    @Translate(refTable = "tb_goods_category",refColumn = "category_description")
    @ApiModelProperty(value = "分类描述",required = true)
    private String categoryDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Byte getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Byte isOpen) {
        this.isOpen = isOpen;
    }
}