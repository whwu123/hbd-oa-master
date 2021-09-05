package com.active4j.hr.yc.dao;


import com.active4j.hr.yc.entity.YcStudentModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface YcStudentModelDao extends BaseMapper<YcStudentModel> {



    public void getUuidUpdateState(@Param("state")Integer state, @Param("uuidmodel")String uuidmodel);
}
