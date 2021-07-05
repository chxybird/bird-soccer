<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>登录界面</title>
    <style>
        body{
            background: url('../img/login.jpg');
            background-size: cover;
            height:600px;
        }
        .container{
            width: 1200px;
            height: 580px;
            margin: 0 auto;
        }
        .code{
            font-family: arial;
            font-style: italic;
            font-weight: bold;
            border: 0;
            letter-spacing: 3px;
            color: blue;
        }
        .yz{
            margin: 5px 0 5px 42px;
            font-size: 17px;
            color: #6a6f77;
        }
        .nav{
            margin-top: 10px;
            height: 50px;
            line-height: 50px;
        }
        .nav ul li{
            float: right;
            font-size: 15px;
        }
        .nav ul li a{
            text-decoration: none;
            color: #152b3c;
            padding: 15px 30px;
        }
        .nav ul li a:hover{
            border:1px solid white;
            border-radius: 5%;
            color: white;
        }
        .main{
            width: 1200px;
            height: 500px;
        }
        .sideleft{
            width: 460px;
            height: 250px;
            float: left;
            padding: 85px 130px;
        }
        .sideleft p{
            padding: 8px 0;
            font-size: 16px;
        }
        .sideright{
            width: 480px;
            height: 600px;
            float: right;
        }
        .sideright .index{
            width: 330px;
            height:510px;
            background-color:rgba(255,255,255,0.75) ;
            margin: 50px 90px;
        }
        .headline{
            font-size: 22px;
            text-align: center;
            padding: 20px;
        }
        input[type="text"],
        input[type="password"],
        input[type="submit"]{
            -web-kit-appearance:none;
            -moz-appearance: none;
            display: block;
            margin: 0 auto;
            font-size: 15px;
            width: 240px;
        }
        input[type="text"]{
            height: 35px;
            border-radius: 3px;
            border: 1px solid #c8cccf;
            color: #6a6f77;
            outline: 0;
        }
        input[type="password"]{
            height: 35px;
            border-radius: 3px;
            border: 1px solid #c8cccf;
            color: #6a6f77;
            outline: 0;
        }
        input[type="submit"]{
            margin-top: 25px;
            height: 35px;
            background: #bcedff;
            font-weight: bold;
            font-size: 16px;
        }
        input[type="submit"]:hover{
            background: #0e62a3;
            cursor: pointer;
        }
        .astyle{
            margin: 5px 0 5px 42px;
            font-size: 17px;
            color: #6a6f77;
        }
        .bstyle{
            display: block;
            float:left ;
            margin-right: 46px;
            font-size: 15px;
        }
        .cstyle{
            display: block;
            float: right;
            margin-right: 46px;
            font-size: 15px;
        }
        .cstyle a{
            text-decoration: none;
        }
        .footer{
            width: 1000px;
            margin: 60px auto;
            margin-bottom: 10px;
        }
        .footer ul{
            margin-left: 100px;
        }
        .footer ul li{
            float: left;
        }
        .footer ul li a{
            text-decoration: none;
            color: black;
            border-left: 2px solid black;
            padding: 0 10px;
        }
    </style>

    <script type="text/javascript" src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#form").submit(function(){
                var result=true;
                if(!checkCode()) result=false;
                if(!checkUsername()) result=false;
                if(!checkPasswrd()) result=false;
                return result;
            });
        });

        var code;
        window.onload=function(){
            createCode();
        }
        function createCode(){
            code="";
            var codeLength=5;
            var Code=$("#Code").val();
            var random=new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J',
                'K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');
            for(var i=0;i<codeLength;i++){
                var charIndex=Math.floor(Math.random()*36);
                code+=random[charIndex];
            }
            $("#Code").html(code);
            Code.val(code);
        }
        function checkCode(){
            var inputCode=$("#input").val().toUpperCase();
            if(inputCode.length==0){
                $("#code_message").html("请输入验证码！")
                return false;
            }else if(inputCode!=code){
                $("#code_message").html("验证码输入错误")
                return false;
            }else{
                $("#code_message").html("");
                return true;
            }
        }

        function checkUsername() {
            var username=$("#username").val();
            if(username==''){
                $("#username_message").html("账号不能为空！");
                return false;
            }else if(username.length>10||username.length<0){
                $("#username_message").html("账号输入字符必须在[1-9]之间");
                return false;
            }else{
                $("#username_message").html("");
                return true;
            }
        }

        function checkPasswrd() {
            var password=$("#password").val();
            if(password==''){
                $("#password_message").html("密码不能为空！");
                return false;
            }else{
                $("#password_message").html("");
                return true;
            }
        }
    </script>
</head>


<body>
<div class="container">
    <div class="main">
        <div class="sideright">
            <div class="index">
                <form action="/login" id="form" method="post">
                    <p class="headline">用户登录</p>
                    <p class="astyle">账号：</p>
                    <input type="text" id="username" name="username" placeholder="请输入您的账号"/>
                    <span id="username_message" name="username_message" style="color: red"></span>
                    <p class="astyle">密码：</p>
                    <input type="password" id="password" name="password" placeholder="请输入密码"/>
                    <span id="password_message" name="password_message" style="color: red"></span>
                    <div>
                        <p class="astyle">验证码：</p>
                        <div class="yz">
                            <span id="Code" name="Code" class="code"></span>
                            <a href="#" onclick="createCode()">看不清</a>
                            <span id="code_message" style="color:red"></span>
                        </div>
                        <input type="text" id="input" placeholder="请输入验证码"/>
                    </div>

                    <input id="submit" type="submit" value="登录" name="login" /><br/>
                    <p class="cstyle"><a href="">忘记密码</a></p>
                    <br/><br/>
                    <p class="cstyle">没有账号？<a href="${pageContext.request.contextPath}/pages/register.jsp">立即注册</a></p>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
