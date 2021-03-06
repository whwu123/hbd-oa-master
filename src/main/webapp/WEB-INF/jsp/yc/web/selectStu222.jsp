<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="format-detection" content="telephone=yes"/>
    <title>怀保代</title>
    <meta name="keywords" content="怀保代">
    <meta name="description" content="怀保代">
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
            background-color: red;
            border-color: red;
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

        <p style="text-align: left;font-size: 16px">线上缴费系统已经关闭，不再支持线上缴费及退费和修改信息。</p>
        <p style="text-align: left;font-size: 14px">如有需要请联系公司客服：<a href="tel:17374537783">17374537783</a></p>
        <p style="text-align: left;font-size: 14px">座机（工作日：9:00-17:30）<a href="tel:0745-2289827">0745-2289827</a></p>

</div>
<script src="static/jquery/js/jquery.min.js"></script>
<script src="static/bootstrap/js/bootstrap.min.js"></script>
<script src="static/bootstrap/js/qq.js"></script>
<script src="static/validate/js/jquery.validate.min.js"></script>
<script src="static/validate/js/messages_zh.min.js"></script>
<script src="static/login/js/login.js"></script>
<script src="static/toastr/js/toastr.min.js"></script>
<script>

    function toVaild(){
       // alert('11111111')

        var quxianDepartment = $("#quxianDepartment").val().trim();
        var studentName = $("#studentName").val().trim();
        var studentCard = $("#studentCard").val().trim();
        var studentSchool = $("#studentSchool").val().trim();
        var memoThree = $("#memoThree").val().trim();
        var memoOne = $("#memoOne").val().trim();
        var memoTwo = $("#memoTwo").val().trim();


        //  var toubaorenPhone = $("#toubaorenPhone").val();
        if(quxianDepartment === '' ){
            alert('请完成所有信息填写');
            return false
        }else if(studentName === '' ){
            alert('请完成所有信息填写');
            return false
        }else if(studentCard === '' ){
            alert('请完成所有信息填写');
            return false
        }else if(studentSchool === '' ){
            alert('请完成所有信息填写');
            return false
        }else if(studentSchool === '' ){
            alert('请完成所有信息填写');
            return false
        }if(memoThree === '' ){
            alert('请完成所有信息填写');
            return false
        }else if(memoOne === '' ){
            alert('请完成所有信息填写');
            return false
        }else if(memoTwo === '' ){
            alert('请完成所有信息填写');
            return false
        }else {
            return true
        }
    }


    $('#studentCard').blur(function(){

        var num = $("#studentCard").val();
        if(isCardID(num)){
            // alert("正确")
            //进入后台去进行检验
        }else{
            alert('你输入的身份证长度或格式错误');

        }

    });

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
    
    function checkboxOnclick() {
        var chk_value =[];//定义一个数组
        var total = 0
        $('input[name="chk"]:checked').each(function(){//遍历每一个名字为nodes的复选框，其中选中的执行函数
            chk_value.push($(this).val());//将选中的值添加到数组chk_value中
        });
        //var groups = chk_value.join(",");
        for(var i = 0; i < chk_value.length; i++) {
            total += Number(chk_value[i])
        }
        alert(total);
        $("#totalmoney").html(total);
        $("#totalmoney2").html(total);


    }


</script>
</body>
</html>

