<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<t:base type="default,select2,icheck"></t:base>
	<script type="text/javascript">
		$(function() {
			$("#roleid").val("${roleId}".split(",")).trigger("change");
		});
	
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<t:formvalid action="updateController/saveUpdatelog">
							<input type="hidden" name="id" id="id" value="${ycUpdateLog.id }">
							<div class="form-group">
                                <label class="col-sm-3 control-label">学生所在县区名称*：</label>
                                <div class="col-sm-8">
                                    <select class="form-control help-block m-b-none" name="quxianDepartment">
                                        <option value="" <c:if test="${ycUpdateLog.quxianDepartment==''}">selected</c:if>>请选择</option>
                                        <option value="市辖区经理室" <c:if test="${ycUpdateLog.quxianDepartment=='市辖区经理室'}">selected</c:if>>市辖区经理室</option>
                                        <option value="鹤城区经理室" <c:if test="${ycUpdateLog.quxianDepartment=='鹤城区经理室'}">selected</c:if>>鹤城区经理室</option>
                                        <option value="沅陵县经理室" <c:if test="${ycUpdateLog.quxianDepartment=='沅陵县经理室'}">selected</c:if>>沅陵县经理室</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">学生姓名*：</label>
                                <div class="col-sm-8">
                                    <input  type="text" class="form-control" id="studentName" name="studentName" value="${ycUpdateLog.studentName}"  placeholder="请输入学生真实姓名"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">学生身份证：</label>
                                <div class="col-sm-8">
                                    <input   type="text" class="form-control" id="studentCard" name="studentCard" value="${ycUpdateLog.studentCard}" placeholder="请输入有效的学生身份证"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">学生所在学校名称*：</label>
                                <div class="col-sm-8">
                                    <input   type="text" class="form-control" id="studentSchool" name="studentSchool" value="${ycUpdateLog.studentSchool}"  placeholder="请输入学校名称" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否已经缴费：</label>
                                <div class="col-sm-8">
                                    <select class="form-control help-block m-b-none" name="flagPay" id="flagPay">
                                        <option value="是" >是</option>
                                        <option value="否" >否</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">错误类型：</label>
                                <div class="col-sm-8">
                                    <select class="form-control help-block m-b-none" name="errorType" id="errorType">
                                        <option value="无" <c:if test="${ycUpdateLog.errorType==''}">selected</c:if>>请选择</option>
                                        <option value="加保"  <c:if test="${ycUpdateLog.errorType=='加保'}">selected</c:if> >加购险种</option>
                                        <option value="年级错误" <c:if test="${ycUpdateLog.errorType=='年级错误'}">selected</c:if>>年级错误</option>
                                        <option value="班级错误" <c:if test="${ycUpdateLog.errorType=='班级错误'}">selected</c:if>>班级错误</option>
                                        <option value="学生学校名称错误" <c:if test="${ycUpdateLog.errorType=='学生学校名称错误'}">selected</c:if>>学生学校名称错误</option>
                                        <option value="身份证被占用"  <c:if test="${ycUpdateLog.errorType=='身份证被占用'}">selected</c:if> >身份证被占用</option>
                                        <option value="监护人信息错误"  <c:if test="${ycUpdateLog.errorType=='监护人信息错误'}">selected</c:if> >监护人信息错误</option>
                                        <option value="入学年份错误"  <c:if test="${ycUpdateLog.errorType=='入学年份错误'}">selected</c:if> >入学年份错误</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">订单流水号：</label>
                                <div class="col-sm-8">
                                    <input  type="text" class="form-control" id="memoThree" name="memoThree" value="${ycUpdateLog.memoThree}" placeholder="请输入缴费后的订单号"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">家长联系方式：</label>
                                <div class="col-sm-8">

                                    <input   type="text" class="form-control" id="jiazhengPhone" name="jiazhengPhone" value="${ycUpdateLog.jiazhengPhone}"  placeholder="请输入家长电话"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">错误信息：</label>
                                <div class="col-sm-8">
                                    <textarea id="memoOne" name="memoOne" class="form-control" rows="3"  placeholder="输入您填写错误的信息" >${ycUpdateLog.memoOne}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">修改信息：</label>
                                <div class="col-sm-8">
                                    <textarea id="memoTwo" name="memoTwo" class="form-control" rows="3" placeholder="您想要修改的正确信息">${ycUpdateLog.memoTwo}</textarea>
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
    var tagname='';
    var attrid='';
    var tagvalue='';
    var flag =true;
    document.oninput = function(e){

        var o = e.srcElement || e.target;
        getValue(o);
        if(tagname !='' && tagname=='INPUT'){
            if(tagvalue != '' && !/^[^\<>]*$/.test(tagvalue)){
                //包含<>返回false
                //alert("输入内容不能包含特殊字符,如尖括号（<>）");
                var str = tagvalue.replace('<', '<').replace('>', '>');
                $(o).val(str);//把过滤特殊字符后的内容赋值给文本框
                tagvalue='';//当输入第一个字符为特殊字符，回退键删除后会有缓存
                if(flag){
                    alert("您输入的内容中包含了特殊字符如尖括号<>，系统已自动过滤！");
                    flag=false;
                }

                return false;
            }
            return true;
        }
        if(tagname !='' && tagname=='TEXTAREA'){
            if(tagvalue != '' && !/^[^\<>]*$/.test(tagvalue)){
                //包含<>返回false
                //alert("输入内容不能包含特殊字符");
                var str = tagvalue.replace('<', '<').replace('>', '>');
                $(o).val(str);//把过滤特殊字符后的内容赋值给文本框
                tagvalue='';
                alert("您输入的内容中包含了特殊字符如尖括号<>，系统已自动为你转译！");
                return false;
            }
            return true;
        }
        //escape2Html();
    }

    function getValue(o){
        if(o.tagName!=''){
            tagname=o.tagName;
        }
        if($(o).attr('id')){
            attrid=$(o).attr('id');
        }
        if($(o).val()){
            tagvalue=$(o).val();
        }
    }
</script>

</html>

