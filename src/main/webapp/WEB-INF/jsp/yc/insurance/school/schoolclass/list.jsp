<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<t:base type="default,select2,jqgrid"></t:base>
</head>
<body class="gray-bg">
<!-- 页面部分 -->
<div class="wrapper wrapper-content animated fadeInRight">
	<div class="row">
		<div class="col-sm-12">
			<div class="row">
				<div class="col-sm-12" id="searchGroupId">
				</div>
			</div>
			<div class="ibox">
				<div class="ibox-content">
					<div id="jqGrid_wrapper" class="jqGrid_wrapper"></div>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- 脚本部分 -->
<t:datagrid actionUrl="insuranceController/school/class/datagrid" tableContentId="jqGrid_wrapper" searchGroupId="searchGroupId" fit="true" caption="班级管理" name="table_list_2"  sortName="createDate"  sortOrder="desc">

	<t:dgCol name="id" label="编号" hidden="true" key="true" width="20"></t:dgCol>
	<t:dgCol name="parentId" label="年级名称"  defval="${gradeId}" query="true" replace="${gradeStr}" queryId="parentId" valueId="parentId"/>
	<t:dgCol name="name" label="班级名称" width="200" query="true"></t:dgCol>
	<t:dgCol name="sort" label="排序显示" width="150"></t:dgCol>
	<t:dgCol name="createDate" label="创建时间" width="150"></t:dgCol>
	<t:dgCol name="opt" label="操作" width="150"></t:dgCol>
	<t:dgDelOpt label="删除" url="insuranceController/school/del?id={id}"/>
	<t:dgToolBar url="insuranceController/school/class/addorupdate" params="parentId" type="add" width="40%" height="40%" ></t:dgToolBar>
	<t:dgToolBar url="insuranceController/school/class/addorupdate" type="edit" width="60%" ></t:dgToolBar>
	<t:dgToolBar type="refresh"></t:dgToolBar>
</t:datagrid>
</body>
</html>