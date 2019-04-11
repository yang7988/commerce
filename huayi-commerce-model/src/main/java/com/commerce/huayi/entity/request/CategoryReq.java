package com.commerce.huayi.entity.request;

import com.commerce.huayi.annotation.Pretreatment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@ApiModel(value = "商品分类的请求json对象")
public class CategoryReq {

    //父级分类id
    @ApiModelProperty(value = "分类父id",example = "0")
    @NotNull
    private Long parentId;

    //分类名称
    @ApiModelProperty(value = "分类名",example = "big_earphone")
    @NotNull
    @Pretreatment
    private String categoryName;

    //分类描述
    @ApiModelProperty(value = "分类描述",example = "big_earphone")
    @NotNull
    @Pretreatment
    private String categoryDescription;

    //分类名称及描述的翻译可选字段
    @ApiModelProperty(value = "分类名称及描述的翻译可选字段",position = 4,example = "{\n" +
            "        \"categoryName_english\":\"big_earphone\",\n" +
            "        \"categoryName_chinese\":\"大耳机\",\n" +
            "        \"categoryDescription_english\":\"big_earphone_desc_english\",\n" +
            "        \"categoryDescription_chinese\":\"中文大耳机描述\"\n" +
            "    }")
    private Map<String,String> translation;

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

    public Map<String, String> getTranslation() {
        return translation;
    }

    public void setTranslation(Map<String, String> translation) {
        this.translation = translation;
    }

    public Map<String,String> buildSql(String language) {
        if (StringUtils.isBlank(this.categoryName) || StringUtils.isBlank(this.categoryDescription)) {
            return null;
        }
        String categoryNameKey = "categoryName_".concat(language);
        String categoryDescriptionKey = "categoryDescription_".concat(language);
        String categoryNameTranslate = translation.get(categoryNameKey);
        String categoryDescriptionTranslate = translation.get(categoryDescriptionKey);
        if (StringUtils.isBlank(categoryNameTranslate) || StringUtils.isBlank(categoryDescriptionTranslate)) {
            return null;
        }
        String preSql = "insert into tb_goods_category_%s (category_name,category_name_translate,category_description," +
                "category_description_translate) values('%s','%s','%s','%s')";
        String sql = String.format(preSql, language, this.categoryName, categoryNameTranslate,
                this.categoryDescription, categoryDescriptionTranslate);
        Map<String, String> sqlMap = new HashMap<>(1);
        sqlMap.put("sqlStatement", sql);
        return sqlMap;
    }
}