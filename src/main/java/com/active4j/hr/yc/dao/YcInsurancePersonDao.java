package com.active4j.hr.yc.dao;

import com.active4j.hr.yc.entity.HbdModel;
import com.active4j.hr.yc.entity.YcInsurancePersonEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YcInsurancePersonDao extends BaseMapper<YcInsurancePersonEntity> {

    public List<HbdModel> getHbdList(@Param("schoolId") String schoolId);

}
