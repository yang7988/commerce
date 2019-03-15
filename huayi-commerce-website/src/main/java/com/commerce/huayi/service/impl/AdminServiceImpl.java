package com.commerce.huayi.service.impl;

import com.commerce.huayi.annotation.Translate;
import com.commerce.huayi.api.ApiResponse;
import com.commerce.huayi.api.BusinessException;
import com.commerce.huayi.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Override
    public ApiResponse login(String userName, String password) throws BusinessException {
        return null;
    }
}