package com.commerce.huayi.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 数据分页信息
 *
 * @date 2019-03-26
 */
@ApiModel(value = "数据分页请求json对象")
public class PageRequest {

    @ApiModelProperty(value = "每页显示数据条数pageMaxSize", example = "10", dataType = "int")
    @NotNull
    private int pageMaxSize;

    @ApiModelProperty(value = "当前页码pageIndex", example = "1", dataType = "int")
    @NotNull
    private int pageIndex;

    public int getPageMaxSize() {
        return pageMaxSize;
    }

    public void setPageMaxSize(int pageMaxSize) {
        this.pageMaxSize = pageMaxSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
