package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class AddGoodsReq {

    //商品名称
    @ApiModelProperty(value = "产品名称",required = true)
    private String goodsName;

    //商品描述
    @ApiModelProperty(value = "产品描述",required = true)
    private String goodsDescription;


    //最低售价
    @ApiModelProperty(value = "产品价格",required = true)
    private BigDecimal price;

    //分类id
    @ApiModelProperty(value = "产品所属分类id",required = true)
    private Long categoryId;

    //规格名称
    @ApiModelProperty(value = "产品的规格名称",required = true)
    private String specName;

    //规格描述
    @ApiModelProperty(value = "产品的规格描述",required = true)
    private String specDescription;

    //规格值
    @ApiModelProperty(value = "产品的规格具体值",required = true)
    private String specValue;


}