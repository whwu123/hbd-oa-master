package com.active4j.hr.yc.service.impl;

import com.active4j.hr.yc.dao.YcAreaDao;
import com.active4j.hr.yc.entity.YcAreaEntity;
import com.active4j.hr.yc.entity.YcRefundEntity;
import com.active4j.hr.yc.service.YcAreaService;
import com.active4j.hr.yc.service.YcRefunService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("YcAreaService")
@Transactional
public class YcAreaServiceImpl extends ServiceImpl<YcAreaDao, YcAreaEntity> implements YcAreaService {

}
