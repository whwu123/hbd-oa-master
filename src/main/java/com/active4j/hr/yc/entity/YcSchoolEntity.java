package com.active4j.hr.yc.entity;

import com.active4j.hr.common.entity.BaseEntity;
import com.active4j.hr.core.annotation.QueryField;
import com.active4j.hr.core.query.QueryCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @title SysDeptEntity.java
 * @description 
		  系统管理   部门管理
 * @time  2020年1月15日 上午10:48:20
 * @author 麻木神
 * @version 1.0
*/
@TableName("yc_school")
@Getter
@Setter
public class YcSchoolEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3540862564325838898L;

	/**
	 * 学校编号
	 */
	@TableField("DEPT_NO")
	@QueryField(queryColumn="DEPT_NO", condition= QueryCondition.like)
	private String deptNo;
	
	/**
	 * 名称  全称
	 */
	@TableField("NAME")
	@QueryField(queryColumn="NAME", condition= QueryCondition.like)
	private String name;
	
	/**
	 * 类型  0公立      1： 私立
	 */
	@TableField("TYPE")
	@QueryField(queryColumn="TYPE", condition= QueryCondition.eq)
	private String type;
	
	/**
	 * 上级ID
	 */
	@TableField("PARENT_ID")
	@QueryField(queryColumn="PARENT_ID", condition= QueryCondition.eq)
	private String parentId;
	
	/**
	 * 排序
	 */
	@TableField("LEVEL")
	@QueryField(queryColumn="LEVEL", condition= QueryCondition.eq)
	private int level;
	
	/**
	 * 描述
	 */
	@TableField("DESCRIPTION")
	private String description;
	
	/**
	 * 备注
	 */
	@TableField("MEMO")
	private String memo;

	@TableField("STATE")
	@QueryField(queryColumn="STATE", condition= QueryCondition.eq)
	private int state;

	/**
	 * 学历
	 */
	@TableField("RECORD")
	private String record;

	@TableField("SORT")
	private String sort;
}

