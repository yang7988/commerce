package com.commerce.huayi.entity.request;

import java.util.Date;

/**
 * 新闻中心
 * @date 2019-03-16
 * */
public class NewsInfoReq {

    private String title;

    private String content;

    private Date effectDate;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }


}
