package com.active4j.hr.yc.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.active4j.hr.core.annotation.QueryField;
import com.active4j.hr.core.query.QueryCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

public class PaymentRecordModel extends BaseEntity {


    private String shiDepartment;


    private String quxianDepartment;


    private String studentName;


    private String studentCard;


    private String studentSchool;


    private String studentNianji;


    private String studentBanji;


    private String casName;


    private String payMoney;


    private String type;


    private String baofeiMoney;


    private String zhifuNumber;


    private String supplier;


    private String toubaorenName;


    private String toubaorenPhone;


    private String mome;
}
