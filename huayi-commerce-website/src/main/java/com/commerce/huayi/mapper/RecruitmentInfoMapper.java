package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.RecruitmentInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RecruitmentInfoMapper extends Mapper<RecruitmentInfo> {
    List<RecruitmentInfo> getRecruitmentInfos();

    void addRecruitmentInfo(@Param("recruitmentInfo") RecruitmentInfo recruitmentInfo);

    void delRecruitmentInfo(@Param("id") int id);

    RecruitmentInfo getRecruitmentInfo(@Param("id") int id);

    void updateRecruitmentInfo(@Param("recruitmentInfo") RecruitmentInfo recruitmentInfo);
}