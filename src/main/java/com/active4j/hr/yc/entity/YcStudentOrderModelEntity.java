package com.active4j.hr.yc.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.active4j.hr.core.annotation.QueryField;
import com.active4j.hr.core.query.QueryCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YcStudentOrderModelEntity extends BaseEntity {
    private static final long serialVersionUID = -3440962564325838838L;

    @TableField("one")
    private int one;


    @TableField("two")
    private int two;


    @TableField("three")
    private int three;


    @TableField("tfor")
    private int tfor;


    @TableField("payNumber")
    private String payNumber;

    @TableField("studentName")
    private String studentName;

    @TableField("studentCard")
    private String studentCard;

    @TableField("schoolId")
    private String schoolId;

    @TableField("nianjiId")
    private String nianjiId;

    @TableField("banjiId")
    private String banjiId;

    @TableField("parentName")
    private String parentName;

    @TableField("parentCard")
    private String parentCard;

    @TableField("parentPhone")
    private String parentPhone;
}
