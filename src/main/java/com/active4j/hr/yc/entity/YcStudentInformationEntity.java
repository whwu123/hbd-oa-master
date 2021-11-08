package com.active4j.hr.yc.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.active4j.hr.core.annotation.QueryField;
import com.active4j.hr.core.query.QueryCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@TableName("yc_student_information")
@Getter
@Setter
public class YcStudentInformationEntity extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -3440862564325838838L;


    @TableField("AREA_ID")
    @QueryField(queryColumn="AREA_ID", condition= QueryCondition.eq)
    private String areaId;

    @TableField("SCHOOL_ID")
    @QueryField(queryColumn="SCHOOL_ID", condition= QueryCondition.eq)
    private String schoolId;

    @TableField("NIANJI_ID")
    @QueryField(queryColumn="NIANJI_ID", condition= QueryCondition.eq)
    private String nianjiId;

    @TableField("BANJI_ID")
    @QueryField(queryColumn="BANJI_ID", condition= QueryCondition.eq)
    private String banjiId;

    @TableField("STUDENT_NAME")
    @QueryField(queryColumn="STUDENT_NAME", condition= QueryCondition.like)
    private String studentName;

    @TableField("STUDENT_CARD")
    @QueryField(queryColumn="STUDENT_CARD", condition= QueryCondition.eq)
    private String studentCard;

    @TableField("PARENT_NAME")
    @QueryField(queryColumn="PARENT_NAME", condition= QueryCondition.like)
    private String parentName;

    @TableField("PARENT_PHONE")
    @QueryField(queryColumn="PARENT_PHONE", condition= QueryCondition.eq)
    private String parentPhone;

    @TableField("PARENT_CARD")
    @QueryField(queryColumn="PARENT_CARD", condition= QueryCondition.eq)
    private String parentCard;

    @TableField("INFORMATION_STATE")
    @QueryField(queryColumn="INFORMATION_STATE", condition= QueryCondition.eq)
    private int informationState;

}
