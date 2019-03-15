package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.AdminLoginRecord;
import com.commerce.huayi.entity.db.AdminLoginRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminLoginRecordMapper {
    int countByExample(AdminLoginRecordExample example);

    int deleteByExample(AdminLoginRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminLoginRecord record);

    int insertSelective(AdminLoginRecord record);

    List<AdminLoginRecord> selectByExample(AdminLoginRecordExample example);

    AdminLoginRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminLoginRecord record, @Param("example") AdminLoginRecordExample example);

    int updateByExample(@Param("record") AdminLoginRecord record, @Param("example") AdminLoginRecordExample example);

    int updateByPrimaryKeySelective(AdminLoginRecord record);

    int updateByPrimaryKey(AdminLoginRecord record);
}