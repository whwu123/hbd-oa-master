package com.active4j.hr.yc.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.active4j.hr.core.annotation.QueryField;
import com.active4j.hr.core.query.QueryCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName("yc_insurance_person")
@Getter
@Setter
public class YcInsurancePersonEntity extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -3440862564325439838L;


    @TableField("PERSON_NAME")
    @QueryField(queryColumn="PERSON_NAME", condition= QueryCondition.like)
    private String personName;

    @TableField("PERSON_PHONE")
    @QueryField(queryColumn="PERSON_PHONE", condition= QueryCondition.eq)
    private String personPhone;

    @TableField("PERSON_SEX")
    @QueryField(queryColumn="PERSON_SEX", condition= QueryCondition.eq)
    private String personSex;

    @TableField("PERSON_STATE")
    @QueryField(queryColumn="PERSON_STATE", condition= QueryCondition.eq)
    private int personState;

    @TableField("PERSON_NOTE")
    @QueryField(queryColumn="PERSON_NOTE", condition= QueryCondition.eq)
    private String personNote;

}
