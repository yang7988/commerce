package com.commerce.huayi.api;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "接口返回的json对象")
public class ApiResponse<T> implements Serializable {

    @ApiModelProperty(value = "版本号", required = true)
    private final String version = "1.0.0";

    @ApiModelProperty(value = "成功或失败标识", required = true)
    private boolean result;

    @ApiModelProperty(value = "状态码简称", required = true)
    private String message = "";

    @ApiModelProperty(value = "状态码描述", required = true)
    private String description = "";

    @ApiModelProperty(value = "状态码", required = true)
    private int code;

    @ApiModelProperty(value = "服务端返回数据", required = true)
    private T data;

    @ApiModelProperty(value = "服务端响应时间戳", required = true)
    private Long serverTime;

    public ApiResponse() {
        this(ApiResponseEnum.SUCCESS);
    }

    private ApiResponse(ApiResponseEnum apiResponseEnum) {
        this.result = apiResponseEnum.success();
        this.code = apiResponseEnum.getId();
        this.message = apiResponseEnum.getCode().toLowerCase();
        this.description = apiResponseEnum.getLabel();
        this.serverTime = new Date().getTime();
    }

    private ApiResponse(ApiResponseEnum result, T t) {
        this(result);
        this.data = t;
    }

    private ApiResponse(String message, T t) {
        this.message = message;
        this.data = t;
    }

    public static <T> ApiResponse<T> returnSuccess() {
        return new ApiResponse<>();
    }

    public static <T> ApiResponse<T> returnSuccess(T data) {
        return new ApiResponse<>(ApiResponseEnum.SUCCESS, data);
    }

    public static <T> ApiResponse<T> returnSuccess(T data, ApiResponseEnum result) {
        return new ApiResponse<>(result, data);
    }

    public static <T> ApiResponse<T> returnFail(ApiResponseEnum result, String appendErrorMessage) {
        ApiResponse<T> apiResponse = returnFail(result);
        if (appendErrorMessage != null) {
            apiResponse.description = (apiResponse.message + "（" + appendErrorMessage + "）");
        }
        return apiResponse;
    }

    public static <T> ApiResponse<T> returnFail(ApiResponseEnum result) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.result = false;
        apiResponse.message = result.getCode().toLowerCase();
        apiResponse.description = result.getLabel();
        apiResponse.code = result.getId();
        return apiResponse;
    }

    public static <T> ApiResponse<T> returnFail(String errorMessage) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.result = false;
        apiResponse.message = ApiResponseEnum.FAIL.getCode().toLowerCase();
        apiResponse.description = errorMessage;
        apiResponse.code = ApiResponseEnum.FAIL.getId();
        return apiResponse;
    }


    public Long getServerTime() {
        return this.serverTime;
    }

    public void setServerTime(Long serverTime) {
        this.serverTime = new Date().getTime();
    }

    public String getVersion() {
        return "1.0.0";
    }

    public boolean isResult() {
        return this.result;
    }

    public String getMessage() {
        return (this.message == null) || ("".equals(this.message.trim())) ? "" : this.message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
