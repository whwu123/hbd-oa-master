<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>财信育才投保系统</title>
    <meta name="keywords" content="财信育才投保系统">
    <meta name="description" content="财信育才投保系统">
    <link rel="shortcut icon" href="static/bootstrap/image/favicon.ico">
    <link href="static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/bootstrap/css/font-awesome.min.css" rel="stylesheet">
    <link href="static/bootstrap/css/animate.min.css" rel="stylesheet">
    <link href="static/bootstrap/css/style.min.css" rel="stylesheet">
    <link href="static/toastr/css/toastr.min.css" rel="stylesheet">
    <style>
        #div1{
            border-bottom: 2px solid #1136ea;
        }
        .btn{
            background-color: #ddd;
            border-color: #ddd;
            color: #1c84c6;
            margin-left: 6px
        }
    </style>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>
<body class="gray-bg">
<div class="middle-box text-center loginscreen  animated fadeInDown">

        <form class="form-horizontal m-t" id="signupForm" action="login" method="post">
            <h3 style="text-align: left;color: #9d9d9d">学生信息</h3>

            <div id="div1"></div>
            <%--<input type="hidden" id="diquId" value="${diquId}"/>
            <input type="hidden" id="quxianId" value="${quxianId}"/>--%>
            <div class="form-group">
                <label class="col-sm-4 control-label">所在区县：</label>
                <div class="col-sm-8">
                    <select class="form-control help-block m-b-none" id="selectQuxian" onchange="func()">
                        <option>请选择区县</option>
                        <c:forEach items="${areaList }" var="t">
                            <option value="${t.id }" >${t.areaName }</option>
                        </c:forEach>
                    </select>

                </div>
            </div>



                <div class="form-group">
                    <label class="col-sm-4 control-label">所在学校：</label>
                    <div class="col-sm-8">
                        <select class="form-control help-block m-b-none" id="xuexiaoList" onchange="func2()">
                            <option>请选择学校</option>
                            <c:forEach items="${ycLocalEntityList }" var="t">
                                <option value="${t.id }" >${t.name }</option>
                            </c:forEach>
                        </select>

                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">所在年级：</label>
                    <div class="col-sm-8">
                        <select class="form-control help-block m-b-none" id="nianjiList" onchange="func3()">
                            <option>请选择年级</option>

                        </select>

                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">所在班级：</label>
                    <div class="col-sm-8">
                        <select class="form-control help-block m-b-none" id="banjiList">
                            <option>请选择班级</option>

                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">学生姓名：</label>
                    <div class="col-sm-8">
                        <input  type="text" class="form-control" value="" placeholder="请输入学生真实姓名" id="xueshengName"/>
                    </div>
                </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">学生身份证：</label>
                <div class="col-sm-8">
                    <input   type="text" class="form-control" value="${studentCard}" placeholder="请输入有效的学生身份证" id="xueshengCard"/>
                </div>
            </div>
            <h3 style="text-align: left;color: #9d9d9d;margin-top: 30px">家长信息</h3>
            <div id="div1"></div>

            <div class="form-group">
                <label class="col-sm-4 control-label">家长姓名：</label>
                <div class="col-sm-8">
                    <input   type="text" class="form-control" value=""  placeholder="请输入家长的真实姓名" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">家长电话：</label>
                <div class="col-sm-8">
                    <input   type="text" class="form-control" value=""  placeholder="请输入家长手机电话" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">家长身份证：</label>
                <div class="col-sm-8">
                    <input   type="text" class="form-control" value=""  placeholder="请输入家长身份证" />
                </div>
            </div>

            <button type="button" class="btn btn-primary block full-width m-b" onclick="doNxet()">下一步</button>

        </form>


</div>
<script src="static/jquery/js/jquery.min.js"></script>
<script src="static/bootstrap/js/bootstrap.min.js"></script>
<script src="static/bootstrap/js/qq.js"></script>
<script src="static/validate/js/jquery.validate.min.js"></script>
<script src="static/validate/js/messages_zh.min.js"></script>
<script src="static/login/js/login.js"></script>
<script src="static/toastr/js/toastr.min.js"></script>
<script>

    function func() {
        var areaId = $('#selectQuxian  option:selected').val();
        if(areaId != null) {
            $.ajax({
                url: 'webhbd/selectData?id='+areaId,
                type: 'GET',
                dataType: 'json',
                success: function (res) {
                    if(res.success == true){
                        console.log(res)
                        //alert(res.msg);;
                        //对数据进行处理
                        if(res.obj.length>0){
                            //alert(res.msg+"111")
                            $("#xuexiaoList").html("")
                            var html = "<option>请选择学校</option>";
                            //var html = "<option>请选择学校</option>";
                            for(var i in res.obj){
                                var position=res.obj[i];
                                var id = position.id;
                                var name = position.name;
                                html += "<option  value='"+id+"' >"+name+"</option>";
                                //console.log(id+'==='+name);
                            }
                            $("#xuexiaoList").html(html)
                        }

                    }
                }
            });

        }
    }

    function func2() {
        var schoolId = $('#xuexiaoList  option:selected').val();
        if(schoolId != null) {
            // alert(areaId)
            $.ajax({
                url: 'webhbd/selectData?id='+schoolId,
                type: 'GET',
                dataType: 'json',
                success: function (res) {
                    if(res.success == true){
                        console.log(res)
                        $("#nianjiList").html("")
                        var html = "<option>请选择年级</option>";
                        for(var i in res.obj){
                            var position=res.obj[i];
                            var id = position.id;
                            var name = position.name;
                            html += "<option  value='"+id+"' >"+name+"</option>";
                        }
                        $("#nianjiList").html(html)


                    }
                }
            });

        }
    }

    function func3() {
        var nianjiId = $('#nianjiList  option:selected').val();
        if(nianjiId != null) {
            // alert(areaId)
            $.ajax({
                url: 'webhbd/selectData?id='+nianjiId,
                type: 'GET',
                dataType: 'json',
                success: function (res) {
                    if(res.success == true){
                        console.log(res)
                        $("#banjiList").html("")
                        var html = "<option>请选择班级</option>";
                        for(var i in res.obj){
                            var position=res.obj[i];
                            var id = position.id;
                            var name = position.name;
                            html += "<option  value='"+id+"' >"+name+"</option>";
                        }
                        $("#banjiList").html(html)


                    }
                }
            });

        }
    }

    function doNxet() {
        var quxianId = $("#quxianId").val();
        var xueshengCard = $("#xueshengCard").val();
        var xueshengName = $("#xueshengName").val();
        var jieduanId = $("#jieduanId").val();
        var xuexiaoName =$("#xuexiaoList option:selected").text();
        var xuexiaoValue =$("#xuexiaoList option:selected").val();
        var nianji =$("#nianji option:selected").val();
        var banji = $("#banji").val();
        var diquId = $("#diquId").val();
        var xiangzhengId = $("#xiangzhenList option:selected").val();


        window.location.href="ychome/form2?quxianId="+quxianId+"&xueshengCard="+xueshengCard+ "&xueshengName="+xueshengName+"&jieduanId="+jieduanId+"&xuexiaoName="+xuexiaoName+"&nianji="+nianji+"&banji="+banji+"&xuexiaoValue="+xuexiaoValue+"&diquId="+diquId+"&xiangzhengId="+xiangzhengId;
    }

    function isCardID(sId) {
        if (!/(^\d{15}$)|(^\d{17}(\d|X|x)$)/.test(sId)) {
            console.log('你输入的身份证长度或格式错误')
            return false
        }
        //身份证城市
        var aCity = {
            11: "北京",
            12: "天津",
            13: "河北",
            14: "山西",
            15: "内蒙古",
            21: "辽宁",
            22: "吉林",
            23: "黑龙江",
            31: "上海",
            32: "江苏",
            33: "浙江",
            34: "安徽",
            35: "福建",
            36: "江西",
            37: "山东",
            41: "河南",
            42: "湖北",
            43: "湖南",
            44: "广东",
            45: "广西",
            46: "海南",
            50: "重庆",
            51: "四川",
            52: "贵州",
            53: "云南",
            54: "西藏",
            61: "陕西",
            62: "甘肃",
            63: "青海",
            64: "宁夏",
            65: "新疆",
            71: "台湾",
            81: "香港",
            82: "澳门",
            91: "国外"
        };
        if (!aCity[parseInt(sId.substr(0, 2))]) {
            console.log('你的身份证地区非法')
            return false
        }

        // 出生日期验证
        var sBirthday = (sId.substr(6, 4) + "-" + Number(sId.substr(10, 2)) + "-" +
            Number(sId.substr(12, 2))).replace(/-/g, "/"),
            d = new Date(sBirthday)
        if (sBirthday != (d.getFullYear() + "/" + (d.getMonth() + 1) + "/" + d.getDate())) {
            console.log('身份证上的出生日期非法')
            return false
        }

        // 身份证号码校验
        var sum = 0,
            weights = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2],
            codes = "10X98765432"
        for (var i = 0; i < sId.length - 1; i++) {
            sum += sId[i] * weights[i];
        }
        console.log(sum);
        var last = codes[sum % 11]; //计算出来的最后一位身份证号码
        if (sId[sId.length - 1] != last) {
            console.log('你输入的身份证号非法')
            return false
        }

        return true
    }
</script>
</body>
</html>