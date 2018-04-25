<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.*" %>
	<%@ page import="com.chinasofti.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css" />
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<style>
.home {
	position: absolute;
	right: 5px;
	top: 5px;
	z-index: 10;
}
</style>
</head>
<body>
<%
	String proName = (String)request.getAttribute("proLogoName");
	Cookie[] cookies = request.getCookies();
	ArrayList<Cookie> list = new ArrayList();
	Cookie tempCookie = null;
	for(Cookie coo : cookies) {
		if(coo.getName().startsWith("product_")) {
			String value = coo.getValue();
			list.add(coo);
			if(value.equals(proName)) {
				tempCookie = coo;
				/* coo.setMaxAge(0);
				response.addCookie(coo); */
			}
		}
	}
	if(list.size() >= 5 && tempCookie == null) {
		list.get(0).setMaxAge(0);
		response.addCookie(list.get(0));
	}
	Cookie cookie = new Cookie(("product_" + proName),proName);
	response.addCookie(cookie);
	Product pro = (Product)request.getAttribute("product");
%>
	<a type="button" class="btn btn-info logout"
		href="TransferHomeServlet">首页</a>
	<a type="button" class="btn btn-info personal"
		href="#">个人中心</a>
	<table style="text-align: center; margin: 0 auto; width: 60%">
		<tr>
			<td rowspan="5"><img src="img/<%=proName %>"
				width="250" height="250" /></td>
		</tr>
		<tr>
			<td><B><%=pro.getProductName() %></B></td>
		</tr>
		<tr>
			<td>产地：<%=pro.getProductCity() %></td>
		</tr>
		<tr>
			<td>价格￥：<%=pro.getProductPrice() %></td>
		</tr>
		<tr>
			<td>购买数量：<%=pro.getProductCount() %> <input type="button" id="sub" 
				value="-" onclick="reduce()"> <input type="text" id="number" name="number"
				value="1" size="2" /> <input type="button" id="add"value="+" onclick="add()"></td>
		</tr>
	</table>
	<div id="cart" style="text-align: center; margin: 0 auto; width: 60%">
		<span>浏览次数： </span> 
		<a id="a" onclick="addCart(<%=pro.getId()%>,<%=pro.getProductPrice()%>)"> <img
			src="img/in_cart.png"></a> 
	</div>
	<script type="text/javascript">
		var inputNum = document.getElementById("number");
		var xmlHttp;
		function createXMLHttpRequest() {
			if (window.ActiveXObject) {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} else if (window.XMLHttpRequest) {
				xmlHttp = new XMLHttpRequest();
			}
		}
		function reduce() {	
			var num = inputNum.value;
			if(num > 1) {
				inputNum.value = --num;
			}
		}
		function add() {
			var num = inputNum.value;
			if(num < 100) {
				inputNum.value = ++num;
			}
		}
		function addCart(pid,price) {
			document.getElementById("a").href = "CartServlet?pid="+pid+"&price="+price+"&count="+inputNum.value;
			/* createXMLHttpRequest();
			var url = "CartServlet?pid="+pid+"&price="+price+"&count="+inputNum.value;
			xmlHttp.open("GET",url,true);
			xmlHttp.send(null);  */
		}
		
	</script>
</body>
</html>