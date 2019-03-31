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


    <div class="tpl-login">
        <div class="tpl-login-content">
            <div class="tpl-login-logo">
                <img alt="" src="${headimgurl }" width="160px" height="160px">
            </div>
            <form id="loginForm" class="am-form tpl-form-line-form" action="/Wx/wxBind.do" method="post">
                <div class="am-form-group">
                    <span>欢迎您：${ nickName }</span>
                </div>

                <div class="am-form-group">
                    <input type="text" class="tpl-form-input" id="user-name" name="userName" value="${userName}" placeholder="请输入账号" autocomplete="off">
                </div>

                <div class="am-form-group">
                    <input type="password" class="tpl-form-input" id="user-pwd" name="pwd"  placeholder="请输入密码">
                </div>

                <div class="am-form-group">
                    <button type="button" onclick="f()" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">绑定</button>
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
                }
            }
        })
    }
</script>

</html>