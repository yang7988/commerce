package com.commerce.huayi.entity.request;

import com.commerce.huayi.annotation.Dictionary;
import com.commerce.huayi.annotation.Pretreatment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@ApiModel(value = "产品添加的请求json对象")
@Pretreatment
@Dictionary
public class AddGoodsReq extends AbstractDictReq {

    //商品名称
    @ApiModelProperty(value = "产品名称",required = true,example = "blue_earphone")
    @NotNull
    @Pretreatment
    private String goodsName;

    //商品描述
    @ApiModelProperty(value = "产品描述",required = true,example = "blue_earphone")
    @NotNull
    @Pretreatment
    private String goodsDescription;

    //商品描述
    @ApiModelProperty(value = "产品图片url地址",required = true,example = "http://localhost/image/earphone")
    @NotNull
    private String goodsImageKey;

    //最低售价
    @ApiModelProperty(value = "产品价格",example = "66.36")
    private BigDecimal price;

    //分类id
    @ApiModelProperty(value = "产品所属分类id",required = true,example = "10")
    @NotNull
    private Long categoryId;

    //产品名称及规格的翻译可选字段
    @ApiModelProperty(value = "产品名称及规格的翻译可选字段",position = 8,example = "{\n" +
            "    \"goodsName_english\":\"iphone6\",\n" +
            "    \"goodsName_chinese\":\"爱疯6\",\n" +
            "    \"goodsDescription_english\":\"iphone6\",\n" +
            "    \"goodsDescription_chinese\":\"爱疯6代\",\n" +
            "}")
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

    public String getGoodsImageKey() {
        return goodsImageKey;
    }

    public void setGoodsImageKey(String goodsImageKey) {
        this.goodsImageKey = goodsImageKey;
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

    public Map<String, String> getOptionals() {
        return optionals;
    }

    public void setOptionals(Map<String, String> optionals) {
        this.optionals = optionals;
    }

    @Override
    public Map<String, Object> buildSql(String language) {
        if (StringUtils.isBlank(this.goodsName) || StringUtils.isBlank(this.goodsDescription)) {
            return null;
        }

        String goodsNameKey = "goodsName_".concat(language);
        String goodsDescriptionKey = "goodsDescription_".concat(language);
        String goodsNameTranslate = optionals.get(goodsNameKey);
        String goodsDescriptionTranslate = optionals.get(goodsDescriptionKey);
        if (StringUtils.isBlank(goodsNameTranslate) || StringUtils.isBlank(goodsDescriptionTranslate)) {
           return null;
        }
        Map<String, Object> sqlMap = new HashMap<>();
        sqlMap.put("table","tb_goods_spu_".concat(language));
        sqlMap.put("id", getDictId());
        sqlMap.put("goods_name",goodsName);
        sqlMap.put("goods_name_translate",goodsNameTranslate);
        sqlMap.put("goods_description",goodsDescription);
        sqlMap.put("goods_description_translate",goodsDescriptionTranslate);

        /*Map<String, String> sqlMap = new HashMap<>();
        String preSql = "insert into tb_goods_spu_%s (goods_name,goods_name_translate,goods_description," +
                "goods_description_translate) values('%s','%s','%s','%s')";
        String sql = String.format(preSql, language, this.goodsName, goodsNameTranslate,
                this.goodsDescription, goodsDescriptionTranslate);
        sqlMap.put("sqlStatement", sql);*/
        return sqlMap;
    }
}