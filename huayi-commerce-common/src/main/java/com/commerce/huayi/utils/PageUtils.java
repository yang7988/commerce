package com.commerce.huayi.utils;

public class PageUtils {

    /**
     * 把传过来的页数以及每一个显示条数转换成数据库分页的数据行号
     *
     * @param pageNum   页码
     * @param pageCount 每一页显示数据条数 默认10
     * @return int 数据开始下标
     */
    public static int pageNumCastToRowNum(int pageNum, int pageCount) {
        int rowNum = 0;
        if (pageNum > 0 && pageCount > 0) {
            rowNum = (pageNum - 1) * pageCount;
        } else {
            rowNum = 0;
        }
        return rowNum;
    }

}
