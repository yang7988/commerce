package com.commerce.huayi.service.impl;

import com.commerce.huayi.api.ApiResponseEnum;
import com.commerce.huayi.api.BusinessException;
import com.commerce.huayi.entity.db.Administrator;
import com.commerce.huayi.entity.request.AdministratorReq;
import com.commerce.huayi.entity.request.DelDataReq;
import com.commerce.huayi.entity.response.AdministratorVo;
import com.commerce.huayi.mapper.AdministratorMapper;
import com.commerce.huayi.service.AdminService;
import com.commerce.huayi.utils.BeanCopyUtil;
import com.commerce.huayi.utils.MD5Tools;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdministratorMapper administratorMapper;

    @Override
    public AdministratorVo login(String userName, String password) throws BusinessException {

        Administrator administrator = administratorMapper.getAdminByLoginName(userName);

        if(null != administrator) {
            if(administrator.getPassword().equals(password)) {
                AdministratorVo administratorVo = new AdministratorVo();
                String token = getUserLoginToken(userName);
                administratorVo.setToken(token);
                administratorVo.setLoginName(userName);
                administratorVo.setMobilePhone(administrator.getMobilePhone());
                administratorVo.setName(administrator.getName());
                administratorVo.setId(administrator.getId());
                administratorVo.setIsDelete(administrator.getIsDelete());
                administratorVo.setStatus(administrator.getStatus());
                return administratorVo;
            } else {
                // 密码错误
                throw new BusinessException(ApiResponseEnum.USERNAME_PASSWORD_ERROR);
            }
        } else {
            // 查找无此有效用户
            throw new BusinessException(ApiResponseEnum.USER_NOT_FOUND);
        }

    }

    @Override
    public void addAdmin(AdministratorReq administratorReq) {
        Administrator administrator = new Administrator();
        administrator.setLoginName(administratorReq.getLoginName());
        administrator.setName(administratorReq.getName());
        administrator.setMobilePhone(administratorReq.getMobilePhone());
        administrator.setPassword(administratorReq.getPassword());
        administratorMapper.addAdmin(administrator);
    }

    @Override
    public void delAdmin(DelDataReq delDataReq) {
        administratorMapper.delAdmin(delDataReq.getId());
    }

    @Override
    public List<AdministratorVo> getAdmin() {
        List<Administrator> lists = administratorMapper.getAdmin();
        if(CollectionUtils.isEmpty(lists)) {
            return null;
        }
        return BeanCopyUtil.copy(AdministratorVo.class, lists);
    }

    @Override
    public boolean loginNameIsUsed(String loginName) {
        return administratorMapper.loginNameIsUsed(loginName) > 0;
    }

    /**
     * 根据规则生成管理员登录token
     * */
    private String getUserLoginToken(String loginName) {
        String result = "";
        StringBuffer token = new StringBuffer();
        String uuid = UUID.randomUUID().toString();
        String timeStamp = String.valueOf(System.currentTimeMillis());
        token = token.append(loginName).append(timeStamp).append(uuid);
        result = MD5Tools.encryptUpperCase(token.toString());
        return result;
    }

}