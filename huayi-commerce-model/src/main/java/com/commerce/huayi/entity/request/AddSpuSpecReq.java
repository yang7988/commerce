package com.commerce.huayi.entity.request;

import com.commerce.huayi.annotation.Pretreatment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@ApiModel(value = "添加规格请求json对象")
@Pretreatment
public class AddSpuSpecReq {

    //规格名称
    @ApiModelProperty(value = "产品的规格名称",required = true,example = "color")
    @NotNull
    @Pretreatment
    private String specName;

    //规格描述
    @ApiModelProperty(value = "产品的规格描述",required = true,example = "describe_color")
    @NotNull
    @Pretreatment
    private String specDescription;

    //规格值
    @ApiModelProperty(value = "产品的规格具体值",required = true,example = "red")
    @NotNull
    @Pretreatment
    private String specValue;

    //产品名称及规格的翻译可选字段
    @ApiModelProperty(value = "产品名称及规格的翻译可选字段",position = 4,example = "{\n" +
            "        \"specName_english\":\"color\",\n" +
            "        \"specName_chinese\":\"颜色\",\n" +"  " +
            "        \"specDescription_english\":\"describe_goods_color\",\n" +
            "        \"specDescription_chinese\":\"描述产品颜色\",\n" +
            "        \"specValue_english\":\"red\",\n" +
            "        \"specValue_chinese\":\"红色\"\n" +
            "    }")
    private Map<String,String> translation;

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

    public Map<String, String> getTranslation() {
        return translation;
    }

    public void setTranslation(Map<String, String> translation) {
        this.translation = translation;
    }

    public Map<String, String> buildSql(String language) {
        Map<String, String> sqlMap = new HashMap<>();
        String specNameKey = "specName_".concat(language);
        String specDescriptionKey = "specDescription_".concat(language);
        String specTranslate = translation.get(specNameKey);
        String specDescriptionTranslate = translation.get(specDescriptionKey);
        if (StringUtils.isBlank(specTranslate) || StringUtils.isBlank(specDescriptionTranslate)) {
            return null;
        }
        String specPreSql = "insert into tb_goods_spec_%s (spec_name,spec_name_translate,spec_description," +
                "spec_description_translate) values('%s','%s','%s','%s')";
        String specSql = String.format(specPreSql, language, this.specName, specTranslate, this.specDescription, specDescriptionTranslate);
        sqlMap.put("specSqlStatement", specSql);
        String specValueKey = "specValue_".concat(language);
        String specValueTranslate = translation.get(specValueKey);
        if (StringUtils.isBlank(specValueTranslate)) {
            return null;
        }
        String specValuePreSql = "insert into tb_goods_spec_value_%s (spec_value,spec_value_translate) values('%s','%s')";
        String specValueSql = String.format(specValuePreSql, language, this.specValue, specValueTranslate);
        sqlMap.put("specValueSql", specValueSql);
        return sqlMap;
    }
}