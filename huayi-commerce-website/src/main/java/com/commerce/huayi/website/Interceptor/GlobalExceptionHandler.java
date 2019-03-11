package com.commerce.huayi.website.Interceptor;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.api.ApiResponseEnum;
import com.commerce.huayi.api.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler() {
    }

    @SuppressWarnings("unchecked")
    @ExceptionHandler({BusinessException.class, RuntimeException.class, Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<?> internalExceptionHandler(Exception e) {
        logger.error("internalExceptionHandler: stacktrace={}", e);
        ApiResponseEnum apiResponseEnum = e instanceof BusinessException ? ((BusinessException) e).getApiResponseEnum() : null;
        ApiResponseEnum responseEnum = apiResponseEnum == null ? ApiResponseEnum.INTERNAL_ERROR : apiResponseEnum;
        ApiResponse apiResponse = ApiResponse.returnFail(responseEnum);
        return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);

    }
}