package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AddGoodsReq {

    //商品名称
    @ApiModelProperty(value = "产品名称",required = true)
    @NotNull
    private String goodsName;

    //商品描述
    @ApiModelProperty(value = "产品描述",required = true)
    @NotNull
    private String goodsDescription;

    //最低售价
    @ApiModelProperty(value = "产品价格")
    private BigDecimal price;

    //分类id
    @ApiModelProperty(value = "产品所属分类id",required = true)
    @NotNull
    private Long categoryId;

    //规格名称
    @ApiModelProperty(value = "产品的规格名称",required = true)
    @NotNull
    private String specName;

    //规格描述
    @ApiModelProperty(value = "产品的规格描述",required = true)
    @NotNull
    private String specDescription;

    //规格值
    @ApiModelProperty(value = "产品的规格具体值",required = true)
    @NotNull
    private String specValue;

    //产品名称及规格的翻译可选字段
    @ApiModelProperty(value = "产品名称及规格的翻译可选字段",dataType = "String")
    private Map<String,String> optionals;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getSpecDescription() {
        return specDescription;
    }

    public void setSpecDescription(String specDescription) {
        this.specDescription = specDescription;
    }

    public String getSpecValue() {
        return specValue;
    }

    public void setSpecValue(String specValue) {
        this.specValue = specValue;
    }

    public Map<String, String> getOptionals() {
        return optionals;
    }

    public void setOptionals(Map<String, String> optionals) {
        this.optionals = optionals;
    }

    public Map<String, String> buildSql(String language) {
        Map<String, String> sqlMap = new HashMap<>();
        if (StringUtils.isBlank(this.goodsName) || StringUtils.isBlank(this.goodsDescription)) {
            String goodsNameKey = "goodsName_".concat(language);
            String goodsDescriptionKey = "goodsDescription_".concat(language);
            String goodsNameTranslate = optionals.get(goodsNameKey);
            String goodsDescriptionTranslate = optionals.get(goodsDescriptionKey);
            if (StringUtils.isNotBlank(goodsNameTranslate) && StringUtils.isNotBlank(goodsDescriptionTranslate)) {
                String preSql = "insert into tb_goods_spu_%s (goods_name,goods_name_translate,goods_description," +
                        "goods_description_translate) values('%s','%s','%s','%s')";
                String sql = String.format(preSql, language, this.goodsName, goodsNameTranslate,
                        this.goodsDescription, goodsDescriptionTranslate);
                sqlMap.put("sqlStatement", sql);
            }
        }

        if (StringUtils.isNotBlank(this.specName) || StringUtils.isNotBlank(this.specDescription)) {
            String specNameKey = "specName_".concat(language);
            String specDescriptionKey = "specDescription_".concat(language);
            String specTranslate = optionals.get(specNameKey);
            String specDescriptionTranslate = optionals.get(specDescriptionKey);
            if (StringUtils.isNotBlank(specTranslate) && StringUtils.isNotBlank(specDescriptionTranslate)) {
                String specPreSql = "insert into tb_goods_spec_%s (spec_name,spec_name_translate,spec_description," +
                        "spec_description_translate) values('%s','%s','%s','%s')";
                String specSql = String.format(specPreSql, language, this.specName, specTranslate,
                        this.specDescription, specDescriptionTranslate);
                sqlMap.put("specSqlStatement", specSql);
            }
        }

        if (StringUtils.isNotBlank(this.specValue)) {
            String specValueKey = "specValue_".concat(language);
            String specValueTranslate = optionals.get(specValueKey);
            if (StringUtils.isNotBlank(specValueTranslate)) {
                String specValuePreSql = "insert into tb_goods_spec_value_%s (spec_value,spec_value_translate) values('%s','%s')";
                String specValueSql = String.format(specValuePreSql, language, this.specValue, specValueTranslate);
                sqlMap.put("specValueSql", specValueSql);
            }
        }
        return sqlMap;
    }
}