package com.commerce.huayi.Interceptor;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.api.ApiResponseEnum;
import com.commerce.huayi.api.BusinessException;
import com.commerce.huayi.service.TranslateService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TranslateService translateService;

    public GlobalExceptionHandler() {
    }

    @SuppressWarnings("unchecked")
    @ExceptionHandler({RuntimeException.class, Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<?> internalExceptionHandler(Exception e) {
        logger.error("internalExceptionHandler: stacktrace={}", ExceptionUtils.getStackTrace(e));
        ApiResponse apiResponse = ApiResponse.returnFail(ApiResponseEnum.INTERNAL_ERROR);
        Object retVl = translateService.translate(apiResponse);
        return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);

    }

    @SuppressWarnings("unchecked")
    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> businessExceptionHandler(BusinessException businessException) {
        ApiResponseEnum apiResponseEnum = businessException.getApiResponseEnum();
        String message = apiResponseEnum != null ? apiResponseEnum.getLabel() : businessException.getMessage();
        logger.error("intercept===BusinessException===info=={}", message);
        Object errorData = businessException.getErrorData();
        Object retVl = translateService.translate(ApiResponse.returnFail(errorData, apiResponseEnum));
        return new ResponseEntity(retVl, HttpStatus.OK);
    }

    @SuppressWarnings("unchecked")
    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<?> exceptionHandler(BindException e) {
        logger.error("internalExceptionHandler: stacktrace={}", ExceptionUtils.getStackTrace(e));
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
        translateService.translate(apiResponse);
        return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);

    }
}