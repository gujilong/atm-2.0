<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<div class="left-sidebar">
    <!-- 用户信息 -->
    <div class="tpl-sidebar-user-panel">
        <div class="tpl-user-panel-slide-toggleable">
            <form id="fpic" enctype="multipart/form-data" method="post" target="buddha">
                <div class="am-form-group am-form-file">
                    <div class="tpl-user-panel-profile-picture">
                        <img id="pic" src="/user/showUserbuddha.do" alt="">
                    </div>
                    <input id="doc-form-file" type="file" name="avatar" onchange="upPa();">
                </div>
            </form>
            <a href="javascript:;" class="tpl-user-panel-action-link"> <span class="am-icon-pencil"></span> 账号设置</a>
        </div>
    </div>

    <ul class="sidebar-nav">

        <c:if test="${active=='首页'}">
            <li class="sidebar-nav-link" >
                <a href="/skip/index.do"  class="active" >
                    <i class="am-icon-home sidebar-nav-link-logo"></i> 首页
                </a>
            </li>
        </c:if>
        <c:if test="${active!='首页'}">
            <li class="sidebar-nav-link">
                <a href="/skip/index.do"  target=blank>
                    <i class="am-icon-home sidebar-nav-link-logo"></i> 首页
                </a>
            </li>
        </c:if>




        <c:if test="${active=='开户'}">
            <li class="sidebar-nav-link">
                <a href="/skip/openAccount.do"  class="active" >
                    <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 开户
                </a>
            </li>
        </c:if>
        <c:if test="${active!='开户'}">
            <li class="sidebar-nav-link">
                <a href="/skip/openAccount.do" >
                    <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 开户
                </a>
            </li>
        </c:if>




        <c:if test="${active=='取款'}">
            <li class="sidebar-nav-link">
                <a href="/skip/todraw.do" class="active" >
                    <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 取款
                </a>
            </li>
        </c:if>
        <c:if test="${active!='取款'}">
            <li class="sidebar-nav-link">
                <a href="/skip/todraw.do" >
                    <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 取款
                </a>
            </li>
        </c:if>



        <c:if test="${active=='存款'}">
            <li class="sidebar-nav-link">
                <a href="/skip/deposit.do" class="active"   >
                    <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 存款
                </a>
            </li>
        </c:if>
        <c:if test="${active!='存款'}">
            <li class="sidebar-nav-link">
                <a href="/skip/deposit.do"  >
                    <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 存款
                </a>
            </li>
        </c:if>



        <c:if test="${active=='转账'}">
            <li class="sidebar-nav-link">
                <a href="/skip/transfer.do"  class="active"   >
                    <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 转账

                </a>
            </li>
        </c:if>
        <c:if test="${active!='转账'}">
            <li class="sidebar-nav-link">
                <a href="/skip/transfer.do"   >
                    <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 转账

                </a>
            </li>
        </c:if>


        <c:if test="${active=='流水'}">
            <li class="sidebar-nav-link">
                <a href="/skip/qflow.do"  class="active"   >
                    <i class="am-icon-bar-chart sidebar-nav-link-logo"></i> 流水

                </a>
            </li>
        </c:if>
        <c:if test="${active!='流水'}">
            <li class="sidebar-nav-link">
                <a href="/skip/qflow.do"   >
                    <i class="am-icon-bar-chart sidebar-nav-link-logo"></i> 流水
                </a>
            </li>
        </c:if>
    </ul>
</div>

<%--<iframe name="buddha" src="https://taobao.com" style="display: none"></iframe>--%>
<%--会出现--%>
<%--(index):1 Uncaught (in promise) DOMException: Only secure origins are allowed--%>
<iframe name="buddha" style="display: none"></iframe>

<script >

    function upPa() {
        $('#fpic').attr('action', '/user/upUserbuddha.do');
        $("#fpic").submit();
    }

    function showP(url) {
        $("#pic").attr("src",url+ "?" + new Date().getTime());
    }

</script>