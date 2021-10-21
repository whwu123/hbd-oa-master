package com.active4j.hr.yc.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.active4j.hr.core.annotation.QueryField;
import com.active4j.hr.core.query.QueryCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName("yc_school")
@Getter
@Setter
public class YcSchoolEntity extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -3440962564325838838L;

    @TableField("SCHOOL_AREA_ID")
    @QueryField(queryColumn="SCHOOL_AREA_ID", condition= QueryCondition.eq)
    private String schoolAreaId;

    @TableField("SCHOOL_NAME")
    @QueryField(queryColumn="SCHOOL_NAME", condition= QueryCondition.like)
    private String schoolName;

    @TableField("SCHOOL_TYPE")
    @QueryField(queryColumn="SCHOOL_TYPE", condition= QueryCondition.eq)
    private String schoolType;

    @TableField("SCHOOL_CODE")
    @QueryField(queryColumn="SCHOOL_CODE", condition= QueryCondition.like)
    private String schoolCode;

    @TableField("SCHOOL_SORT")
    private int schoolSort;

    @TableField("SCHOOL_PUBLIC")
    @QueryField(queryColumn="SCHOOL_PUBLIC", condition= QueryCondition.eq)
    private String schoolPublic;

    @TableField("SCHOOL_HEAR_NAME")
    @QueryField(queryColumn="SCHOOL_HEAR_NAME", condition= QueryCondition.like)
    private String schoolHearName;

    @TableField("SCHOOL_HEAR_PHONE")
    @QueryField(queryColumn="SCHOOL_HEAR_PHONE", condition= QueryCondition.like)
    private String schoolHrarPhone;

    @TableField("SCHOOL_NOTE")
    @QueryField(queryColumn="SCHOOL_NOTE", condition= QueryCondition.like)
    private String schoolNote;

    @TableField("INSURANCE_PERSON_ID")
    @QueryField(queryColumn="INSURANCE_PERSON_ID", condition= QueryCondition.eq)
    private String insurancePersonId;
}
