<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="com.chinasofti.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css" />
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jqpaginator.min.js"></script>
<style type="text/css">
h2 {
	text-align: center;
}

#pic_top {
	display: block;
	margin: 10px auto;
	width: 350px;
	height: 125px;
}

#main {
	width: 1080px;
	height: 800px;
	margin: 0 auto;
}

#main ul {
	overflow: hidden;
	list-style: none;
}

#main li {
	float: left;
	margin: 10px 10px;
	width: 240px;
	height: 372px;
	border: 1px solid #777777;
}

#main li:hover {
	border: 2px solid coral;
}

.item_url:hover {
	text-decoration: none;
}

.item_url:hover .name {
	text-decoration: underline;
}

.price {
	font-size: 18px;
	color: red;
}

.count {
	color: #AAA;
	float: right;
}

.name {
	height: 60px;
	margin: 10px 0;
}

.city {
	color: #BBB;
	float: right;
}

.viewCount {
	color: #BBB;
}

.pic {
	width: 236px;
	height: 236px;
}

.context {
	padding: 0 8px;
	padding-top: 8px;
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

.personal {
	position: absolute;
	right: 252px;
	top: 5px;
	z-index: 10;
}

#time {
	color: limegreen;
}

#search_result {
	display: none;
	width: 499px;
	position: absolute;
	z-index: 1000;
	top: 34px;
	overflow: hidden;
	background: #fff;
	border: 1px solid #ccc;
	border-top: none;
}

.line {
	cursor: pointer;
	padding-left: 10px;
}
</style>
</head>
<body>
<%
	String access_count = (String)application.getAttribute("access_count");
	int count = 1;
	if(access_count != null) {
		count = Integer.parseInt(access_count) + 1;
	}
	application.setAttribute("access_count", String.valueOf(count));
%>
	<a type="button" class="btn btn-info logout" href="#">注销</a>
	<a type="button" class="btn btn-info cart" href="#">查看购物车</a>
	<a type="button" class="btn btn-info order" href="client/myOrder.jsp">我的订单</a>
	<a type="button" class="btn btn-info personal" href="DrawChartServlet">个人中心</a>
	你是第<%=count %>个访问者
	<img id="pic_top" src="img/hai.gif" />
	<h2>欢迎登录，元宵节来啦，快选购商品吧！</h2>
	<div style="padding: 10px 100px 10px; margin: 10px auto; width: 800px;">
		<form class="bs-example bs-example-form" role="form"
			style="width: 100%;" id="form1">
			<div class="row">
				<div class="col-lg-12">
					<div class="input-group">
						<span class="input-group-btn"> <a class="btn btn-default"
							type="button" id="showAllPro"> 全部 </a>
						</span> <input type="text" class="form-control" name="keyName"
							id="search" onfocus="getMore()" onblur="clearContent()"/>
							<div style="width:494 px;height:auto;position:absolute;top:40px;left:50px;border:1px solid red;background-color:red;">
								<table id="content_table" bgcolor="#fffafa" style="width:494px;">
									<tbody id="content_table_body">
									</tbody>
								</table>
							</div>
							 <span class="input-group-btn"> <input
							type="button" id="searchGo" class="btn btn-default" value="搜索" />
						</span>
					</div>
				</div>
			</div>
		</form>
	</div>

	<div id="main">
	<!-- 	<ul>
			<li><a href="details.jsp?proName=lingshi1.png" class="item_url">
					<img class="pic" src="img/lingshi1.png" />
					<div class="context">
						<span class="price">￥ 26</span> <span class="count">234人付款</span>
						<p class="name">雀巢脆脆鲨巧克力味夹心威化24+8条休闲饼干新老包装随机发2倍购</p>
						<span class="viewCount">浏览量：346</span> <span class="city">北京</span>
					</div>
			</a></li>
			<li><a href="details.jsp?proName=sanzhi.jpg" class="item_url">
					<img class="pic" src="img/sanzhi.jpg" />
					<div class="context">
						<span class="price">￥ 11</span> <span class="count">22234人付款</span>
						<p class="name">【三只松鼠_小团圆月饼160g】传统特产中秋台式月饼小礼盒2只装 中秋新潮礼 300款零食
							限1件</p>
						<span class="viewCount">浏览量：2032</span> <span class="city">芜湖</span>
					</div>
			</a></li>
			<li><a href="details.jsp?proName=lingshi2.png" class="item_url">
					<img class="pic" src="img/lingshi2.png" />
					<div class="context">
						<span class="price">￥ 19</span> <span class="count">634人付款</span>
						<p class="name">印尼进口零食richeese丽芝士奶酪威化饼干nabati纳宝帝玉米棒</p>
						<span class="viewCount">浏览量：20032</span> <span class="city">北京</span>
					</div>
			</a></li>
			<li><a href="details.jsp?proName=bao1.png" class="item_url"> <img
					class="pic" src="img/bao1.png" />
					<div class="context">
						<span class="price">￥ 190</span> <span class="count">3264人付款</span>
						<p class="name">2017新款链条包时尚迷你小包包女包百搭韩版潮牛皮单肩斜挎小方包</p>
						<span class="viewCount">浏览量：201032</span> <span class="city">北京</span>
					</div>
			</a></li>
			<li><a href="details.jsp?proName=mugua.png" class="item_url"> <img
					class="pic" src="img/mugua.png" />
					<div class="context">
						<span class="price">￥ 26</span> <span class="count">934人付款</span>
						<p class="name">海南红心木瓜 水果 新鲜 包邮 三亚特产夏威夷现摘冰糖牛奶8斤</p>
						<span class="viewCount">浏览量：632</span> <span class="city">广西</span>
					</div>
			</a></li>
			<li><a href="details.jsp?proName=guo.png" class="item_url"> <img
					class="pic" src="img/guo.png" />
					<div class="context">
						<span class="price">￥ 909</span> <span class="count">634人付款</span>
						<p class="name">贝瑟斯陶瓷刀水果削皮刀削皮器3件套新老包装随机</p>
						<span class="viewCount">浏览量：632</span> <span class="city">山东</span>
					</div>
			</a></li>
			<li><a href="details.jsp?proName=dao.jpg" class="item_url"> <img
					class="pic" src="img/dao.jpg" />
					<div class="context">
						<span class="price">￥ 20</span> <span class="count">634人付款</span>
						<p class="name">水忆寒削苹果神器手摇多功能削苹果机苹果削皮器水果削皮器削皮刀</p>
						<span class="viewCount">浏览量：632</span> <span class="city">义乌</span>
					</div>
			</a></li>
			<li><a href="details.jsp?proName=pinguo1.png" class="item_url">
					<img class="pic" src="img/pinguo1.png" />
					<div class="context">
						<span class="price">￥ 18</span> <span class="count">634人付款</span>
						<p class="name">苹果6/7/8/x贴纸数据线保护套绳ipad充电器保护线贴纸耳机绕线器</p>
						<span class="viewCount">浏览量：632</span> <span class="city">义乌</span>
					</div>
			</a></li>
		</ul> -->
		<ul>
		<%
			ArrayList<Product> arraylist = (ArrayList<Product>)request.getAttribute("all");
			for(Product product : arraylist) {
				%><li><a href="ProductDetailServlet?pid=<%=product.getId() %>" class="item_url">	
				<img class="pic" src="img/<%=product.getProductLogo() %>" />
				<div class="context"><span class="price">￥<%=product.getProductPrice() %></span>
				<span class="count"><%=product.getCount() %>付款</span>
				<p class="name"><%=product.getProductName() %></p>
				<span class="viewCount">浏览量：<%=product.getViewCount() %></span>
				<span class="city"><%=product.getProductCity() %></span></div></a></li>
			<% }%>
		</ul>
	</div>
	<div style="margin: 0 auto; wdth: 80%; text-align: center;">
		<!-- 分页部分 -->
		<ul class="pagination" id="pages">
			
		</ul>
	</div>
	<div style="margin:0 auto;width:1000px;">
		<h2>最近浏览</h2>
		<%
			Cookie[] cs = request.getCookies();
			for(Cookie c : cs) {
				if(c.getName().startsWith("product_")) {
					String value = c.getValue();
		%>
			<img width="150px" height="150px" src="img/<%=value%>" />
		<% 	
				}
			}
		%>
	</div>
	<script type="text/javascript">
		var xmlHttp;
		function createXMLHttpRequest() {
			if (window.ActiveXObject) {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} else if (window.XMLHttpRequest) {
				xmlHttp = new XMLHttpRequest();
			}
		}
		
		function getMore() {
			createXMLHttpRequest();
			var search = document.getElementById("search");
			var url = "SearchClassNameServlet?keyName=" + search.value;
			xmlHttp.open("GET",url,true);
			xmlHttp.send(null);
			xmlHttp.onreadystatechange=function(){
				if (xmlHttp.readyState==4 && xmlHttp.status==200) {
	        		var datas = xmlHttp.responseText;
	        		var jsObject = JSON.parse(datas);
	        		setContent(jsObject);
	    		}
			};
		}
		
		function setContent(contents) {
			clearContent();
			for(var i = 0;i < contents.length;i ++) {
				var node = contents[i];
				var tr = document.createElement("tr");
				var td = document.createElement("td");
				var text = document.createTextNode(node);
				td.onmouseover = function() {
					search.value = this.innerHTML;
				};
				td.appendChild(text);
				tr.appendChild(td);
				document.getElementById("content_table_body").appendChild(tr);
			}
		}
		
		function clearContent() {
			var contentTableBody = document.getElementById("content_table_body");
			var size = contentTableBody.childNodes.length;
			for (var i = size - 1; i >= 0; i--) {
				contentTableBody.removeChild(contentTableBody.childNodes[i]);
			}
		}
		
		$(function(){
			//getAllProduct();
			loadPage();
		}); 
		
		function loadPage() {
			$("#pages").jqPaginator({
								totalCounts : parseInt('${total}'),//商品总数量：20
								pageSize : 8,//每页显示的商品数：8
								currentPage : parseInt('${curPage}'),//当前是第几页
								prev : '<li><a href="javascript:void(0);">&lt;</a></li>',
								next : '<li><a href="javascript:void(0);">&gt;</a></li>',
								page : '<li><a href="javascript:void(0);">{{page}}</a></li>',
								onPageChange : function(num, type) {
									//init  change
									//num:切换的页码数 3 ，首个商品的索引是 16  ,limit 16,8
									//type:change
									//totalCounts:分页总条目数
									//pageSize:每页几条数据
									if (type == "change") {
										//切换页码后展示的首件商品索引
										var offset = (num - 1) * 8;
										//发请求，展示新页面的商品信息
										window.location = "${pageContext.request.contextPath}/TransferHomeServlet?offset="
												+ offset + "&num=" + num + "&flag=n";
									}
								}
							});
		}
		
		function getAllProduct(){
			$.ajax({
				type:"GET",
				url:"LoadHomeServlet",
				dataType:"json",
				success:function(data){
					showPro(data);
				}
			});
		}
		
		function showPro(data) {
			alert(data);
			if (data != '') {
				var layer;
				layer = "<ul>";
				for (var i = 0; i < data.length; i++) {
					
					layer += '<li><a href="ProductDetailServlet?pid='
							+ data[i].id
							+ '" class="item_url"><img class="pic" src="img/'+
						data[i].logo+'" /><div class="context"><span class="price">￥'
							+ data[i].price + '</span><span class="count">'
							+ data[i].count + '付款</span><p class="name">'
							+ data[i].name + '</p><span class="viewCount">浏览量：'
							+ data[i].viewCount + '</span><span class="city">'
							+ data[i].city + '</span></div></a></li>';
				}
				layer += "</ul>";
				$('#main').empty();
				$('#main').append(layer);
				//$('#paging').hide();
			} else {
				alert("还没有这种商品呢");
				$('#main').empty();
			}
		}
	</script>
</body>
</html>