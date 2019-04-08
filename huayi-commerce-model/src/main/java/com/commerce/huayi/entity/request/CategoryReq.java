package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@ApiModel(value = "商品分类的请求json对象")
public class CategoryReq extends HashMap<String,String> {

    @ApiModelProperty(value = "分类id",dataType = "int")
    private Long id;

    //父级分类id
    @ApiModelProperty(value = "分类父id",dataType = "String")
    private Long parentId;

    //分类名称
    @ApiModelProperty(value = "分类名",dataType = "String")
    private String categoryName;

    //分类描述
    @ApiModelProperty(value = "分类描述",dataType = "String")
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

    public Map<String,String> buildSql(String language) {
        if (StringUtils.isBlank(get("categoryName")) || StringUtils.isBlank(get("categoryDescription"))) {
            return null;
        }
        String categoryNameKey = "categoryName_".concat(language);
        String categoryDescriptionKey = "categoryDescription_".concat(language);
        String categoryNameTranslate = get(categoryNameKey);
        String categoryDescriptionTranslate = get(categoryDescriptionKey);
        if (StringUtils.isBlank(categoryNameTranslate) || StringUtils.isBlank(categoryDescriptionTranslate)) {
            return null;
        }
        String preSql = "insert into tb_goods_category_%s (category_name,category_name_translate,category_description," +
                "category_description_translate) values('%s','%s','%s','%s')";
        String sql = String.format(preSql, language, get("categoryName"), categoryNameTranslate,
                get("categoryDescription"), categoryDescriptionTranslate);
        Map<String, String> sqlMap = new HashMap<>(1);
        sqlMap.put("sqlStatement", sql);
        return sqlMap;
    }
}