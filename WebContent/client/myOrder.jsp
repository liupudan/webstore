<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<title>我的订单</title>
<style>
.orderNum {
	cursor: pointer;
}

.orderNum:hover {
	color: blue;
}

.order_tr {
	font-size: 18px;
}

.logout {
	position: absolute;
	right: 5px;
	top: 5px;
	z-index: 10;
}

.cart {
	position: absolute;
	right: 64px;
	top: 5px;
	z-index: 10;
}

.home {
	position: absolute;
	right: 164px;
	top: 5px;
	z-index: 10;
}

.personal {
	position: absolute;
	right: 222px;
	top: 5px;
	z-index: 10;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	loadInit();
});
function loadInit(){
	var layer_all = "";
	$.ajax({
		type:"GET",
		url:"${pageContext.request.contextPath }/MyOrderServlet",
		dataType:"json",
		success:function(data){
			if(data != ''){
				for(var i=0; i<data.length; i++){
					layer_all += '<tr class="order_tr"><td class="orderNum">'+
						data[i].orderNum + '</td><td style="color:red;">￥'+
						data[i].total + '</td><td>'+
						data[i].orderDate + '</td><td>'+
						data[i].userName + '</td><td>'+
						data[i].orderStatus + '</td><td>';
						if(data[i].orderStatus == "未支付") {
						lay = '<button class="btn btn-default" id="pay"><a href="/webstore/PaySuccessServlet?orderjspo='+data[i].orderId+'">支&nbsp;付</a></button></td></tr>';
						layer_all += lay;
						}
				}
				$("#all_tbody").empty();
				$("#all_tbody").append(layer_all);
			}else{
				$("#all_tbody").empty();
				$("#all_tbody").append('<tr><td colspan="4"><h3>您还没有任何订单！</h3></td></tr>');
			}
		}
	});
}
</script>
</head>
<body>
	<div class="container">
		<a type="button" class="btn btn-info logout"
			href="#">注销</a> <a
			type="button" class="btn btn-info cart"
			href="${pageContext.request.contextPath}/cart.jsp">查看购物车</a> <a
			type="button" class="btn btn-info home"
			href="${pageContext.request.contextPath}/transferHome.do">首页</a> <a
			type="button" class="btn btn-info personal"
			href="${pageContext.request.contextPath}/drawChart.do">个人中心</a>
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="tabbable" id="tabs-761375">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#panel-all" data-toggle="tab">全部订单</a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="panel-all">
							<table class="table">
								<thead>
									<tr>
										<th>订单商品件数</th>
										<th>总价</th>
										<th>订单时间</th>
										<th>用户</th>
										<th>支付状态</th>
									</tr>
								</thead>
								<tbody id="all_tbody">

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>