<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/context/mytags.jsp"%>
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
            background-color: #1136ea;
            border-color: #1136ea;
            color:  white;

        }
        .hr_new{
            margin-top: 10px;
            margin-bottom: 10px;
            border: 0;
            border-top: 2px solid #ddd;
        }
        .hr_new_2{
            margin-top: 10px;
            margin-bottom: 10px;
            border: 0;
            border-top: 2px solid red;
        }
    </style>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>
<body class="gray-bg">
<div class="middle-box text-center loginscreen  animated fadeInDown">

    <form class="form-horizontal m-t" id="signupForm" action="webhbd/saveStudentOrder" method="post">

        <input type="hidden" value="${ycStudentOrderModelEntity.schoolId}" id="schoolId"/>
        <input type="hidden" value="${ycStudentOrderModelEntity.nianjiId}" id="nianjiId"/>
        <input type="hidden" value="${ycStudentOrderModelEntity.banjiId}" id="banjiId"/>
        <input type="hidden" value="${ycStudentOrderModelEntity.one}" id="tone" />
        <input type="hidden" value="${ycStudentOrderModelEntity.two}" id="ttwo" />
        <input type="hidden" value="${ycStudentOrderModelEntity.three}" id="tthree" />
        <h3 style="text-align: left;margin-top: 20px;">订单信息</h3>
        <hr class="hr_new"/>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            总保费：  ￥${ycStudentOrderModelEntity.one+ycStudentOrderModelEntity.two+ycStudentOrderModelEntity.three} 元
        </p>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            已购险种：
        </p>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            <c:if test="${ycStudentOrderModelEntity.one == 10}">
                监护人责任险  ￥10元
            </c:if>
        </p>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            <c:if test="${ycStudentOrderModelEntity.two == 20}">
                交通意外重大疾病险  ￥20元
            </c:if>
        </p>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            <c:if test="${ycStudentOrderModelEntity.three == 40}">
                学生平安保险  ￥40元
            </c:if>
        </p>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            订单编号：${ycStudentOrderModelEntity.payNumber}
        </p>
        <hr class="hr_new"/>

        <h3 style="text-align: left;margin-top: 20px;">学生信息</h3>
        <hr class="hr_new"/>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            ${ycStudentOrderModelEntity.studentName} | <span id="schoolName"></span><span id="nianjiName"></span><span id="banjiName"></span>
        </p>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            身份证：${ycStudentOrderModelEntity.studentCard}
        </p>
        <hr class="hr_new"/>
        <h3 style="text-align: left;margin-top: 20px;">家长信息</h3>
        <hr class="hr_new"/>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            ${ycStudentOrderModelEntity.parentName} | ${ycStudentOrderModelEntity.parentPhone}
        </p>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            身份证：${ycStudentOrderModelEntity.parentCard}
        </p>
        <hr class="hr_new"/>
        <h3 style="text-align: left;margin-top: 20px;">保险期间</h3>
        <hr class="hr_new"/>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            起保时间：2021年09月01日零时起
        </p>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            止保时间：2022年02月28日二十四时止
        </p>
        <hr class="hr_new"/>

        <h3 style="text-align: left;margin-top: 20px;">承保信息</h3>
        <hr class="hr_new"/>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            险种：监护人责任险
        </p>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            承保公司：<span id="cjhr"></span>
        </p>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            负责人：<span id="jhrheat"></span>
        </p>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            险种：交通意外重疾险
        </p>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            承保公司：<span id="cjt"></span>
        </p>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            负责人：<span id="jtheat"></span>
        </p>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            险种：学生平安保险
        </p>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            承保公司：<span id="cxpx"></span>
        </p>
        <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
            负责人：<span id="xpxheat"></span>
        </p>
        <hr class="hr_new"/>
        <c:if test="${ycStudentOrderModelEntity.one+ycStudentOrderModelEntity.two+ycStudentOrderModelEntity.three < 70}">
            <button type="submit" class="btn btn-primary block full-width m-b" style="background-color: #1136ea;" onclick="doNxet()">补交费用</button>
        </c:if>
        <button type="button" class="btn btn-primary block full-width m-b" style="background-color: #1136ea;" onclick="doUpdate('123')">去修改</button>

    </form>


</div>
<script src="static/jquery/js/jquery.min.js"></script>
<script src="static/bootstrap/js/bootstrap.min.js"></script>
<script src="static/bootstrap/js/qq.js"></script>
<script src="static/validate/js/jquery.validate.min.js"></script>
<script src="static/validate/js/messages_zh.min.js"></script>
<script src="static/toastr/js/toastr.min.js"></script>
<script>
    $(function(){
        //alert("初始化数据")
        var schoolId = $("#schoolId").val();
        var nianjiId = $("#nianjiId").val();
        var banjiId = $("#banjiId").val();
        $.ajax({
            url: 'webhbd/getInitData?id='+schoolId,
            type: 'GET',
            dataType: 'json',
            success: function (res) {
                if(res.success == true){
                    console.log(res)
                    //对数据进行处理
                    if(res.obj != null ){
                        $("#schoolName").html(res.obj.name)
                    }

                }
            }
        });
        $.ajax({
            url: 'webhbd/getInitData?id='+nianjiId,
            type: 'GET',
            dataType: 'json',
            success: function (res) {
                if(res.success == true){
                    console.log(res)
                    //对数据进行处理
                    if(res.obj != null ){
                        $("#nianjiName").html(res.obj.name)
                    }

                }
            }
        });
        $.ajax({
            url: 'webhbd/getInitData?id='+banjiId,
            type: 'GET',
            dataType: 'json',
            success: function (res) {
                if(res.success == true){
                    console.log(res)
                    //对数据进行处理
                    if(res.obj != null ){
                        $("#banjiName").html(res.obj.name)
                    }

                }
            }
        });

        //根据学校ID拿到承保公司信息
        $.ajax({
            url: 'webhbd/getSchoolInsuredData?id='+schoolId,
            type: 'GET',
            dataType: 'json',
            success: function (res) {
                if(res.success == true){
                    console.log(res)
                    //对数据进行处理
                    if(res.obj.length>0){

                        for(var i in res.obj){
                            var position=res.obj[i];
                            var pphone = position.pphone;
                            var pname = position.pname;
                            var cname = position.cname
                            var type = position.insuranceType
                            if(type == "xuepingxian"){
                                $("#cxpx").html(cname)
                                $("#xpxheat").html(pname+"|"+pphone);
                            }else if(type == "jiaotongjibing"){
                                $("#cjt").html(cname)
                                $("#jtheat").html(pname+"|"+pphone);
                            }else if(type =="jianhuren"){
                                $("#cjhr").html(cname)
                                $("#jhrheat").html(pname+"|"+pphone);
                            }
                        }
                    }

                }
            }
        });
    });

    function doNxet() {
        checkboxOnclick2();
        //var baoxianStr =  $("#baoxianStr").val();
        //alert("保存订单="+baoxianStr)

    }

    function doUpdate(studentCard){
        alert(studentCard);
    }

    function checkboxOnclick() {
        var chk_value =[];//定义一个数组
        var total = 0
        var baoxianStr = [];
        $('input[name="chk"]:checked').each(function(){//遍历每一个名字为nodes的复选框，其中选中的执行函数
            chk_value.push($(this).val());//将选中的值添加到数组chk_value中

            var text = $(this).attr("text");
            var money = $(this).val();
            var str = text+"_"+money;
            baoxianStr.push(str);
        });
        //var groups = chk_value.join(",");
        for(var i = 0; i < chk_value.length; i++) {
            total += Number(chk_value[i])
        }
        $("#totalmoney").html(total);
        $("#totalmoney2").html(total);
        $("#baoxianStr").val(baoxianStr);
        $("#baoxianTotalmoney").val(total);
    }

    function checkboxOnclick2() {
        var chk_value =[];//定义一个数组
        var total = 0
        var baoxianStr = [];
        $('input[name="chk"]:checked').each(function(){//遍历每一个名字为nodes的复选框，其中选中的执行函数
            chk_value.push($(this).val());//将选中的值添加到数组chk_value中
            var money = $(this).val();
            baoxianStr.push(money);
        });
        for(var i = 0; i < chk_value.length; i++) {
            total += Number(chk_value[i])
        }
        $("#totalmoney").html(total);
        $("#totalmoney2").html(total);
        $("#baoxianStr").val(baoxianStr);
        $("#baoxianTotalmoney").val(total);
    }


</script>
</body>
</html>

