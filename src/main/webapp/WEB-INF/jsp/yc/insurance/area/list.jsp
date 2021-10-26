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
<t:datagrid actionUrl="insuranceController/area/datagrid" tableContentId="jqGrid_wrapper" searchGroupId="searchGroupId" fit="true" caption="区县管理" name="table_list_2"  sortName="areaSort"  sortOrder="desc">

	<t:dgCol name="id" label="编号" hidden="true" key="true" width="20"></t:dgCol>
	<t:dgCol name="areaName" label="区县名称" width="200" query="true" ></t:dgCol>
	<t:dgCol name="areaCode" label="区县编码" width="200" query="true" ></t:dgCol>
	<t:dgCol name="areaSort" label="显示序号"  width="150"  ></t:dgCol>
	<t:dgCol name="areaState" label="启用状态"  width="100"  replace="启用_0,禁用_1" ></t:dgCol>
	<t:dgCol name="createDate" label="创建时间" width="150"></t:dgCol>
	<t:dgCol name="opt" label="操作" width="150"></t:dgCol>
	<t:dgDelOpt label="删除" url="insuranceController/area/del?id={id}" />
	<t:dgFunOpt label="学校管理" funName="updateGrad" icon="fa fa-list-ul" />
	<t:dgToolBar url="insuranceController/area/addorupdate" type="add" width="60%" ></t:dgToolBar>
	<t:dgToolBar url="insuranceController/area/addorupdate" type="edit" width="60%" ></t:dgToolBar>
	<t:dgToolBar type="refresh"></t:dgToolBar>
</t:datagrid>

</body>
<script type="text/javascript">
	function updateGrad(id){
		window.location = 'insuranceController/school/list';
	}
</script>
</html>