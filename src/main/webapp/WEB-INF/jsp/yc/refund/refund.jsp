<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="default,select2,icheck"></t:base>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<t:formvalid action="ycRefunController/saveUpdate">
							<input type="hidden" name="id" id="id" value="${refund.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">支付流水号*：</label>
                                <div class="col-sm-8">
                                    <input   type="text" class="form-control" id="payNumber" name="payNumber" value="${refund.payNumber}"  placeholder="请输入缴费后的流水单号" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">学生姓名*：</label>
                                <div class="col-sm-8">
                                    <input  type="text" class="form-control" id="studentName" name="studentName" value="${refund.studentName}"  placeholder="请输入学生真实姓名"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">学生身份证*：</label>
                                <div class="col-sm-8">
                                    <input   type="text" class="form-control" id="studentCard" name="studentCard" value="${refund.studentCard}" placeholder="请输入有效的学生身份证"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">家长联系电话：</label>
                                <div class="col-sm-8">
                                    <input   type="text" class="form-control" id="tbrPhone" name="tbrPhone" value="${refund.tbrPhone}" placeholder="输入家长联系电话"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">购买金额：</label>
                                <div class="col-sm-8">
                                    <input  type="number" class="form-control" id="payMoney" name="payMoney" value="${refund.payMoney}" placeholder="请输入支付成功的金额"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">退款原因：</label>
                                <div class="col-sm-8">
                                    <textarea id="content" name="content" class="form-control" rows="3"  placeholder="输入需要退款的理由" >${refund.content}</textarea>
                                </div>
                            </div>
						</t:formvalid>
                    </div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>

</script>
</html>

