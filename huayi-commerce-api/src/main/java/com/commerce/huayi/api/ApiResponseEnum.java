package com.commerce.huayi.api;

import java.io.Serializable;

public enum ApiResponseEnum implements Serializable {
    SUCCESS(200, "SUCCESS", "成功", true),
    FAIL(100, "FAIL", "失败", false),
    FORBIDDEN(403, "FORBIDDEN", "没有权限", false),
    RESOURCE_NOT_FOUND(404, "RESOURCE_NOT_FOUND", "资源不存在", false),
    INTERNAL_ERROR(500, "INTERNAL_ERROR", "服务器处理失败", false),
    PARAMETER_INVALID(601, "PARAMETER_INVALID", "非法参数", false),
    ABSENCE_LANGUAGE_PARAM(602, "ABSENCE_LANGUAGE_PARAM", "缺少language请求头部参数", false),
    CONTENT_TYPE_ILLEGAL(603, "CONTENT_TYPE_ILLEGAL", "Content-Type必须为application/json", false),
    LANGUAGE_PARAM_ILLEGAL(604, "LANGUAGE_PARAM_ILLEGAL", "language请求头部参数必须是chinese/english/japanese/french/german语系中一种", false),
    DO_NOT_HAVE_ANY_MORE_RECORD(700, "DO_NOT_HAVE_ANY_MORE_RECORD", "没有更多记录", false);

    protected int id;
    protected String code;
    protected String label;
    protected boolean success;

    private ApiResponseEnum(int id, String code, String label, boolean success) {
        this.id = id;
        this.code = code;
        this.label = label;
        this.success = success;
    }

    public int getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public String getLabel() {
        return this.label;
    }

    public boolean success() {
        return this.success;
    }
}
