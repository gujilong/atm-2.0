<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>老人健康情况</title>
<meta name="decorator" content="default" />
<script type="text/javascript">

<style type="text/css">
#contentTable th {
	text-align: center
}

#contentTable td {
	text-align: center;
	width: 100px
}
</style>
<!-- ECharts单文件引入 -->
<script src="/js/jquery.min.js"></script>
<script src="/js/echarts.js"></script>
<script type="text/javascript">
	$(function() {
		// 基于准备好的dom，初始化echarts实例
		var myChart1 = echarts.init(document.getElementById('main1'));
		var myChart2 = echarts.init(document.getElementById('main2'));
		
		// 1.身体健康结构
		var option1 = {
			title : {
				text : '身体健康结构',
				x : 'center'
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			series : [ {
				name : '身体健康结构',
				type : 'pie',
				radius : '60%',
				center: ['45%', '50%'],
				data :  ${cardOldmenInfoSelfcares},
				label : {
					normal : {
						textStyle : {
						fontWeight : 'normal',
						fontSize : 15
						}
					}
				},
				itemStyle : {
					emphasis : {
						shadowBlur : 10,
						shadowOffsetX : 0,
						shadowColor : 'rgba(0, 0, 0, 0.5)'
					}
				}
			} ]
		};
		//2.月收入分级
		var option2 = {
			title : {
				text : '月收入分级',
				x : 'center'
			},
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			legend : {
				data : [ '半自理', '不能自理','完全自理'],
				top : '10%'
			},
			grid : {
				left : '2%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				 data : [ '0元', '500元以下','501-2000元','2001-3000元', '3000-5000元', '5000以上'],
				"axisLabel" : {
					interval : 0,
					rotate : 15,
					textStyle : {
						color : "blue",
						fontSize : 10
					}
				},
			} ],
			yAxis : [ {
				type : 'value',
				name: '人数(人)'
			} ],
			series : [ {
				name : '半自理',
				type : 'bar',
				stack : '1',
				data : ${incomeBanCountList}
			},{
				name : '不能自理',
				type : 'bar',
				stack : '1',
				data : ${incomeBuCountList}
			},{
				name : '完全自理',
				type : 'bar',
				stack : '1',
				data : ${incomeWanCountList}
			}]
		};
		
		// 为echarts对象加载数据
		myChart1.setOption(option1, true);
		myChart2.setOption(option2, true);
	});
</script>
</head>
<body>

	<!-- 为ECharts准备一个具备大小（宽高）的Dom 保持在一行display:inline-block-->
	<div align="center">
		<div id="main1" style="height: 400px; width: 500px; "></div>
		<div style="width:90%;border-top:5px solid #476984" ></div>
		<div id="main2" style="margin-top:70px;height: 400px; width: 500px;"></div>
		<div style="margin-top:70px;width:90%;border-top:5px solid #476984" ></div>
	</div>
</body>
</html>

