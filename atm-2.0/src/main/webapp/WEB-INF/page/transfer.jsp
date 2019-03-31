<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html>
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
                                <div class="widget-title am-fl">转账操作</div>
                                <div class="widget-function am-fr">
                                    <a href="javascript:;" class="am-icon-cog"></a>
                                </div>
                            </div>
                            <div class="widget-body am-fr">
                                <form id="form1" class="am-form tpl-form-line-form" action="/card/transfer.do" method="post">
                                <div class="am-form-group">
                                    <label for="cardSelect" class="am-u-sm-3 am-form-label">银行卡 <span class="tpl-form-line-small-title"></span></label>
                                    <div class="am-u-sm-9">
                                        <select id="cardSelect" name="outCardNo" data-am-selected="{searchBox: 1}" style="display: none;">
                                        </select>
                                    </div>
                                </div>

                                    <div class="am-form-group">
                                        <label for="inCardNo" class="am-u-sm-3 am-form-label">转入卡号 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="text" class="tpl-form-input" id="inCardNo" name="inCardNo" autocomplete="off" placeholder="请输入收款卡号">
                                            <small></small>
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <label for="money" class="am-u-sm-3 am-form-label">金额 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="text" class="tpl-form-input" id="money" name="money" autocomplete="off" placeholder="请输入转账金额">
                                            <small></small>
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <label for="pwd" class="am-u-sm-3 am-form-label">密码 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="password" class="tpl-form-input" id="pwd" name="pwd" autocomplete="off" placeholder="请输入6位银行卡密码">
                                            <small></small>
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <div class="am-u-sm-9 am-u-sm-push-3">
                                            <button type="button" class="am-btn am-btn-primary tpl-btn-bg-color-success" onclick="commit()">转账</button>
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
<script>
    $(document).ready(function() {
        $.ajax({
            type: "post",
            dataType: "json",
            url: '/card/findcardNo.do',
            success: function (dataResult) {
                if (1000 == dataResult.code) {
                    var resutls = dataResult.data;
                    var msg = '<option value="no">请选择银行卡</option>';
                    for (var i=0; i<resutls.length; i++) {
                        msg += '<option value="'+resutls[i]+'">'+resutls[i]+'</option>';
                    }
                    $('#cardSelect').html(msg);
                }else{
                    alert(dataResult.message);
                    return false;
                }
            }
        });
    });



    function commit() {
        $("#form1").ajaxSubmit({
            dataType: "json",
            success: function (dataResult) {
                if (1000 == dataResult.code) {
                    alert("转账成功,1-2小时到账");
                    window.location.href="/skip/index.do";
                }else{
                    alert(dataResult.message);
                }
            }
        })
    }
</script>



</html>