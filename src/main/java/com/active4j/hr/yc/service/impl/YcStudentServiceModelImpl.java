package com.active4j.hr.yc.service.impl;

import com.active4j.hr.yc.dao.YcStudentDao;
import com.active4j.hr.yc.dao.YcStudentModelDao;
import com.active4j.hr.yc.entity.YcStudentEntity;
import com.active4j.hr.yc.entity.YcStudentModel;
import com.active4j.hr.yc.service.YcStudentModelService;
import com.active4j.hr.yc.service.YcStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("ycStudentmodelService")
@Transactional
public class YcStudentServiceModelImpl extends ServiceImpl<YcStudentModelDao, YcStudentModel> implements YcStudentModelService {

}
