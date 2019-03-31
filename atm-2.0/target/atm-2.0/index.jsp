<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Amaze UI Admin index Examples</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="/assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="/assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="/assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="/assets/css/amazeui.datatables.min.css" />
    <link rel="stylesheet" href="/assets/css/app.css">
    <script src="/assets/js/jquery.min.js"></script>
    <script src="/js/jquery.form.min.js"></script>

</head>

<body data-type="login" class="theme-white">

    <div class="am-g tpl-g">
        <!-- 风格切换 -->
        <div class="tpl-skiner">
            <div class="tpl-skiner-toggle am-icon-cog">
            </div>
            <div class="tpl-skiner-content">
                <div class="tpl-skiner-content-title">
                    选择主题
                </div>
                <div class="tpl-skiner-content-bar">
                    <span class="skiner-color skiner-white" data-color="theme-white"></span>
                    <span class="skiner-color skiner-black" data-color="theme-black"></span>
                </div>
            </div>
        </div>
        <div class="tpl-login">
            <div class="tpl-login-content">
                <div class="tpl-login-logo">
                </div>
                <form id="loginForm" class="am-form tpl-form-line-form" action="/user/login.do" method="post">
                    <div class="am-form-group">
                        <input type="text" class="tpl-form-input" id="user-name" name="userName" value="${userName}" placeholder="请输入账号" autocomplete="off">
                    </div>

                    <div class="am-form-group">
                        <input type="password" class="tpl-form-input" id="user-pwd" name="pwd"  placeholder="请输入密码">
                    </div>

                    <div class="am-form-group">
                        <input type="text" class="tpl-form-input" id="captcha" name="captcha" placeholder="请输入验证码">
                        <img src="/getCaptcha.do" id="captchaImg">
                    </div>
                    <div class="am-form-group">

                        <button type="button" onclick="f()" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">登录</button>
                        <button type="button" onclick="Wx()" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">微信登录</button>
                        <button type="button" onclick="window.location.href='/skip/tologinUp.do'" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">注册</button>


                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="/assets/js/amazeui.min.js"></script>
    <script src="/assets/js/app.js"></script>

</body>

<script type="text/javascript">

    function f() {
        $("#loginForm").ajaxSubmit({
            dataType: 'json',
            success: function (result) {
                if (result.code==1000) {
                    window.location.href="/skip/index.do";
                } else {
                    alert(result.message);
                    $("#captchaImg").attr('src', '/getCaptcha.do?' + new Date().getTime());
                }
            }
        })
    }

    function Wx() {
        $.ajax({
            type: "post",
            dataType: "json",
            url: '/Wx/getCodeUrl.do',
            success: function (dataResult) {
                if (1000 == dataResult.code) {
                    window.location.href= dataResult.data;
                }else{
                    alert(dataResult.message);
                    return false;
                }
            }
        });

    }
    
    function x() {
        
    }

</script>

</html>