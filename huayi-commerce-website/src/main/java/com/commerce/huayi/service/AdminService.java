package com.commerce.huayi.service;

import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.api.BusinessException;

public interface AdminService {

    ApiResponse login(String userName,String password) throws BusinessException;
}
