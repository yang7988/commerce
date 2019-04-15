package com.commerce.huayi.entity.request;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class AddSpuSpecValueReq extends AbstractDictReq {

    private String specValue;

    private Map<String,String> translation;

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

    @Override
    public Map<String, Object> buildSql(String language) {
        Map<String, Object> sqlMap = new HashMap<>();

        String specValueKey = "specValue_".concat(language);
        String specValueTranslate = translation.get(specValueKey);
        if (StringUtils.isBlank(specValueTranslate)) {
            return null;
        }
        sqlMap.put("table","tb_goods_spec_value_".concat(language));
        sqlMap.put("id", getDictId());
        sqlMap.put("spec_value",specValue);
        sqlMap.put("spec_value_translate",specValueTranslate);
        /*String specValuePreSql = "insert into tb_goods_spec_value_%s (spec_value,spec_value_translate) " +
                "select '%s','%s' FROM DUAL WHERE NOT EXISTS (SELECT spec_value from tb_goods_spec_value_%s where spec_value = '%s')";
        String specValueSql = String.format(specValuePreSql, language, this.specValue, specValueTranslate,language,this.specValue);
        sqlMap.put("specValueSql", specValueSql);*/
        return sqlMap;
    }
}