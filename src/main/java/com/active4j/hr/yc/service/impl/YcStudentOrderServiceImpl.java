package com.active4j.hr.yc.service.impl;

import com.active4j.hr.yc.dao.YcStudentDao;
import com.active4j.hr.yc.dao.YcStudentOrderDao;
import com.active4j.hr.yc.entity.YcStudentEntity;
import com.active4j.hr.yc.entity.YcStudentOrderEntity;
import com.active4j.hr.yc.entity.YcStudentOrderModelEntity;
import com.active4j.hr.yc.service.YcStudentOrderService;
import com.active4j.hr.yc.service.YcStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("ycStudentOrderService")
@Transactional
public class YcStudentOrderServiceImpl extends ServiceImpl<YcStudentOrderDao, YcStudentOrderEntity> implements YcStudentOrderService{

    @Autowired
    private  YcStudentOrderDao ycStudentOrderDao;
    @Override
    public YcStudentOrderModelEntity getOrderByStudentCard(String studenCard) {
        return ycStudentOrderDao.getOrderByStudentCard(studenCard);
    }
}
