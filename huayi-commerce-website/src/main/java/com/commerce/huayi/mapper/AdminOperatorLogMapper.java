package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.AdminOperatorLog;
import com.commerce.huayi.entity.db.AdminOperatorLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminOperatorLogMapper {
    int countByExample(AdminOperatorLogExample example);

    int deleteByExample(AdminOperatorLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminOperatorLog record);

    int insertSelective(AdminOperatorLog record);

    List<AdminOperatorLog> selectByExampleWithBLOBs(AdminOperatorLogExample example);

    List<AdminOperatorLog> selectByExample(AdminOperatorLogExample example);

    AdminOperatorLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminOperatorLog record, @Param("example") AdminOperatorLogExample example);

    int updateByExampleWithBLOBs(@Param("record") AdminOperatorLog record, @Param("example") AdminOperatorLogExample example);

    int updateByExample(@Param("record") AdminOperatorLog record, @Param("example") AdminOperatorLogExample example);

    int updateByPrimaryKeySelective(AdminOperatorLog record);

    int updateByPrimaryKeyWithBLOBs(AdminOperatorLog record);

    int updateByPrimaryKey(AdminOperatorLog record);
}