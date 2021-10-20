package com.active4j.hr.yc.service.impl;

import com.active4j.hr.yc.dao.YcInsurancePersonDao;
import com.active4j.hr.yc.entity.YcInsurancePersonEntity;
import com.active4j.hr.yc.service.YcInsurancePersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("YcInsurancePersonService")
@Transactional
public class YcInsurancePersonServiceImpl extends ServiceImpl<YcInsurancePersonDao, YcInsurancePersonEntity> implements YcInsurancePersonService {

}
