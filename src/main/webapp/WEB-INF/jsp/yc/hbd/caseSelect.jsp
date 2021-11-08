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
            <input type="hidden" value="${informationEntity.id}" id="id" name="id"/>
            <input type="hidden" value="${informationEntity.schoolId}" id="schoolId"/>
            <input type="hidden" value="${informationEntity.nianjiId}" id="nianjiId"/>
            <input type="hidden" value="${informationEntity.banjiId}" id="banjiId"/>
            <input type="hidden" value="" id="baoxianStr" name="baoxianStr"/>
            <input type="hidden" value="" id="baoxianTotalmoney"/>
            <h3 style="text-align: left;margin-top: 20px;">学生信息</h3>
            <hr class="hr_new"/>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                ${informationEntity.studentName} | <span id="schoolName"></span><span id="nianjiName"></span><span id="banjiName"></span>
            </p>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                身份证：${informationEntity.studentCard}
            </p>
            <hr class="hr_new"/>
            <h3 style="text-align: left;margin-top: 20px;">家长信息</h3>
            <hr class="hr_new"/>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                ${informationEntity.parentName} | ${informationEntity.parentPhone}
            </p>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                身份证：${informationEntity.parentCard}
            </p>
            <hr class="hr_new"/>

            <h3 style="text-align: left;">学生保险保费共<span id="totalmoney2">70</span>元</h3>
            <hr class="hr_new"/>

            <p style="text-align: left;margin-top: 10px;color: #9d9d9d" id="fruit">
                学平险
                <span style="float: right;color: black;">
                   40 <input type="checkbox" id="xpx" text ="1_学平险" checked value="40"  name="chk"  onclick="checkboxOnclick('ywx')" />
                </span>
            </p>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                交通意外重疾险
                <span style="float: right;color: black;">
                    20 <input type="checkbox" id="ywx" text = "2_交通意外重疾险" checked value="20" name="chk"  onclick="checkboxOnclick('ywx')" />
                </span>
            </p>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                监护人责任险
                <span style="float: right;color: black;">
                   10 <input type="checkbox" id="zrx" text = "3_护人责任险" checked value="10" name="chk"  onclick="checkboxOnclick('zrx')" />
                </span>
            </p>


            <h3 style="text-align: left;margin-top: 20px;">保险期间</h3>
            <hr class="hr_new"/>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                起保时间：2021年09月01日零时起
            </p>
            <p style="text-align: left;margin-top: 10px;color: #9d9d9d">
                止保时间：2022年02月28日二十四时止
            </p>
            <hr class="hr_new"/>
            <button type="submit" class="btn btn-primary block full-width m-b" style="background-color: #1136ea;" onclick="doNxet()">金额：￥ <span id="totalmoney">70</span> 元</button>

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
    });

    function doNxet() {
        checkboxOnclick2();
       var baoxianStr =  $("#baoxianStr").val();
       alert("保存订单="+baoxianStr)

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

