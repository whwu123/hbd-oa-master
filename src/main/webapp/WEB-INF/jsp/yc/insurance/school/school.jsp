<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="default,select2,icheck，jqgrid"></t:base>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<t:formvalid action="insuranceController/school/save">
							<input type="hidden" name="id" id="id" value="${ycSchoolEntity.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">区县名称*：</label>
                                <div class="col-sm-8">
                                    <select class="form-control m-b" name="parentId" id="parentId">
                                        <c:forEach items="${areaList }" var="t">
                                            <option value="${t.id }" >${t.areaName }</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">学校名称*：</label>
                                <div class="col-sm-8">
                                    <input   type="text" class="form-control" id="name" name="name" value="${ycSchoolEntity.name}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">学校编码*：</label>
                                <div class="col-sm-8">
                                    <input   type="text" class="form-control" id="deptNo" name="deptNo" value="${ycSchoolEntity.deptNo}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">学校显示顺序*：</label>
                                <div class="col-sm-8">
                                    <input   type="number" class="form-control" id="sort" name="sort" value="${ycSchoolEntity.sort}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">学校性质*：</label>
                                <div class="col-sm-8">
                                    <select class="form-control m-b" name="type" id="type">
                                        <option value="0" >公立学校</option>
                                        <option value="1" >私立学校</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">学校类型*：</label>
                                <div class="col-sm-8">
                                    <select class="form-control m-b" name="record" id="record">
                                        <c:forEach items="${xueliList }" var="t">
                                            <option value="${t.value }" <c:if test="${ycSchoolEntity.record == t.value}">selected</c:if>>${t.label }</option>
                                        </c:forEach>
                                    </select>
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

