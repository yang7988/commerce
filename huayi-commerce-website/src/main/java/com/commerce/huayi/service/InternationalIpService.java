package com.commerce.huayi.service;

import com.commerce.huayi.entity.response.IpAddressDetails;

public interface InternationalIpService {

    IpAddressDetails queryIp(String ip);
}
