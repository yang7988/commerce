package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

public class AddSpuSpecReq {

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
        String specNameKey = "specName_".concat(language);
        String specDescriptionKey = "specDescription_".concat(language);
        String specTranslate = optionals.get(specNameKey);
        String specDescriptionTranslate = optionals.get(specDescriptionKey);
        if (StringUtils.isBlank(specTranslate) || StringUtils.isBlank(specDescriptionTranslate)) {
            return null;
        }
        String specPreSql = "insert into tb_goods_spec_%s (spec_name,spec_name_translate,spec_description," +
                "spec_description_translate) values('%s','%s','%s','%s')";
        String specSql = String.format(specPreSql, language, this.specName, specTranslate, this.specDescription, specDescriptionTranslate);
        sqlMap.put("specSqlStatement", specSql);
        String specValueKey = "specValue_".concat(language);
        String specValueTranslate = optionals.get(specValueKey);
        if (StringUtils.isBlank(specValueTranslate)) {
            return null;
        }
        String specValuePreSql = "insert into tb_goods_spec_value_%s (spec_value,spec_value_translate) values('%s','%s')";
        String specValueSql = String.format(specValuePreSql, language, this.specValue, specValueTranslate);
        sqlMap.put("specValueSql", specValueSql);
        return sqlMap;
    }
}