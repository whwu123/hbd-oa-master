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
						<t:formvalid action="insuranceController/area/save">
							<input type="hidden" name="id" id="id" value="${ycAreaEntity.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">区县名称*：</label>
                                <div class="col-sm-8">
                                    <input   type="text" class="form-control" id="areaName" name="areaName" value="${ycAreaEntity.areaName}"   />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">区县编码*：</label>
                                <div class="col-sm-8">
                                    <input   type="text" class="form-control" id="areaCode" name="areaCode" value="${ycAreaEntity.areaCode}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">显示序号*：</label>
                                <div class="col-sm-8">
                                    <input   type="text" class="form-control" id="areaSort" name="areaSort" value="${ycAreaEntity.areaSort}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否启用*：</label>
                                <div class="col-sm-8">
                                    <select class="form-control m-b" name="areaState" id="areaState">
                                        <option value="0" <c:if test="${ycAreaEntity.areaState == 0}">selected</c:if>>启用</option>
                                        <option value="1" <c:if test="${ycAreaEntity.areaState == 1}">selected</c:if>>禁用</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">描述</label>
                                <div class="col-sm-8">
                                    <textarea name="areaNote" class="form-control">${ycAreaEntity.areaNote }</textarea>
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

