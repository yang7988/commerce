package com.commerce.huayi.pagination;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Hugo.Wwg
 * @Since 2017-08-21
 */
public final class Page<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int DEFAULT_PAGE_SIZE = 10;

    // 每页显示多少条记录，固定值，可以从配置文件中获取
    @ApiModelProperty(value = "每页显示数据条数pageMaxSize",example = "10")
    private int pageMaxSize;

    // 当前页码，传进来
    @ApiModelProperty(value = "当前页码pageIndex",example = "1")
    private int pageIndex;

    // 每页的起始位置，通过当前页面和每页显示多少条记录算出来的
    @ApiModelProperty(value = "数据的偏移量",example = "0")
    private int offset;

    // 总记录数，查询出来的，传进来
    @ApiModelProperty(value = "数据库总记录数",example = "5")
    private int count;

    // 每页显示的数据,查询出来的，传进来
    @ApiModelProperty(value = "数据列表,跟具体业务类型相关，举个产品列表的例子",example = "[\n" +
            "            {\n" +
            "                \"id\": 1,\n" +
            "                \"spuNo\": \"132156465kjkj\",\n" +
            "                \"goodsName\": \"蓝牙大耳机样品\",\n" +
            "                \"goodsDescription\": \"蓝牙大耳机样品\",\n" +
            "                \"goodsImageKey\": \"\",\n" +
            "                \"lowPrice\": 500,\n" +
            "                \"categoryId\": 10,\n" +
            "                \"brandId\": 0,\n" +
            "                \"specId\": 1,\n" +
            "                \"specNo\": \"1456498132132\",\n" +
            "                \"specName\": \"颜色\",\n" +
            "                \"specDescription\": \"用于区分商品颜色\",\n" +
            "                \"specValueId\": 1,\n" +
            "                \"specValue\": \"红色\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 1,\n" +
            "                \"spuNo\": \"132156465kjkj\",\n" +
            "                \"goodsName\": \"蓝牙大耳机样品\",\n" +
            "                \"goodsDescription\": \"蓝牙大耳机样品\",\n" +
            "                \"goodsImageKey\": \"\",\n" +
            "                \"lowPrice\": 500,\n" +
            "                \"categoryId\": 10,\n" +
            "                \"brandId\": 0,\n" +
            "                \"specId\": 1,\n" +
            "                \"specNo\": \"1456498132132\",\n" +
            "                \"specName\": \"颜色\",\n" +
            "                \"specDescription\": \"用于区分商品颜色\",\n" +
            "                \"specValueId\": 2,\n" +
            "                \"specValue\": \"绿色\"\n" +
            "            }\n" +
            "            \n" +
            "        ]")
    private List<T> list;

    // 总页数，计算出来，通过总记录数和每页显示多少条记录算出来的
    @ApiModelProperty(value = "总页数",example = "1")
    private int pageCount;

    private Page() {
    }

    private Page(int pageIndex, int pageMaxSize) {
        this(pageIndex, pageMaxSize, 0);
    }

    private Page(int pageIndex, int pageMaxSize, int count) {
        this(pageIndex, pageMaxSize, count, null);
    }

    private Page(int pageIndex, int pageMaxSize, int count, List<T> list) {
        this.setPageMaxSize(pageMaxSize);
        this.setCount(count);
        this.setPageIndex(pageIndex);
        this.setList(list);
    }

    public void setPageMaxSize(int pageMaxSize) {
        this.pageMaxSize = pageMaxSize <= 0 ? DEFAULT_PAGE_SIZE : pageMaxSize;
    }

    public int getPageMaxSize() {
        return pageMaxSize;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex < 1 ? 1 : pageIndex;
        this.setOffset((this.pageIndex - 1) * pageMaxSize);
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setCount(int count) {
        this.count = count;
        this.setPageCount(count % pageMaxSize == 0 ? count / pageMaxSize : count / pageMaxSize + 1);
    }

    public int getCount() {
        return count;
    }

    //设置数据库偏移索引,如果偏移量为负数则数据库会出错
    public void setOffset(int offset) {
        this.offset = offset < 0 ? 0 : offset;
    }

    public int getOffset() {
        return offset;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public static <T> Page<T> create(int pageIndex, int pageMaxSize) {
        return new Page<>(pageIndex, pageMaxSize);
    }

    public static <T> Page<T> create(int pageIndex, int pageMaxSize, int count) {
        return new Page<>(pageIndex, pageMaxSize, count);
    }

    public static <T> Page<T> create(int pageIndex, int pageMaxSize, int count, List<T> list) {
        return new Page<>(pageIndex, pageMaxSize, count, list);
    }


}