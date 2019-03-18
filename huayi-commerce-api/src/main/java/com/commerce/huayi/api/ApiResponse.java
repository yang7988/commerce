package com.commerce.huayi.api;

import com.alibaba.fastjson.JSON;
import com.commerce.huayi.annotation.Translate;
import com.commerce.huayi.constant.Constant;

import java.io.Serializable;
import java.util.Date;

@Translate(refTable = Constant.TRANSLATE_API_RESPONSE_TABLE_PREFIX )
public class ApiResponse<T> implements Serializable {
    private final String version = "1.0.0";
    private boolean result;
    @Translate(refColumn = Constant.TRANSLATE_API_RESPONSE_COLUMN)
    private String message = "";
    private int code;
    private T data;
    private Long serverTime;

    public ApiResponse() {
        setApiResponseEnum(ApiResponseEnum.SUCCESS);
    }

    private ApiResponse(T t) {
        this();
        this.data = t;
    }

    private ApiResponse(ApiResponseEnum result) {
        setApiResponseEnum(result);
    }

    private ApiResponse(ApiResponseEnum result, T t) {
        setApiResponseEnum(result);
        this.data = t;
    }

    private ApiResponse(String message, T t) {
        this.message = message;
        this.data = t;
    }

    public static <T> ApiResponse<T> returnSuccess() {
        return new ApiResponse();
    }

    public static <T> ApiResponse<T> returnSuccess(T data) {
        return new ApiResponse(ApiResponseEnum.SUCCESS, data);
    }

    public static <T> ApiResponse<T> returnSuccess(T data, ApiResponseEnum result) {
        return new ApiResponse(result, data);
    }

    public static <T> ApiResponse<T> returnSuccess(T data, String errorMessage) {
        return new ApiResponse(errorMessage, data);
    }

    public static <T> ApiResponse<T> returnSuccess(ApiResponseEnum result) {
        return new ApiResponse(result);
    }

    public static <T> ApiResponse<T> returnFail(ApiResponseEnum result, String appendErrorMessage, T data) {
        ApiResponse apiResponse = returnFail(result, appendErrorMessage);
        apiResponse.setData(data);
        return apiResponse;
    }

    public static <T> ApiResponse<T> returnFail(ApiResponseEnum result, String appendErrorMessage) {
        ApiResponse apiResponse = returnFail(result);
        if (appendErrorMessage != null) {
            apiResponse.message = (apiResponse.message + "（" + appendErrorMessage + "）");
        }
        return apiResponse;
    }

    public static <T> ApiResponse<T> returnFail(ApiResponseEnum result) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.result = false;
        apiResponse.message = result.getCode();
        apiResponse.code = result.getId();
        return apiResponse;
    }

    public static <T> ApiResponse<T> returnFail(String errorMessage) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.result = false;
        apiResponse.message = errorMessage;
        apiResponse.code = ApiResponseEnum.FAIL.getId();
        return apiResponse;
    }

    public static <T> ApiResponse<T> returnFail(T data, ApiResponseEnum result) {
        return returnSuccess(data, result);
    }

    public static <T> ApiResponse<T> returnFail(T data, String errorMessage) {
        return returnSuccess(data, errorMessage);
    }

    public static <T> ApiResponse<T> returnFail(int errorCode, String errorMessage) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.code = errorCode;
        apiResponse.message = errorMessage;
        apiResponse.result = false;
        return apiResponse;
    }

    public static <T> ApiResponse<T> returnSuccess(int errorCode, String errorMessage, T data) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.code = errorCode;
        apiResponse.message = errorMessage;
        apiResponse.result = true;
        apiResponse.setData(data);
        return apiResponse;
    }

    public Long getServerTime() {
        return this.serverTime;
    }

    public void setServerTime(Long serverTime) {
        this.serverTime = Long.valueOf(new Date().getTime());
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
        return (T) this.data;
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

    public void setApiResponseEnum(ApiResponseEnum apiResponseEnum) {
        this.result = apiResponseEnum.success();
        this.code = apiResponseEnum.getId();
        this.message = apiResponseEnum.getCode();
        this.serverTime = Long.valueOf(new Date().getTime());
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.data == null ? 0 : this.data.hashCode());
        result = 31 * result + ((this.message == null) || ("".equals(this.message.trim())) ? 0 : this.message.hashCode());
        result = 31 * result + (this.result ? 1231 : 1237);
        result = 31 * result + this.code;
        result = 31 * result + ("1.0.0" == null ? 0 : "1.0.0".hashCode());
        return result;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        ApiResponse<?> that = (ApiResponse) o;
        if (this.code != that.code) {
            return false;
        }
        if (this.result != that.result) {
            return false;
        }
        if (this.data != null ? !this.data.equals(that.data) : that.data != null) {
            return false;
        }
        if (this.message != null ? !this.message.equals(that.message) : that.message != null) {
            return false;
        }
        return false;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
