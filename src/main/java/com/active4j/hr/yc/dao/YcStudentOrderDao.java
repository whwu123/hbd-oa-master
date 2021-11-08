package com.active4j.hr.yc.dao;

import com.active4j.hr.yc.entity.YcStudentEntity;
import com.active4j.hr.yc.entity.YcStudentOrderEntity;
import com.active4j.hr.yc.entity.YcStudentOrderModelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface YcStudentOrderDao extends BaseMapper<YcStudentOrderEntity> {


    public YcStudentOrderModelEntity getOrderByStudentCard(@Param("StudenCard")String StudenCard);
}
