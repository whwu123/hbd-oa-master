<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <t:base type="default"></t:base>
    <title>查询管理系统</title>
</head>
<body class="gray-bg">
<!-- 页面部分 -->
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>缴费统计</h5>
                </div>

                <div class="ibox-content">
                    <button class="btn btn-primary" style="    margin-bottom: 5px" type="button" onclick="daochuIndex('${xuexiaoName}');"><i class="fa fa-level-up"></i>导出</button>
                    <button class="btn btn-primary" style="    margin-bottom: 5px" type="button" onclick="reload();"><i class="fa fa-refresh"></i>刷新</button>
                    <table class="table table-hover">

                        <thead>
                        <tr class="info" style="font-weight: bold">
                            <td>序号</td>
                            <td>名称</td>
                            <td>学生平安保险</td>
                            <td>交通意外、重大疾病险</td>
                            <td>监护人责任险</td>
                            <td>合计</td>
                        </tr>
                        </thead>
                        <thead>
                        <tr  style="font-weight: bold">
                            <td>#</td>
                            <td ><span class="label label-warning" style="color: white;font-size: 14px;">${xuexiaoModel.schoolName}</span></td>
                            <td ><span class="label ">${xuexiaoModel.xuepingxian}</span></td>
                            <td ><span class="label ">${xuexiaoModel.yiwaixian}</span></td>
                            <td ><span class="label ">${xuexiaoModel.jianhurenxian}</span></td>
                            <td ><span class="label label-success  ">${xuexiaoModel.total}</span></td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="im" items="${indexmodelSchoolList}" varStatus="s">
                            <tr>
                                <td>${s.count}</td>
                                <td><span class="label " style="font-size: 13px;">${im.nianjiName}</span></td>
                                <td><span class="label ">${im.xuepingxian}</span></td>
                                <td><span class="label ">${im.yiwaixian}</span></td>
                                <td><span class="label ">${im.jianhurenxian}</span></td>
                                <td><span class="label label-success ">${im.total}</span></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
<script>
    function reload(){
        location.reload();
    }
    function daochuIndex(xuexiaoName){
        window.location.href="reportController/exportXuexiao?name="+xuexiaoName;
    }

</script>

</html>

