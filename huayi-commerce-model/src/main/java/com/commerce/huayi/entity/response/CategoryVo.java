package com.commerce.huayi.entity.response;

import com.commerce.huayi.annotation.Translate;

public class CategoryVo {

    //主键id
    private Long id;

    //父级分类id
    private Long parentId;

    //分类名称
    @Translate
    private String categoryName;

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
}