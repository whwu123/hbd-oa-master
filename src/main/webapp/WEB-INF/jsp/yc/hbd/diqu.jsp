<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>财信育才管理系统</title>
    <meta name="keywords" content="active4j-oa演示系统">
    <meta name="description" content="active4j-oa演示系统">
    <link rel="shortcut icon" href="../static/bootstrap/image/favicon.ico">
    <link href="../static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../static/bootstrap/css/font-awesome.min.css" rel="stylesheet">
    <link href="../static/bootstrap/css/animate.min.css" rel="stylesheet">
    <link href="../static/bootstrap/css/style.min.css" rel="stylesheet">
    <link href="../static/toastr/css/toastr.min.css" rel="stylesheet">
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
        <%--<div>
            <h3 class="logo-name">YC2</h3>
        </div>--%>
        <h3 style="text-align: left;color: #9d9d9d">身份证号查询</h3>

        <div id="div1"></div>

            <form class="form-horizontal m-t" id="signupForm" action="checkPayMoney" >
                <div class="form-group">
                    <input type="text" name="studentCard" id="studentCard" value="" class="form-control" placeholder="请输入学生身份证号" required="">
                </div>

                <button type="submit" class="btn btn-primary block full-width m-b" >立即缴费</button>

            </form>




</div>
<script src="../static/jquery/js/jquery.min.js"></script>
<script src="../static/bootstrap/js/bootstrap.min.js"></script>
<script src="../static/bootstrap/js/qq.js"></script>
<script src="../static/validate/js/jquery.validate.min.js"></script>
<script src="../static/validate/js/messages_zh.min.js"></script>
<script src="../static/login/js/login.js"></script>
<script src="../static/toastr/js/toastr.min.js"></script>
<script>
    function payMoney(str) {
       var studentCard  = $("#studentCard").val();
       alert(studentCard);
    }

</script>
</body>
</html>

