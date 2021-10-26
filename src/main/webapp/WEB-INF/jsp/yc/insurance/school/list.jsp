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
<t:datagrid actionUrl="insuranceController/school/datagrid" tableContentId="jqGrid_wrapper" searchGroupId="searchGroupId" fit="true" caption="学校管理" name="table_list_2"  sortName="createDate"  sortOrder="desc">

	<t:dgCol name="id" label="编号" hidden="true" key="true" width="20"></t:dgCol>
	<t:dgCol name="parentId" label="区县名称" width="120" query="true" replace="${areaReplace}" queryId="parentId" valueId="parentId"></t:dgCol>
	<t:dgCol name="name" label="学校名称"  query="true" > </t:dgCol>
	<t:dgCol name="type" label="学校性质" replace="公立学校_0,私立学校_1"></t:dgCol>
	<t:dgCol name="record" label="学历类型" query="true"></t:dgCol>
	<t:dgCol name="deptNo" label="学校编码" width="200" query="true" ></t:dgCol>
	<t:dgCol name="createDate" label="创建时间" width="150"></t:dgCol>
	<t:dgCol name="opt" label="操作" width="150"></t:dgCol>
	<t:dgDelOpt label="删除" url="insuranceController/school/del?id={id}"/>
	<t:dgFunOpt label="年级管理" funName="updateGrad(id)" icon="fa fa-list-ul" />
	<t:dgToolBar url="insuranceController/school/addorupdate" type="add" width="60%" ></t:dgToolBar>
	<t:dgToolBar url="insuranceController/school/addorupdate" type="edit" width="60%" ></t:dgToolBar>
	<t:dgToolBar type="refresh"></t:dgToolBar>
</t:datagrid>
<script type="text/javascript">
	function updateGrad(id){

		window.location = 'insuranceController/school/grade/list?schoolId='+id;
	}
</script>
</body>
</html>