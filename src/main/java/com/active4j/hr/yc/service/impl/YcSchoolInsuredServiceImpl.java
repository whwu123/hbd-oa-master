package com.active4j.hr.yc.service.impl;

import com.active4j.hr.yc.dao.YcSchoolInsuredDao;
import com.active4j.hr.yc.entity.YcSchoolInsuredEntity;
import com.active4j.hr.yc.service.YcSchoolInsuredService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("YcSchoolService")
@Transactional
public class YcSchoolInsuredServiceImpl extends ServiceImpl<YcSchoolInsuredDao, YcSchoolInsuredEntity> implements YcSchoolInsuredService {

}
