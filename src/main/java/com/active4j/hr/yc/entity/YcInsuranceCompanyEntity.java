package com.active4j.hr.yc.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.active4j.hr.core.annotation.QueryField;
import com.active4j.hr.core.query.QueryCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName("yc_insurance_company")
@Getter
@Setter
public class YcInsuranceCompanyEntity extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -3440862564325839838L;


    @TableField("COMPANY_NAME")
    @QueryField(queryColumn="COMPANY_NAME", condition= QueryCondition.like)
    private String companyName;

    @TableField("COMPANY_TYPE")
    @QueryField(queryColumn="COMPANY_TYPE", condition= QueryCondition.eq)
    private String companyType;

    @TableField("COMPANY_SORT")
    @QueryField(queryColumn="COMPANY_SORT", condition= QueryCondition.eq)
    private String companySort;

    @TableField("COMPANY_STATE")
    @QueryField(queryColumn="COMPANY_STATE", condition= QueryCondition.eq)
    private int companyState;

    @TableField("COMPANY_NOTE")
    @QueryField(queryColumn="COMPANY_NOTE", condition= QueryCondition.eq)
    private String companyNote;

}
