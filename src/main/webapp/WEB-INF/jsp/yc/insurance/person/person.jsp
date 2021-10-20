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
						<t:formvalid action="insuranceController/person/save">
							<input type="hidden" name="id" id="id" value="${ycInsuranceCompanyEntity.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">公司名称：</label>
                                <div class="col-sm-8">
                                    <select class="form-control help-block m-b-none"  name="companyId" id="companyId" >
                                        <option>请选择</option>
                                        <c:forEach items="${companyEntityList}" var="companyEntityList">
                                            <option value="${companyEntityList.id }_${companyEntityList.companyName}">${companyEntityList.companyName}</option>
                                        </c:forEach>
                                    </select>

                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">姓名：</label>
                                <div class="col-sm-8">
                                    <input   type="text" class="form-control" id="personName" name="personName" value="${ycInsurancePersonEntity.personName}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">性别：</label>
                                <div class="col-sm-8">
                                    <select class="form-control help-block m-b-none"  name="personSex" id="personSex" >
                                        <option>请选择性别</option>
                                        <option value="0">男性</option>
                                        <option value="1">女性</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">电话：</label>
                                <div class="col-sm-8">
                                    <input   type="text" class="form-control" id="personPhone" name="personPhone" value="${ycInsurancePersonEntity.personPhone}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">描述:</label>
                                <div class="col-sm-8">
                                    <textarea name="personNote" class="form-control">${ycInsurancePersonEntity.personNote }</textarea>
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

