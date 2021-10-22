package com.active4j.hr.yc.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.active4j.hr.core.annotation.QueryField;
import com.active4j.hr.core.query.QueryCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@TableName("yc_area")
@Getter
@Setter
public class YcAreaEntity extends BaseEntity {
    /**
     */
    private static final long serialVersionUID = -3440962564321838838L;
    
    @TableField("AREA_NAME")
    @QueryField(queryColumn="AREA_NAME", condition= QueryCondition.like)
    private String areaName;

    @TableField("AREA_SORT")
    @QueryField(queryColumn="AREA_SORT", condition= QueryCondition.eq)
    private String areaSort;

    @TableField("AREA_TYPE")
    private int areaType;

    @TableField("AREA_STATE")
    @QueryField(queryColumn="AREA_STATE", condition= QueryCondition.eq)
    private int areaState;

    @TableField("AREA_CODE")
    @QueryField(queryColumn="AREA_CODE", condition= QueryCondition.like)
    private String areaCode;

    @TableField("AREA_NOTE")
    @QueryField(queryColumn="AREA_NOTE", condition= QueryCondition.like)
    private String areaNote;

}
