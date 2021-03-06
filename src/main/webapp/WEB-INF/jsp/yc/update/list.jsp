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
<t:datagrid actionUrl="updateController/datagrid" tableContentId="jqGrid_wrapper" searchGroupId="searchGroupId" fit="true" caption="日志管理" name="table_list_2"  sortName="createDate"  sortOrder="desc">

	<t:dgCol name="id" label="编号" hidden="true" key="true" width="20"></t:dgCol>
	<t:dgCol name="quxianDepartment" label="学生所在县区名称" width="90"  query="true"  ></t:dgCol>
	<t:dgCol name="studentSchool" label="学生所在学校名称" width="90" query="true" classes="stuCad"></t:dgCol>
	<t:dgCol name="studentName" label="学生姓名" width="60" query="true"></t:dgCol>
	<t:dgCol name="studentCard" label="学生身份证号" width="90" query="true"></t:dgCol>
	<t:dgCol name="flagPay" label="是否缴费" width="50" replace="是_是,否_否" query="true"></t:dgCol>
	<t:dgCol name="memoThree" label="订单流水号" width="90" query="true"></t:dgCol>
	<t:dgCol name="jiazhengPhone" label="家长联系方式" width="90" query="true" ></t:dgCol>
	<t:dgCol name="errorType" label="错误类型" width="90" query="true" replace="无_无,加购险种_加保,身份证被占用_身份证被占用,入学年份错误_入学年份错误,年级错误_年级错误,班级错误_班级错误,学生学校名称错误_学生学校名称错误,监护人信息错误_监护人信息错误"></t:dgCol>
	<t:dgCol name="memoOne" label="错误信息" ></t:dgCol>
	<t:dgCol name="memoTwo" label="修改信息"  ></t:dgCol>
	<t:dgCol name="createDate" label="创建时间"  datefmt="yyyy-MM-dd HH:mm:ss" query="true" queryModel="group" datePlugin="laydate"></t:dgCol>
	<t:dgCol name="opt" label="操作" width="60"></t:dgCol>
	<t:dgToolBar label="导出"  type="export" funName="arrowUp"></t:dgToolBar>
	<t:dgToolBar url="updateController/addUpdate" type="add" width="60%" ></t:dgToolBar>
	<t:dgToolBar url="updateController/addUpdate" type="edit" width="60%" ></t:dgToolBar>
	<t:dgFunOpt label="审核" funName="auditingStu(id,studentName)" icon="fa fa-check"></t:dgFunOpt>
	<t:dgToolBar type="refresh"></t:dgToolBar>
</t:datagrid>
<script type="text/javascript">
    $(function(){
        laydate({elem:"#createDate_begin",event:"focus",istime: true, format: 'YYYY-MM-DD hh:mm:ss'});
        laydate({elem:"#createDate_end",event:"focus",istime: true, format: 'YYYY-MM-DD hh:mm:ss'});
    });

	function arrowUp() {
		var quxianDepartment = $("#quxianDepartment").val();
		var studentSchool = $("#studentSchool").val();
		var studentName = $("#studentName").val();
		var studentCard = $("#studentCard").val();
		var flagPay = $("#flagPay").val();
		var memoThree = $("#memoThree").val();
		var jiazhengPhone = $("#jiazhengPhone").val();
		var errorType = $("#errorType").val();
		window.location.href="reportController/export3?quxianDepartment="+quxianDepartment+"&studentSchool="+studentSchool+"&studentName="+studentName+"&studentCard="+studentCard+"&flagPay="+flagPay+"&memoThree="+memoThree+"&jiazhengPhone="+jiazhengPhone+"&errorType="+errorType+"&state=1";
	}

	function auditingStu(id,studentName) {
		//alert(id);
		qhConfirm("确定要审核学生《" + studentName + "》的信息吗?", function (index) {
			//关闭询问
			parent.layer.close(index);

			//是
			$.post("updateController/auditingStu", {id: id}, function (data) {
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