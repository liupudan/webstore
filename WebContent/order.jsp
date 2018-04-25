<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chinasofti.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap-3.3.7-dist/css/bootstrap.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.2.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<title>订单</title>
<style type="text/css">
dl {
	margin-top: 30px;
	font-size: 20px;
}


dt, dd {
	height: 50px;
}

.price {
	color: red;
}

.proname {
	text-overflow: ellipsis;
	color: green;
}

.proLogo {
	display: block;
	width: 140px;
	height: 140px;
	margin: 24px auto;
	cursor: pointer;
}

.clearfix:nth-child(odd) {
	background: #F0F8FF;
}

.clearfix:nth-child(even) {
	background: #FFF8DC;
}

#orderDate {
	float: right;
	
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

.order {
	position: absolute;
	right: 164px;
	top: 5px;
	z-index: 10;
}

.home {
	position: absolute;
	right: 252px;
	top: 5px;
	z-index: 10;
</style>
</head>
<body>
	<a type="button" class="btn btn-info logout"
		href="#">注销</a>
	<a type="button" class="btn btn-info cart"
		href="#">查看购物车</a>
	<a type="button" class="btn btn-info order"
		href="#">我的订单</a>
	<a type="button" class="btn btn-info home"
		href="home.jsp">首页</a>
	<div class="container" style="margin-top:60px;">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<h3>
				<%
				Order order = (Order)request.getAttribute("order");
				%>
					<span id="orderId">产品购买数量：<%=order.getOrderNum() %></span><span
						id="orderDate">订单时间：<%=order.getOrderDate() %></span>
				</h3>
			</div>
		</div>
		<%
			ArrayList<ProOrder> proOrderList = (ArrayList<ProOrder>) request.getAttribute("proOrderList");
			ArrayList<Product> proList = (ArrayList<Product>) request.getAttribute("proList");
			for (int i = 0; i < proOrderList.size(); i++) {
				ProOrder proOrder = proOrderList.get(i);//中间表中商品的购买数量
				Product pro = proList.get(i);
		%>
		<div class="row clearfix">
			<div class="col-md-1 column"></div>
			<div class="col-md-2 column">
				<img class="proLogo" alt="140x140"
					src="img/<%=pro.getProductLogo() %>" />
			</div>
			<div class="col-md-8 column">
				<dl class="dl-horizontal">
					<dt style="width: 120px">商品名称：</dt>
					<dd class="proname">
						<p><%=pro.getProductName()%></p>
					</dd>
					<dt style="width: 120px">数量：</dt>
					<dd>
						<p><%=proOrder.getProNum()%></p>
					</dd>
					<dt style="width: 120px">单价：</dt>
					<dd class="price">
						<p>
							￥<%=proOrder.getProTotal()%></p>
					</dd>
				</dl>
			</div>
		</div>
		<%
			}
		%>
		<div class="row clearfix">
			<form id="form1" action="" method="post">
				<input type="hidden" value="<%=order.getOrderNum()%>">
				<div class="col-md-2 col-md-offset-8 column">
					<h3 class="text-left" style="color: red;">
						总计：￥<span id="total"><%=order.getOrderTotal() %></span>
					</h3>
				</div>
				<div class="col-md-2 column">
					<h4 class="text-center">
					
						<button class="btn btn-default" id="pay"><a href="PaySuccessServlet?orderjspo=<%=order.getOrderId() %>">支&nbsp;付</a></button>
						
					</h4>
				</div>
			</form>
		</div>
	</div>
</body>
</html>