<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">


<body data-type="index" class="theme-white">
    <div class="am-g tpl-g">
        <!-- 头部 -->
        <jsp:include page="common/header.jsp"></jsp:include>

        <!-- 侧边导航栏 -->
        <jsp:include page="common/left.jsp"></jsp:include>

        <!-- 内容区域 -->
        <div class="tpl-content-wrapper">

            <div class="row-content am-cf">
                <div class="row  am-cf">

                    <div class="row">

                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="widget am-cf">
                            <div class="widget-head am-cf">
                                <div class="widget-title am-fl">银行卡开户</div>
                                <div class="widget-function am-fr">
                                    <a href="javascript:;" class="am-icon-cog"></a>
                                </div>
                            </div>
                            <div class="widget-body am-fr">

                                <form class="am-form tpl-form-line-form" action="/card/openAccount.do" method="post" id="f1">
                                    <div class="am-form-group">
                                        <label for="pwd" class="am-u-sm-3 am-form-label">密码 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="password" class="tpl-form-input" id="pwd" name="pwd" placeholder="请输入6位银行卡密码">
                                            <small></small>
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <label for="conPwd" class="am-u-sm-3 am-form-label">确认密码 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="password" class="tpl-form-input" id="conPwd" name="conPwd" placeholder="请输入6位银行卡密码">
                                            <small></small>
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <div class="am-u-sm-9 am-u-sm-push-3">
                                            <button type="button" class="am-btn am-btn-primary tpl-btn-bg-color-success" onclick="f()">开户</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
                </div>
            </div>
        </div>
    </div>
    </div>

    <script src="/assets/js/amazeui.min.js"></script>
    <script src="/assets/js/amazeui.datatables.min.js"></script>
    <script src="/assets/js/dataTables.responsive.min.js"></script>
    <script src="/assets/js/app.js"></script>


</body>

<script type="text/javascript">
    function f() {
        $("#f1").ajaxSubmit({
            dataType: 'json',
            success: function (result) {
                if (result.code==1000) {
                    alert("开户成功,您的卡号为"+result.data);
                    window.location.href="/skip/index.do";
                } else {
                    alert(result.message);
                }
            }
        })
    }
</script>

</html>