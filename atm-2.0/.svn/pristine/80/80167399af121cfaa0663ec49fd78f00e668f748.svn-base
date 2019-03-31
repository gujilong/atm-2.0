<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<body data-type="index"  class="theme-white">

    <div class="am-g tpl-g" >
        <!-- 头部 -->
        <jsp:include page="common/header.jsp"></jsp:include>
        <!-- 侧边导航栏 -->
        <jsp:include page="common/left.jsp"></jsp:include>

        <!-- 内容区域 -->
        <div id="flow_list" class="tpl-content-wrapper">

            <div class="row-content am-cf">
                <div class="row  am-cf">
                    
                    <div class="row">

                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="widget am-cf">
                            <div class="widget-head am-cf">
                                <div class="widget-title am-fl">流水操作</div>
                                <div class="widget-function am-fr">
                                    <a href="javascript:;" class="am-icon-cog"></a>
                                </div>
                            </div>
                            <div class="widget-body am-fr">

                                <form id="form1" class="am-form tpl-form-line-form" action="/card/qflow.do" method="post">
                                <div class="am-form-group">
                                        <label for="cardSelect" class="am-u-sm-3 am-form-label">银行卡 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <select id="cardSelect" name="cardNo" data-am-selected="{searchBox: 1}" style="display: none;">

                                            </select>
                                            <button type="button" class="am-btn am-btn-default am-radius" v-on:click="seFlows()">查询</button>
                                            <button type="button" class="am-btn am-btn-default am-radius" onclick="f1()">下载</button>
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <label for="pwd" class="am-u-sm-3 am-form-label">密码 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="password" name="pwd" class="tpl-form-input" id="pwd" placeholder="请输入6位银行卡密码">
                                            <small></small>
                                        </div>
                                    </div>
                                </form>

                                <div class="widget am-cf">
                                <div class="widget-head am-cf">
                                <div class="widget-title am-fl"></div>
                                <div class="widget-function am-fr">
                                </div>
                            </div>
                            <div  v-if="pageUtil.totalPages != undefined"  class="widget-body  widget-body-lg am-fr">
                                <table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black " id="example-r">
                                    <thead>
                                        <tr>
                                            <th>卡号</th>
                                            <th>金额</th>
                                            <th>备注</th>
                                            <th>时间</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr v-for="flow in pageUtil.data" class="gradeX">
                                            <td v-text="flow.cardNum"></td>
                                            <td v-text="flow.amount"></td>
                                            <td v-text="flow.desc"></td>
                                            <td v-text="flow.createTime"></td>
                                        </tr>
                                        <!-- more data -->
                                    </tbody>
                                </table>
                                <ul  class="am-pagination">
                                  <li v-on:click="pageing(1)"><a href="javascript:void(0)">首页 </a></li>

                                    <li class="am-disabled" v-if="pageUtil.currentPageindex <= 1"><a href="javascript:void(0)">«</a></li>
                                    <li  v-if="pageUtil.currentPageindex > 1"  v-on:click="pageing(pageUtil.currentPageindex - 1)"><a href="#" >«</a></li>

                                    <li v-for="page in pageUtil.showPages" v-bind:class="{'am-active':(pageUtil.currentPageindex==page)}" >
                                        <a class="am-disabled" href="#" v-text="page" v-if="pageUtil.currentPageindex==page">1</a>
                                        <a href="#" v-text="page" v-else v-on:click="pageing(page)" >1</a>
                                    </li>

                                    <li class="am-disabled" v-if="pageUtil.currentPageindex >=pageUtil.totalPages "><a href="javascript:void(0)">»</a></li>
                                    <li  v-if="pageUtil.currentPageindex < pageUtil.totalPages" v-on:click="pageing(pageUtil.currentPageindex + 1)"><a href="#">»</a></li>


								  <li v-on:click="pageing(pageUtil.totalPages)"><a href="javascript:void(0)">尾页</a></li>
								  <li v-if="pageUtil.totalPages == undefined " >0/0</li>
                                  <li v-else v-text="pageUtil.currentPageindex+'/'+pageUtil.totalPages">1/20</li>
								</ul>
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
    </div>
    </div>
    <script src="/assets/js/amazeui.min.js"></script>
    <script src="/assets/js/amazeui.datatables.min.js"></script>
    <script src="/assets/js/dataTables.responsive.min.js"></script>
    <script src="/assets/js/app.js"></script>

</body>

<script>
    var app = new Vue({
        el:"#flow_list" ,
        data:{
            pageUtil: {}
        },
        methods:{
            seFlows:function () {
                f();
            },
            pageing:function (pageIndex) {
                f(pageIndex);
            }
        }
    })

    //查流水分页
    function f(pageIndex) {
        $("#form1").ajaxSubmit({
            dataType: 'json',
            data: {currentPageindex:pageIndex},//预期的服务器响应的数据类型。
            success: function (dataResult) {
                if (1000 == dataResult.code) {
                    app.pageUtil = dataResult.data;
                } else {
                    alert(dataResult.message);
                    return;
                }
            }
        })
    }



    //下载流水
    function f1() {
        // $.ajax({
        //     type: "post",
        //     dataType: "json",
        //     url: '/card/downloadFlows.do',
        //     data:{ cardNo:$("#card").text(),
        //            pwd:$("#pwd").val()},
        //     success: function (dataResult) {
        //         if (1000 == dataResult.code) {
        //
        //         }else{
        //             alert(dataResult.message);
        //             return false;
        //         }
        //     }
        // });

        window.location.href="/card/downloadFlows.do?cardNo="+$("#cardSelect").val() +"&pwd=" + $('#pwd').val();



    }



    // 获取银行卡号
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

</script>

</html>