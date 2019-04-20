package com.commerce.huayi.api;

public class BusinessException extends RuntimeException {
    private ApiResponseEnum apiResponseEnum;
    private String erroeMessage;

    public BusinessException(RuntimeException e) {
        super(e);
    }


    public BusinessException(ApiResponseEnum apiResponseEnum, String erroeMessage) {
        this.apiResponseEnum = apiResponseEnum;
        this.apiResponseEnum.setLabel(erroeMessage);
    }

    public BusinessException(ApiResponseEnum apiResponseEnum) {
        super(apiResponseEnum.getLabel());
        this.apiResponseEnum = apiResponseEnum;
    }

    public ApiResponseEnum getApiResponseEnum() {
        return this.apiResponseEnum;
    }

    public String getErroeMessage() {
        return erroeMessage;
    }
}
