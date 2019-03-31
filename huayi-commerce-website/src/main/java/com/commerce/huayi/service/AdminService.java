package com.commerce.huayi.service;

import com.commerce.huayi.api.BusinessException;
import com.commerce.huayi.entity.request.AdministratorReq;
import com.commerce.huayi.entity.request.DelDataReq;
import com.commerce.huayi.entity.response.AdministratorVo;

import java.util.List;

public interface AdminService {

    AdministratorVo login(String userName,String password) throws BusinessException;

    void addAdmin(AdministratorReq administratorReq);

    void delAdmin(DelDataReq delDataReq);

    List<AdministratorVo> getAdmin();

    boolean loginNameIsUsed(String loginName);

}
