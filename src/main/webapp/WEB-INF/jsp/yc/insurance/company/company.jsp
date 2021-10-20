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
						<t:formvalid action="insuranceController/companySave">
							<input type="hidden" name="id" id="id" value="${ycInsuranceCompanyEntity.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">公司名称*：</label>
                                <div class="col-sm-8">
                                    <input   type="text" class="form-control" id="companyName" name="companyName" value="${ycInsuranceCompanyEntity.companyName}"   />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">公司类型*：</label>
                                <div class="col-sm-8">

                                    <select class="form-control m-b" name="companyType" id="companyType">
                                            <option value="0" >请选择</option>
                                            <option value="1" <c:if test="${ycInsuranceCompanyEntity.companyType == 1}">selected</c:if>>财险</option>
                                            <option value="2" <c:if test="${ycInsuranceCompanyEntity.companyType == 2}">selected</c:if>>寿险</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">显示序号*：</label>
                                <div class="col-sm-8">
                                    <input   type="text" class="form-control" id="companySort" name="companySort" value="${ycInsuranceCompanyEntity.companySort}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">描述</label>
                                <div class="col-sm-8">
                                    <textarea name="companyNote" class="form-control">${ycInsuranceCompanyEntity.companyNote }</textarea>
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

