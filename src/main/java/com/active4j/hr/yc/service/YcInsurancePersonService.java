package com.active4j.hr.yc.service;

import com.active4j.hr.yc.entity.HbdModel;
import com.active4j.hr.yc.entity.YcInsurancePersonEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YcInsurancePersonService extends IService<YcInsurancePersonEntity>{
    public List<HbdModel> getHbdList(String schoolId);
}
