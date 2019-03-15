package com.commerce.huayi.entity.response;

public class CategoryVo {

    //主键id
    private Long id;

    //父级分类id
    private Long parentId;

    //国际化翻译的key
    private String translateKey;

    //分类名称
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

    public String getTranslateKey() {
        return translateKey;
    }

    public void setTranslateKey(String translateKey) {
        this.translateKey = translateKey;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}