package com.active4j.hr.yc.service.impl;

import com.active4j.hr.yc.dao.YcInsuranceCompanyDao;
import com.active4j.hr.yc.dao.YcRefundDao;
import com.active4j.hr.yc.entity.YcInsuranceCompanyEntity;
import com.active4j.hr.yc.entity.YcRefundEntity;
import com.active4j.hr.yc.service.YcInsuranceCompanyService;
import com.active4j.hr.yc.service.YcRefunService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("YcInsuranceCompanyService")
@Transactional
public class YcInsuranceCompanyServiceImpl extends ServiceImpl<YcInsuranceCompanyDao, YcInsuranceCompanyEntity> implements YcInsuranceCompanyService {

}
