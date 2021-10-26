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
						<t:formvalid action="insuranceController/school/grade/save">
							<input type="hidden" name="id" id="id" value="${ycSchoolEntity.id }">
                            <input type="hidden" name="parentId" id="parentId" value="${ycSchoolEntity.parentId }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">年级*：</label>
                                <div class="col-sm-8">
                                        <input id="name" name="name"  type="text" class="form-control" value="${ycSchoolEntity.name }" placeholder="例如：一年级">
                                   <%-- <select class="form-control help-block m-b-none" name="studentNianji">
                                        <option value="幼儿园小">幼儿园小</option>
                                        <option value="幼儿园中">幼儿园中</option>
                                        <option value="幼儿园大">幼儿园大</option>
                                        <option value="学前班" >学前班</option>
                                        <option value="一年级" >一年级</option>
                                        <option value="二年级" >二年级</option>
                                        <option value="三年级" >三年级</option>
                                        <option value="四年级" >四年级</option>
                                        <option value="五年级" >五年级</option>
                                        <option value="六年级" >六年级</option>
                                        <option value="初一" >初一</option>
                                        <option value="初二" >初二</option>
                                        <option value="初三" >初三</option>
                                        <option value="高一" >高一</option>
                                        <option value="高二" >高二</option>
                                        <option value="高三" >高三</option>
                                    </select>--%>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">显示顺序*：</label>
                                <div class="col-sm-8">
                                    <input   type="number" class="form-control" id="sort" name="sort" value="${ycSchoolEntity.sort}" placeholder="默认数值越大排越前"/>
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

