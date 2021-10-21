package com.active4j.hr.yc.service.impl;

import com.active4j.hr.yc.dao.YcAreaDao;
import com.active4j.hr.yc.dao.YcSchoolDao;
import com.active4j.hr.yc.entity.YcAreaEntity;
import com.active4j.hr.yc.entity.YcSchoolEntity;
import com.active4j.hr.yc.service.YcAreaService;
import com.active4j.hr.yc.service.YcSchoolService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("YcSchoolService")
@Transactional
public class YcSchoolServiceImpl extends ServiceImpl<YcSchoolDao, YcSchoolEntity> implements YcSchoolService {

}
