<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<t:base type="default,select2,jqgrid,datetimePicker,laydate"></t:base>
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
<t:datagrid actionUrl="ycRefunController/datagrid" tableContentId="jqGrid_wrapper" searchGroupId="searchGroupId" fit="true" caption="学生管理" name="table_list_2"  sortName="createDate"  sortOrder="desc">

	<t:dgCol name="id" label="编号" hidden="true" key="true" width="20"></t:dgCol>
	<t:dgCol name="payNumber" label="订单流水号" width="120" ></t:dgCol>
	<t:dgCol name="studentCard" label="学生身份证" width="90" ></t:dgCol>
	<t:dgCol name="studentName" label="学生姓名" width="90" query="true"></t:dgCol>
	<t:dgCol name="payMoney" label="购买金额" width="90" query="true"></t:dgCol>
	<t:dgCol name="state" label="状态" replace="送审中_1,已送审_2,已删除_-1" query="true" display="refund"></t:dgCol>
	<t:dgCol name="tbrPhone" label="家长联系电话" width="90" query="true"></t:dgCol>
	<t:dgCol name="content" label="退款理由" width="150" ></t:dgCol>
	<t:dgCol name="createDate" label="创建时间"  datefmt="yyyy-MM-dd HH:mm:ss" query="true" queryModel="group" datePlugin="laydate"></t:dgCol>
	<t:dgDelOpt label="删除" url="ycRefunController/del?id={id}" operationCode="sys:user:del"/>
	<t:dgCol name="opt" label="操作" width="90"></t:dgCol>
	<t:dgToolBar label="导出"  type="export" funName="arrowUp"></t:dgToolBar>
	<t:dgToolBar url="ycRefunController/addorupdate" type="add" width="50%" height="60%" ></t:dgToolBar>
	<t:dgToolBar url="ycRefunController/addorupdate" type="edit" width="50%" height="60%" ></t:dgToolBar>
	<t:dgToolBar type="refresh"></t:dgToolBar>
	<t:dgFunOpt label="送审" funName="auditingStu(id,studentName)" icon="fa fa-check"></t:dgFunOpt>
</t:datagrid>
<script type="text/javascript">

	function arrowUp() {
		var state = $("#state").val();
		//var end = $("#createDate_end").val();

		window.location.href="ycRefunController/export?state="+state;
	}



	$(function(){
		laydate({elem:"#createDate_begin",event:"focus",istime: true, format: 'YYYY-MM-DD hh:mm:ss'});
		laydate({elem:"#createDate_end",event:"focus",istime: true, format: 'YYYY-MM-DD hh:mm:ss'});
	});

	function auditingStu(id,studentName) {

		qhConfirm("审核退费记录《" + studentName + "》的信息吗?", function (index) {
			//关闭询问
			parent.layer.close(index);

			//是
			$.post("ycRefunController/auditingStu", {id: id}, function (data) {
				if (data.success) {
					qhTipSuccess(data.msg);
					//操作结束，刷新表格
					reloadTable('table_list_2');
				} else {
					qhTipWarning(data.msg);
				}
			});

		}, function () {
			//否
		});
	}
</script>
</body>
</html>