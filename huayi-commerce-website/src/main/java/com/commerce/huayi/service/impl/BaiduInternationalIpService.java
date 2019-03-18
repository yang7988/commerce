package com.commerce.huayi.service.impl;

import com.alibaba.fastjson.JSON;
import com.commerce.huayi.entity.response.IpAddressDetails;
import com.commerce.huayi.service.InternationalIpService;
import com.commerce.huayi.entity.response.IpAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service("baiduIpService")
public class BaiduInternationalIpService implements InternationalIpService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaiduInternationalIpService.class);
    //百度获取ip地址详情接口
    private static String httpUrl = "http://apis.baidu.com/apistore/iplookup/iplookup_paid?ip=";
    //接口密钥
    private static final String apiKey = "a152c631b149ab6420e37d25fb1faafa";

    @Override
    public IpAddressDetails queryIp(String ip) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf;
        String urlHttp = httpUrl + ip;
        urlHttp.replace(" ", "");
        URL url;
        HttpURLConnection connection = null;
        InputStream is = null;
        IpAddress js;
        try {
            url = new URL(urlHttp);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            sbf = new StringBuffer();
            connection.setRequestProperty("apikey", apiKey);
            connection.connect();
            is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String strRead;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            result = sbf.toString();
        } catch (Exception e) {
            LOGGER.error("BaiduInternationalIpService===queryIp===error", e);
        } finally {
            colseResources(is,reader,connection);
        }
        try {
            js = JSON.parseObject(result, IpAddress.class);
        } catch (Exception e) {
            LOGGER.error("BaiduInternationalIpService===queryIp===jsonParse==error", e);
            return null;
        }
        if (js == null || js.getErrNum() != 0 || !js.getErrMsg().equals("success")) {
            return null;
        }
        IpAddressDetails ipAddressDetails = js.getRetData();
        if(ipAddressDetails == null) {
            return null;
        }
        if ("None".equalsIgnoreCase(ipAddressDetails.getCountry())) {
            ipAddressDetails.setCountry("");
        }
        if ("None".equalsIgnoreCase(ipAddressDetails.getProvince())) {
            ipAddressDetails.setProvince("");
        }
        if ("None".equalsIgnoreCase(ipAddressDetails.getCity())) {
            ipAddressDetails.setCity("");
        }
        return ipAddressDetails;
    }

    private void colseResources(InputStream is, BufferedReader reader, HttpURLConnection connection) {
        try {
            if (is != null) {
                is.close();
            }
            if (reader != null) {
                reader.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        } catch (Exception e) {
            LOGGER.error("BaiduInternationalIpService===queryIp===colseResources==error", e);
        }
    }
}