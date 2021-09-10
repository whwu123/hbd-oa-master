package com.active4j.hr.yc.service.impl;

import com.active4j.hr.yc.dao.YcRefundDao;
import com.active4j.hr.yc.dao.YcStudentDao;
import com.active4j.hr.yc.entity.YcRefundEntity;
import com.active4j.hr.yc.entity.YcStudentEntity;
import com.active4j.hr.yc.service.YcRefunService;
import com.active4j.hr.yc.service.YcStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("YcRefunService")
@Transactional
public class YcRefunServiceImpl extends ServiceImpl<YcRefundDao, YcRefundEntity> implements YcRefunService {

}
