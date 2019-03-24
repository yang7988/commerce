package com.commerce.huayi.mapper;

import com.commerce.huayi.entity.db.RecruitmentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecruitmentInfoMapper {

    List<RecruitmentInfo> getRecruitmentInfos();

    void addRecruitmentInfo(@Param("recruitmentInfo") RecruitmentInfo recruitmentInfo);

    void delRecruitmentInfo(@Param("id") int id);

}
