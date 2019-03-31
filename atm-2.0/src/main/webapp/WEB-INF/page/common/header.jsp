<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
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
    <script src="/assets/js/echarts.min.js"></script>
    <link rel="stylesheet" href="/assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="/assets/css/amazeui.datatables.min.css" />
    <link rel="stylesheet" href="/assets/css/app.css">
    <script src="/assets/js/jquery.min.js"></script>
    <script src="/js/jquery.form.min.js"></script>
    <script src="/js/vue.js"></script>
</head>
<header>
    <!-- logo -->
    <div class="am-fl tpl-header-logo">
        <a href="javascript:;">大猿ATM</a>
    </div>
    <!-- 右侧内容 -->
    <div class="tpl-header-fluid">
        <!-- 侧边切换 -->

        <!-- 搜索 -->

        <!-- 其它功能-->
        <div class="am-fr tpl-header-navbar">
            <ul>
                <!-- 欢迎语 -->
                <%--<c:if test="${sessionScope.nickName} == '' ">--%>
                <%--<li class="am-text-sm tpl-header-navbar-welcome">--%>
                <%--<a href="javascript:;">欢迎你, <span id="userName">---</span> </a>--%>
                <%--</li>--%>
                <%--</c:if>--%>
                <%--<c:if test="${sessionScope.nickName }!=''">--%>
                <%--<li class="am-text-sm tpl-header-navbar-welcome">--%>
                <%--<a href="javascript:;">欢迎你, <span>${nickName}</span> </a>--%>
                <%--</li>--%>
                <%--</c:if>--%>

                    <li class="am-text-sm tpl-header-navbar-welcome">
                        <a href="javascript:;">欢迎你, <span id="userName">---</span> </a>
                    </li>







                <!-- 新邮件 -->

                <!-- 新提示 -->
                <li class="am-dropdown" data-am-dropdown>
                    <a href="javascript:;" class="am-dropdown-toggle" data-am-dropdown-toggle>
                        <i class="am-icon-bell"></i>
                        <span class="am-badge am-badge-warning am-round item-feed-badge">5</span>
                    </a>

                    <!-- 弹出列表 -->
                    <ul class="am-dropdown-content tpl-dropdown-content">
                        <li class="tpl-dropdown-menu-notifications">
                            <a href="javascript:;" class="tpl-dropdown-menu-notifications-item am-cf">
                                <div class="tpl-dropdown-menu-notifications-title">
                                    <i class="am-icon-line-chart"></i>
                                    <span> 有6笔新的销售订单</span>
                                </div>
                                <div class="tpl-dropdown-menu-notifications-time">
                                    12分钟前
                                </div>
                            </a>
                        </li>
                        <li class="tpl-dropdown-menu-notifications">
                            <a href="javascript:;" class="tpl-dropdown-menu-notifications-item am-cf">
                                <div class="tpl-dropdown-menu-notifications-title">
                                    <i class="am-icon-star"></i>
                                    <span> 有3个来自人事部的消息</span>
                                </div>
                                <div class="tpl-dropdown-menu-notifications-time">
                                    30分钟前
                                </div>
                            </a>
                        </li>
                        <li class="tpl-dropdown-menu-notifications">
                            <a href="javascript:;" class="tpl-dropdown-menu-notifications-item am-cf">
                                <div class="tpl-dropdown-menu-notifications-title">
                                    <i class="am-icon-folder-o"></i>
                                    <span> 上午开会记录存档</span>
                                </div>
                                <div class="tpl-dropdown-menu-notifications-time">
                                    1天前
                                </div>
                            </a>
                        </li>


                        <li class="tpl-dropdown-menu-notifications">
                            <a href="javascript:;" class="tpl-dropdown-menu-notifications-item am-cf">
                                <i class="am-icon-bell"></i> 进入列表…
                            </a>
                        </li>
                    </ul>
                </li>

                <!-- 退出 -->
                <li class="am-text-sm">
                    <a href="/user/out.do">
                        <span class="am-icon-sign-out"> 退出</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>

</header>
<script >
    $(document).ready(function() {
        <%--var name = '${nickName}';--%>
       // alert(name != '');
        $.ajax({
            type: "post",
            dataType: "json",
            url: '/user/getUserName.do',
            success: function (dataResult) {
                if (1000 == dataResult.code) {
                    $("#userName").html(dataResult.data);


                    // if(name == ''){
                    //     $("#userName").html(dataResult.data);
                    // }else{
                    //     $("#userName").html(name);
                    // }

                }else{
                    alert(dataResult.message);
                    return false;
                }
            }
        });
    });
</script>

<script>
    var msgCount = 0;
    var ws = new WebSocket("ws://192.168.2.245:8088/transferInfo.do");
    ws.onopen = function(evt) {
        console.log("websocket建立成功 ...");
        ws.send("Hello atm WebSockets!");
    };

    ws.onmessage = function(evt) {
        console.log( "接收服务器发送的消息: " + evt.data);
        msgCount ++;
        $('#msgNum').html(msgCount);
        // $('#horseMic').attr('src', '/video/horse.mp3');
        $('#horseMic').attr('src', '/video/abc2.mp4');
    };

    ws.onclose = function(evt) {
        console.log("websocket 关闭.");
    };
</script>