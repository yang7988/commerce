package com.commerce.huayi.filter;

import com.alibaba.fastjson.JSON;
import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.api.ApiResponseEnum;
import com.commerce.huayi.constant.LanguageEnum;
import com.commerce.huayi.constant.RequestHeaderEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class GenericFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericFilter.class);


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
        if (!request.getRequestURI().startsWith("/api")) {
            return ApiResponseEnum.SUCCESS;
        }
        if (request.getRequestURI().contains("/image")) {
            return ApiResponseEnum.SUCCESS;
        }
        String language = request.getHeader(RequestHeaderEnum.language.name());
        if (StringUtils.isBlank(language)) {
            language = "chinese";
            LOGGER.error("requestURI===" + request.getRequestURI() + "======缺少请求头部参数language");
            //return ApiResponseEnum.ABSENCE_LANGUAGE_PARAM;
        }
        List<String> list = Stream.of(LanguageEnum.values()).map(LanguageEnum::getLanguage).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(list)) {
            return ApiResponseEnum.LANGUAGE_PARAM_ILLEGAL;
        }
        if (!list.contains(language)) {
            return ApiResponseEnum.LANGUAGE_PARAM_ILLEGAL;
        }
        request.setAttribute("language", language);
        String contentType = request.getHeader("Content-Type");
        contentType = "application/json";
        if (StringUtils.isBlank(contentType) || !contentType.startsWith("application/json")) {
            LOGGER.error("requestURI===" + request.getRequestURI() + "======Content-type:===" + contentType + "is not application/json");
            return ApiResponseEnum.CONTENT_TYPE_ILLEGAL;
        }
        return ApiResponseEnum.SUCCESS;
    }

    private void responseError(ApiResponseEnum apiResponseEnum, ServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ApiResponse apiResponse = ApiResponse.returnFail(apiResponseEnum);
        response.getWriter().write(JSON.toJSONString(apiResponse));
    }
}