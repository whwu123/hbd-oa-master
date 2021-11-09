package com.active4j.hr.yc.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class HbdModel implements Serializable {

    private static final long serialVersionUID = 7516450772634980274L;

    private String schoolId;

    private String cname;

    private String pphone;

    private String pname;

    private String insuranceType;

    private String grad;

    private String igrad;

}
