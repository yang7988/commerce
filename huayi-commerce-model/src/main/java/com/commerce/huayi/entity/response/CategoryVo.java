package com.commerce.huayi.entity.response;

import java.util.List;

public class CategoryVo {

    private Long id;

    //父级分类id
    private Long parentId;

    //分类名称
    private String categoryName;

    //分类描述
    private String categoryDescription;

    private List<CategoryVo> subCategories;

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

    public List<CategoryVo> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<CategoryVo> subCategories) {
        this.subCategories = subCategories;
    }
}