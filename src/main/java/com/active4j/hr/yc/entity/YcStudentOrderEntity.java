package com.active4j.hr.yc.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.active4j.hr.core.annotation.QueryField;
import com.active4j.hr.core.query.QueryCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName("yc_student_order")
@Getter
@Setter
public class YcStudentOrderEntity extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -3440962564325838838L;

    @TableField("STUDENT_ID")
    @QueryField(queryColumn="STUDENT_ID", condition= QueryCondition.eq)
    private String studentId;

    /**
     * 监护人险
     */
    @TableField("TYPE_ONE")
    private int typeOne;

    /**
     * 交通意外险
     */
    @TableField("TYPE_TWO")
    private int typeTwo;

    /**
     * 学平险
     */
    @TableField("TYPE_THREE")
    private int typeThree;

    /**
     * 备用险种
     */
    @TableField("TYPE_FOR")
    private int typeFor;

    @TableField("STUDENT_ORDER_STATE")
    private int studentOrderState;

    @TableField("PAY_NUMBER")
    @QueryField(queryColumn="PAY_NUMBER", condition= QueryCondition.eq)
    private String payNumber;

    @TableField("ORDER_FLAG")
    @QueryField(queryColumn="ORDER_FLAG", condition= QueryCondition.eq)
    private int orderFlag;
}
