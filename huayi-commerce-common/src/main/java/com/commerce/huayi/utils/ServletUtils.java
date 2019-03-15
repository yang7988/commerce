package com.commerce.huayi.utils;

import com.commerce.huayi.constant.LanguageEnum;
import com.commerce.huayi.constant.RequestHeaderEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Hugo.Wwg
 * @Since 2017-06-07
 */
public class ServletUtils {
    private static final Logger logger = LoggerFactory.getLogger(ServletUtils.class);
    private static final String[] HEADERS_TO_TRY = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"};

    public static String getIpAddress(HttpServletRequest request) {
        String ipHeader = null;
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            if (ip.contains(",")) { // 多次反向代理后会有多个ip值，第一个ip才是真实ip
                ip = ip.split(",")[0];
                ipHeader = ip != null ? "x-forwarded-for" : null;
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            ipHeader = ip != null ? "Proxy-Client-IP" : null;
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            ipHeader = ip != null ? "WL-Proxy-Client-IP" : null;
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            ipHeader = ip != null ? "HTTP_CLIENT_IP" : null;
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            ipHeader = ip != null ? "HTTP_X_FORWARDED_FOR" : null;
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
            ipHeader = ip != null ? "X-Real-IP" : null;
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            ipHeader = ip != null ? "getRemoteAddr()" : null;
        }
        logger.warn("获取客户端ip地址头部为: " + ipHeader + " ip: " + ip);
        return ip;
    }

    /**
     * 获取客户端IP
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return "0:0:0:0:0:0:0:1".equals(request.getRemoteAddr()) ? "127.0.0.1" : request.getRemoteAddr();
    }

    public static String getClientIpAddress() {
        return getClientIpAddress(getHttpServletRequest());
    }


    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getHttpServletResponse() {
        return ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getResponse();
    }

    public static void response(HttpServletResponse response, String content) {
        PrintWriter writer = null;
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            writer = response.getWriter();
            writer.println(content);
        } catch (IOException e) {
        }
    }


    public static Map<String, String> getRequestParameterMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getParameter(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 获得操作系统版本
     *
     * @return
     */
    public static String getOsInfo() {
        HttpServletRequest request = ServletUtils.getHttpServletRequest();
        String userAgent = request.getHeader("User-Agent");
        String os = "UnKnown, More-Info: " + userAgent;
        if (userAgent.toLowerCase().contains("windows")) {
            os = "Windows";
        } else if (userAgent.toLowerCase().contains("mac")) {
            os = "Mac";
        } else if (userAgent.toLowerCase().contains("x11")) {
            os = "Unix";
        } else if (userAgent.toLowerCase().contains("android")) {
            os = "Android";
        } else if (userAgent.toLowerCase().contains("iphone")) {
            os = "IPhone";
        }
        return os;
    }

    public static LanguageEnum language() {
        String language = getHttpServletRequest().getHeader(RequestHeaderEnum.language.name());
        return LanguageEnum.enums(language);
    }
}
