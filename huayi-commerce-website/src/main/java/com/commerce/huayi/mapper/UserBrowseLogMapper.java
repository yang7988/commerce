package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.UserBrowseLog;
import com.commerce.huayi.entity.db.UserBrowseLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserBrowseLogMapper {
    int countByExample(UserBrowseLogExample example);

    int deleteByExample(UserBrowseLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserBrowseLog record);

    int insertSelective(UserBrowseLog record);

    List<UserBrowseLog> selectByExample(UserBrowseLogExample example);

    UserBrowseLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserBrowseLog record, @Param("example") UserBrowseLogExample example);

    int updateByExample(@Param("record") UserBrowseLog record, @Param("example") UserBrowseLogExample example);

    int updateByPrimaryKeySelective(UserBrowseLog record);

    int updateByPrimaryKey(UserBrowseLog record);
}