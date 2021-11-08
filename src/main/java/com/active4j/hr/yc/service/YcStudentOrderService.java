package com.active4j.hr.yc.service;

import com.active4j.hr.yc.entity.YcStudentEntity;
import com.active4j.hr.yc.entity.YcStudentOrderEntity;
import com.active4j.hr.yc.entity.YcStudentOrderModelEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

public interface YcStudentOrderService extends IService<YcStudentOrderEntity>{

    public YcStudentOrderModelEntity getOrderByStudentCard(String StudenCard);
}
