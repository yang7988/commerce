package com.commerce.huayi.entity.response;

import com.commerce.huayi.annotation.Translate;

@Translate(refTable = "tb_goods_category")
public class CategoryVo {

    //主键id
    private Long id;

    //父级分类id
    private Long parentId;

    //分类名称
    @Translate(refTable = "tb_goods_category",refColumn = "category_name")
    private String categoryName;

    //分类描述
    @Translate(refTable = "tb_goods_category",refColumn = "category_description")
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
}