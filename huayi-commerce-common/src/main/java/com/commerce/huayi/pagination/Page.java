package com.commerce.huayi.pagination;

import java.io.Serializable;

/**
 * @Author Hugo.Wwg
 * @Since 2017-08-21
 */
public final class Page implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 分页查询开始记录位置.
     */
    private int begin;
    /**
     * 分页查看下结束位置.
     */
    private int end;
    /**
     * 每页显示记录数.
     */
    private int length = 20;
    /**
     * 查询结果总记录数.
     */
    private int totalRecords;
    /**
     * 当前页码.
     */
    private int pageNo;
    /**
     * 总共页数.
     */
    private int pageCount;

    public Page() {
    }

    /**
     * 构造函数.
     */
    public Page(int begin, int length) {
        this.begin = begin;
        this.length = length;
        this.end = this.begin + this.length;
        this.pageNo = (int) Math.floor((this.begin * 1.0d) / this.length) + 1;
    }

    /**
     * @param begin
     * @param length
     * @param totalRecords
     */
    public Page(int begin, int length, int totalRecords) {
        this(begin, length);
        this.totalRecords = totalRecords;
    }

    /**
     * 设置页数，自动计算数据范围.
     */
    public Page(int pageNo) {
        this.pageNo = pageNo;
        pageNo = pageNo > 0 ? pageNo : 1;
        this.begin = this.length * (pageNo - 1);
        this.end = this.length * pageNo;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
        if (this.length != 0) {
            this.pageNo = (int) Math.floor((this.begin * 1.0d) / this.length) + 1;
        }
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
        if (this.begin != 0) {
            this.pageNo = (int) Math.floor((this.begin * 1.0d) / this.length) + 1;
        }
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
        this.pageCount = (int) Math.floor((this.totalRecords * 1.0d) / this.length);
        if (this.totalRecords % this.length != 0) {
            this.pageCount++;
        }
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
        pageNo = pageNo > 0 ? pageNo : 1;
        this.begin = this.length * (pageNo - 1);
        this.end = this.length * pageNo;
    }

    public int getPageCount() {
        if (pageCount == 0) {
            return 1;
        }
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }


    public static Page createPage(int total, int pageNum, int pageSize) {
        Page page = new Page();
        page.setLength(pageSize);
        page.setPageNo(pageNum);
        page.setTotalRecords(total);
        return page;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("begin=").append(begin).append(", end=")
                .append(end).append(", length=").append(length).append(", totalRecords=").append(
                        totalRecords).append(", pageNo=").append(pageNo).append(", pageCount=")
                .append(pageCount);

        return builder.toString();
    }
}