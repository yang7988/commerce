package com.commerce.huayi.filter;

import com.alibaba.fastjson.JSON;
import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.api.ApiResponseEnum;
import com.commerce.huayi.constant.LanguageEnum;
import com.commerce.huayi.constant.RequestHeaderEnum;
import com.commerce.huayi.service.TranslateService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class GenericFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericFilter.class);

    @Autowired
    private TranslateService translateService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ApiResponseEnum responseEnum = validate(request, response);
        if (responseEnum == ApiResponseEnum.SUCCESS) {
            filterChain.doFilter(request, response);
        } else {
            responseError(responseEnum, response);
        }

    }

    @Override
    public void destroy() {

    }

    private ApiResponseEnum validate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(!request.getRequestURI().startsWith("/api")) {
            return ApiResponseEnum.SUCCESS;
        }
        String language = request.getHeader(RequestHeaderEnum.language.name());
        if (StringUtils.isBlank(language)) {
            LOGGER.error("requestURI===" + request.getRequestURI() + "======缺少请求头部参数language");
            return ApiResponseEnum.ABSENCE_LANGUAGE_PARAM;
        } else if (LanguageEnum.enums(language) == null) {
            return ApiResponseEnum.LANGUAGE_PARAM_ILLEGAL;
        } else {
            request.setAttribute("language", language);
        }
        String contentType = request.getHeader("Content-Type");
        if (StringUtils.isBlank(contentType) || !contentType.startsWith("application/json")) {
            LOGGER.error("requestURI===" + request.getRequestURI() + "======Content-type:===" + contentType + "is not application/json");
            return ApiResponseEnum.CONTENT_TYPE_ILLEGAL;
        }
        return ApiResponseEnum.SUCCESS;
    }

    private void responseError(ApiResponseEnum apiResponseEnum, ServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ApiResponse apiResponse = ApiResponse.returnFail(apiResponseEnum);
        Object translate = translateService.translate(apiResponse);
        response.getWriter().write(JSON.toJSONString(translate));
    }
}