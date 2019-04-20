package com.commerce.huayi.constant;

public enum RequestHeaderEnum {
    language("language"),
    loginName("loginName"),
    adminToken("adminToken");

    private String headerName;

    RequestHeaderEnum(String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderName() {
        return this.headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }
}
