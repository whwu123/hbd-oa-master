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
<t:datagrid actionUrl="insuranceController/person/datagrid" tableContentId="jqGrid_wrapper" searchGroupId="searchGroupId" fit="true" caption="保险公司管理" name="table_list_2"  sortName="createDate"  sortOrder="desc">

	<t:dgCol name="id" label="编号" hidden="true" key="true" width="20"></t:dgCol>

	<t:dgCol name="companyId" label="公司名称" replace="${companyReplace}" query="true"  queryId="companyId" valueId="companyId"></t:dgCol>
	<t:dgCol name="personName" label="姓名" width="200" query="true" ></t:dgCol>
	<t:dgCol name="personSex" label="姓别" replace="女_1,男_0" width="200" query="true" ></t:dgCol>
	<t:dgCol name="personPhone" label="电话号码" width="200" query="true" ></t:dgCol>
	<t:dgCol name="createDate" label="创建时间" width="150"></t:dgCol>
	<t:dgCol name="opt" label="操作" width="150"></t:dgCol>
	<t:dgDelOpt label="删除" url="insuranceController/person/del?id={id}" operationCode="sys:user:del"/>
	<t:dgToolBar url="insuranceController/person/addorupdate" type="add" width="60%" ></t:dgToolBar>
	<t:dgToolBar url="insuranceController/person/addorupdate" type="edit" width="60%" ></t:dgToolBar>
	<t:dgToolBar type="refresh"></t:dgToolBar>
</t:datagrid>
<script type="text/javascript">

</script>
</body>
</html>