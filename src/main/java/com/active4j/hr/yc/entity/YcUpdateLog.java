package com.active4j.hr.yc.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.active4j.hr.core.annotation.QueryField;
import com.active4j.hr.core.query.QueryCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName("yc_update_log")
@Getter
@Setter
public class YcUpdateLog extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -3440862564325838838L;


    @TableField("quxian_department")
    @QueryField(queryColumn="quxian_department", condition= QueryCondition.like)
    private String quxianDepartment;

    @TableField("student_name")
    @QueryField(queryColumn="student_name", condition= QueryCondition.eq)
    private String studentName;

    @TableField("student_card")
    @QueryField(queryColumn="student_card", condition= QueryCondition.like)
    private String studentCard;

    @TableField("student_school")
    @QueryField(queryColumn="student_school", condition= QueryCondition.like)
    private String studentSchool;

    @TableField("jiazheng_phone")
    @QueryField(queryColumn="jiazheng_phone", condition= QueryCondition.like)
    private String jiazhengPhone;

    @TableField("flag_pay")
    @QueryField(queryColumn="flag_pay", condition= QueryCondition.like)
    private String flagPay;

    @TableField("memo_one")
    @QueryField(queryColumn="memo_one", condition= QueryCondition.like)
    private String memoOne;

    @TableField("memo_two")
    @QueryField(queryColumn="memo_two", condition= QueryCondition.like)
    private String memoTwo;

    @TableField("memo_three")
    @QueryField(queryColumn="memo_three", condition= QueryCondition.like)
    private String memoThree;

    @TableField("yc_state")
    @QueryField(queryColumn="yc_state", condition= QueryCondition.eq)
    private String state;

    @TableField("student_id")
    @QueryField(queryColumn="student_id", condition= QueryCondition.eq)
    private String studentId;
}
