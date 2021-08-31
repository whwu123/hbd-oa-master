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
<t:datagrid actionUrl="updateController/datagrid2" tableContentId="jqGrid_wrapper" searchGroupId="searchGroupId" fit="true" caption="学生管理" name="table_list_2"  sortName="createDate"  sortOrder="desc">

	<t:dgCol name="id" label="编号" hidden="true" key="true" width="20"></t:dgCol>
	<t:dgCol name="quxianDepartment" label="学生所在县区名称" width="90"  query="true"  ></t:dgCol>
	<t:dgCol name="studentSchool" label="学生所在学校名称" width="90" query="true" classes="stuCad"></t:dgCol>
	<t:dgCol name="studentName" label="学生姓名" width="60" ></t:dgCol>
	<t:dgCol name="studentCard" label="学生身份证号" width="90" query="true"></t:dgCol>
	<t:dgCol name="flagPay" label="是否缴费" width="50" ></t:dgCol>
	<t:dgCol name="memoThree" label="订单流水号" width="90" query="true"></t:dgCol>
	<t:dgCol name="jiazhengPhone" label="家长联系方式" width="90" query="true" ></t:dgCol>
	<t:dgCol name="errorType" label="错误类型" width="90" query="true" replace="无_无,加保_加保,身份证被占用_身份证被占用,入学年份错误_入学年份错误,年级错误_年级错误,班级错误_班级错误,学生学校名称错误_学生学校名称错误,监护人信息错误_监护人信息错误"></t:dgCol>
	<t:dgCol name="memoOne" label="错误信息" ></t:dgCol>
	<t:dgCol name="memoTwo" label="修改信息"  ></t:dgCol>
	<t:dgCol name="updateDate" label="创建时间"  ></t:dgCol>
	<t:dgCol name="state" label="审核状态"  replace="未审核_1,已审核_2"  ></t:dgCol>
	<t:dgToolBar type="refresh"></t:dgToolBar>
</t:datagrid>
<script type="text/javascript">
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