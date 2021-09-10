package com.active4j.hr.yc.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.active4j.hr.core.annotation.QueryField;
import com.active4j.hr.core.query.QueryCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@TableName("yc_refund")
@Getter
@Setter
public class YcRefundEntity extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -3440862564325838838L;


    @TableField("student_name")
    @QueryField(queryColumn="student_name", condition= QueryCondition.like)
    private String studentName;

    @TableField("student_card")
    @QueryField(queryColumn="student_card", condition= QueryCondition.like)
    private String studentCard;

    @TableField("pay_number")
    @QueryField(queryColumn="pay_number", condition= QueryCondition.like)
    private String payNumber;

    @TableField("pay_money")
    @QueryField(queryColumn="pay_money", condition= QueryCondition.like)
    private String payMoney;

    @TableField("yc_state")
    @QueryField(queryColumn="yc_state", condition= QueryCondition.eq)
    private int state;

    @TableField("content")
    private String content;

    @TableField("tbr_phone")
    private String tbrPhone;
}
