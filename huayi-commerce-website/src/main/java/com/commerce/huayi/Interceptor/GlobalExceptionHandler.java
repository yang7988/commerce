package com.commerce.huayi.Interceptor;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.api.ApiResponseEnum;
import com.commerce.huayi.api.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
        if(e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            ApiResponseEnum apiResponseEnum = businessException.getApiResponseEnum();
            Object errorData = businessException.getErrorData();
            return new ResponseEntity(ApiResponse.returnFail(errorData,apiResponseEnum), HttpStatus.BAD_REQUEST);
        }
        ApiResponse apiResponse = ApiResponse.returnFail(ApiResponseEnum.INTERNAL_ERROR);
        return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);

    }

    @SuppressWarnings("unchecked")
    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<?> exceptionHandler(BindException e) {
        logger.error("internalExceptionHandler: stacktrace={}", e);
        ApiResponse apiResponse;
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult != null && bindingResult.hasFieldErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String fieldName = fieldError.getField();
            String errorMsg = "[" + fieldName + "]" + fieldError.getDefaultMessage();
            apiResponse = ApiResponse.returnFail(ApiResponseEnum.PARAMETER_INVALID, errorMsg);
        } else {
            apiResponse = ApiResponse.returnFail(ApiResponseEnum.INTERNAL_ERROR, e.getMessage());
        }
        return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);

    }
}