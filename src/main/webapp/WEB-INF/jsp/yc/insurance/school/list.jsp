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
<t:datagrid actionUrl="insuranceController/school/datagrid" tableContentId="jqGrid_wrapper" searchGroupId="searchGroupId" fit="true" caption="保险公司管理" name="table_list_2"  sortName="createDate"  sortOrder="desc">

	<t:dgCol name="id" label="编号" hidden="true" key="true" width="20"></t:dgCol>
	<t:dgCol name="schoolAreaId" label="所属区县" width="120" query="true" replace="${areaReplace}" queryId="schoolAreaId" valueId="schoolAreaId"></t:dgCol>
	<t:dgCol name="name" label="学校名称"  query="true" > </t:dgCol>
	<t:dgCol name="type" label="学校类型" ></t:dgCol>
	<t:dgCol name="deptNo" label="学校编码" width="200" query="true" ></t:dgCol>
	<t:dgCol name="createDate" label="创建时间" width="150"></t:dgCol>

	<t:dgCol name="opt" label="操作" width="150"></t:dgCol>
	<t:dgDelOpt label="删除" url="insuranceController/school/insured/del?id={id}"/>
	<t:dgToolBar url="insuranceController/school/insured/addorupdate" type="add" width="60%" ></t:dgToolBar>
	<t:dgToolBar url="insuranceController/school/insured/addorupdate" type="edit" width="60%" ></t:dgToolBar>
	<t:dgToolBar type="refresh"></t:dgToolBar>
</t:datagrid>
<script type="text/javascript">

</script>
</body>
</html>