package com.active4j.hr.yc.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.active4j.hr.core.annotation.QueryField;
import com.active4j.hr.core.query.QueryCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName("yc_student_model")
@Setter
@Getter
public class YcStudentModel extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -3440862564325848838L;

    @TableField("shi_department")
    private String shiDepartment;

    @TableField("quxian_department")
    private String quxianDepartment;

    @TableField("student_name")
    private String studentName;

    @TableField("student_card")
    private String studentCard;

    @TableField("student_school")
    private String studentSchool;

    @TableField("student_nianji")
    private String studentNianji;

    @TableField("student_banji")
    private String studentBanji;

    @TableField("state")
    private int state;

    @TableField("model_uuid")
    private String modelUUid;


}
