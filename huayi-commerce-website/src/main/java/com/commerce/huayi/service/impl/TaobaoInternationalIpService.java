package com.commerce.huayi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.commerce.huayi.entity.response.IpAddressDetails;
import com.commerce.huayi.service.InternationalIpService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service("taobaoIpService")
public class TaobaoInternationalIpService implements InternationalIpService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaobaoInternationalIpService.class);
    private static String taobaoUrl = "http://ip.taobao.com/service/getIpInfo.php?ip=";

    @Override
    public IpAddressDetails queryIp(String ip) {
        if (StringUtils.isBlank(ip)) {
            return null;
        }
        ip = ip.trim();
        String httpUrl = taobaoUrl.concat(ip);
        RestTemplate restTemplate = new RestTemplate();
        String json;
        try {
            json = restTemplate.getForObject(httpUrl, String.class);
        } catch (RestClientException e) {
            LOGGER.error("获取ip国家省份城市出错,ip= " + ip, e);
            return null;
        }
        if (StringUtils.isBlank(json)) {
            LOGGER.warn("获取ip国家省份城市出错,ip= " + ip);
            return null;
        }
        LOGGER.warn("==========获取ip国家省份城市json返回: url: " + httpUrl + " json: " + json);
        JSONObject jsonObject = JSONObject.parseObject(json);
        Object data = jsonObject.get("data");
        if (data == null) {
            LOGGER.error("=======获取ip国家省份城市 data为null: url: " + httpUrl);
            return null;
        }
        JSONObject object = (JSONObject) data;
        IpAddressDetails ipAddressDetails = new IpAddressDetails();
        Object countryObj = object.get("country");
        Object regionObj = object.get("region");
        Object cityObj = object.get("city");
        // 将获取到的国家省份城市等信息解析为字符串
        String country = countryObj == null || ((String) countryObj).contains("XX") ? "" : (String) countryObj;
        String region = regionObj == null || ((String) regionObj).contains("XX") ? "" : (String) regionObj;
        String city = cityObj == null || ((String) cityObj).contains("XX") || ((String) cityObj).contains("内网") ? "" : (String) cityObj;
        ipAddressDetails.setCountry(country);
        ipAddressDetails.setProvince(region);
        ipAddressDetails.setCity(city);
        LOGGER.warn("==========获取ip国家省份城市成功: url: " + httpUrl + " ip: " + ip + " ipAddressDetails: " + ipAddressDetails);
        return ipAddressDetails;
    }
}