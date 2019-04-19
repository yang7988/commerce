package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.Administrator;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdministratorMapper {
    void delAdmin(@Param("id") int id);

    void addAdmin(@Param("administrator") Administrator administrator);

    List<Administrator> getAdmin();

    int loginNameIsUsed(@Param("loginName") String loginName);

    Administrator getAdminByLoginName(@Param("loginName") String loginName);
}