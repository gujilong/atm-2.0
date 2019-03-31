<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<body data-type="index"  class="theme-white">

    <div   class="am-g tpl-g" >
        <!-- 头部 -->
        <jsp:include page="common/header.jsp"></jsp:include>
        <!-- 风格切换 -->

        <!-- 侧边导航栏 -->
        <jsp:include page="common/left.jsp"></jsp:include>

        <!-- 内容区域 -->
        <div id="flow_lis"  class="tpl-content-wrapper">

            <div class="row-content am-cf">
                <div class="row  am-cf">
                    <div v-for="card in cards" class="am-u-sm-12 am-u-md-6 am-u-lg-4" style="float:left;">
                        <div  class="widget widget-primary am-cf">
                            <div class="widget-statistic-header">
                                <p v-text="card.cardNum">6222*****196</p>
                            </div>
                            <div class="widget-statistic-body">
                                <div class="widget-statistic-value">
                                    <p v-text="'￥'+card.balance"> ￥27,294</p>
                                </div>
                                <div class="widget-statistic-description">

                                </div>
                                <span class="widget-statistic-icon am-icon-credit-card-alt"></span>
                            </div>
                        </div>
                    </div>
                </div>

                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-6">
                        <div class="widget am-cf">
                            <div class="widget-head am-cf">
                                <div class="widget-title am-fl">最近十笔流水</div>
                                <div class="widget-function am-fr">
                            </div>
                            </div>
                            <div class="widget-body  widget-body-lg am-fr">

                                <table width="100%" class="am-table am-table-compact am-table-bordered am-table-radius am-table-striped tpl-table-black " id="example-r">
                                    <thead>
                                        <tr>
                                            <th>卡号</th>
                                            <th>金额</th>
                                            <th>备注</th>
                                            <th>时间</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr v-for="flow in flows" class="gradeX">
                                            <td v-text="flow.cardNum"></td>
                                            <td v-text="flow.amount"></td>
                                            <td v-text="flow.desc"></td>
                                            <td v-text="flow.createTime"></td>
                                        </tr>
                                        <!-- more data -->
                                    </tbody>
                                </table>

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
    var app = new Vue({
        el:"#flow_lis",
        data:{
            flows: {},
            cards:{},
        }
    })





    $(document).ready(function() {
        $.ajax({
            type: "post",
            dataType: "json",
            url: '/card/qTenflow.do',
            success: function (dataResult) {
                if (1000 == dataResult.code) {
                    app.flows = dataResult.data;
                }else{
                    alert(dataResult.message);
                    return false;
                }
            }
        });


        $.ajax({
            type: "post",
            dataType: "json",
            url: '/card/cardInf.do',
            success: function (dataResult) {
                if (1000 == dataResult.code) {
                    app.cards = dataResult.data;
                }else{
                    alert(dataResult.message);
                    return false;
                }
            }
        });

    });




    var asd = new Vue({
        el:"",
        data:{

            names:{},
            sexs:{},
        }
    })

</script>

</html>