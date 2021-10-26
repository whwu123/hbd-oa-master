<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="default,select2,icheck，jqgrid"></t:base>
    <script type="text/javascript">
        //选择区分供应商是否区分年级
        function changeQuFen() {
            var qufen = $("#schoolIsGrad").val();

            if ('0' == qufen) {
                $("#gradControl").hide();
                $("#schoolGrad").val(null);
            } else {
                $("#gradControl").show();
            }
        }

        $(function() {
            $("#schoolGrad").val("${ycSchoolEntity.schoolGrad}".split(",")).trigger("change");


            var level =  $("#schoolIsGrad").val();
            if(level) {
                if (level == '0') {
                    $("#gradControl").hide();
                    $("#schoolGrad").val(null);
                } else {
                    $("#gradControl").show();
                }
            }else {
                $("#gradControl").hide();
                $("#schoolGrad").val(null);
            }
        })
    </script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<t:formvalid action="insuranceController/school/insured/save">
							<input type="hidden" name="id" id="id" value="${ycSchoolEntity.id }">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">所属区县*：</label>
                                <div class="col-sm-8">
                                    <select class="form-control m-b" name="schoolAreaId" id="schoolAreaId">
                                        <c:forEach items="${areaList }" var="t">
                                            <option value="${t.id }" >${t.areaName }</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">学校名称*：</label>
                                <div class="col-sm-8">
                                    <input   type="text" class="form-control" id="schoolName" name="schoolName" value="${ycSchoolEntity.schoolName}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">学校编码*：</label>
                                <div class="col-sm-8">
                                    <input   type="text" class="form-control" id="schoolCode" name="schoolCode" value="${ycSchoolEntity.schoolCode}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">学校负责人姓名：</label>
                                <div class="col-sm-8">
                                    <input   type="text" class="form-control" id="schoolHearName" name="schoolHearName" value="${ycSchoolEntity.schoolHearName}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">学校负责人电话：</label>
                                <div class="col-sm-8">
                                    <input   type="text" class="form-control" id="schoolHrarPhone" name="schoolHrarPhone" value="${ycSchoolEntity.schoolHrarPhone}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">学校显示顺序*：</label>
                                <div class="col-sm-8">
                                    <input   type="number" class="form-control" id="schoolSort" name="schoolSort" value="${ycSchoolEntity.schoolSort}" placeholder="数值越小排越前"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">学校类型*：</label>
                                <div class="col-sm-8">
                                    <select class="form-control m-b" name="schoolType" id="schoolType">
                                        <c:forEach items="${xueliList }" var="t">
                                            <option value="${t.value }" <c:if test="${ycSchoolEntity.schoolType == t.value}">selected</c:if>>${t.label }</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <%--<div class="form-group">
                                <label class="col-sm-3 control-label">学校性质*：</label>
                                <div class="col-sm-8">
                                    <select class="form-control m-b" name="schoolPublic" id="schoolPublic">
                                        <option value="0" >公立学校</option>
                                        <option value="1" >私立学校</option>
                                    </select>
                                </div>
                            </div>--%>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">承保公司区分年级*：</label>
                                <div class="col-sm-8">
                                    <select class="form-control m-b" name="schoolIsGrad" id="schoolIsGrad"onchange="changeQuFen();">
                                        <option value="0" <c:if test="${ycSchoolEntity.schoolIsGrad eq 0}">selected="selected"</c:if>>不区分</option>
                                        <option value="1" <c:if test="${ycSchoolEntity.schoolIsGrad > 0}">selected="selected"</c:if>>区分</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group" id="gradControl">
                                <label class="col-sm-3 control-label">年级*：</label>
                                <div class="col-sm-8">
                                    <select class="form-control m-b select2" name="schoolGrad" id="schoolGrad" multiple="multiple" >
                                        <c:forEach items="${gradList }" var="t">
                                            <option value="${t.value }" >${t.label }</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">供应商承保险种*：</label>
                                <div class="col-sm-8">
                                    <select class="form-control m-b" name="insuranceType" id="insuranceType">
                                            <c:forEach items="${insuranceList }" var="t">
                                                <option value="${t.value }" <c:if test="${ycSchoolEntity.insuranceType == t.value}">selected</c:if>>${t.label }</option>
                                            </c:forEach>
                                     </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">供应商公司负责人：</label>
                                <div class="col-sm-8">
                                    <select class="form-control m-b" name="insurancePersonId" id="insurancePersonId">
                                    <c:forEach items="${lstTrees}" var="f">
                                        <option value="${f.value}" <c:if test="${ycSchoolEntity.insurancePersonId == f.value }">selected='selected'</c:if>>${f.key}</option>
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

