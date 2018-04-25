<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.css" />
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<script type="text/javascript"
	src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<title>购物车</title>
<style type="text/css">
#form1{
margin-top:70px;
}
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

.select {
	margin-top: 80px;
	margin-left: 60px;
}

.select input {
	width: 20px;
	height: 20px;
}

.count {
	width: 120px;
}

.dec {
	border-right: 1px;
	border-bottom-right-radius: 0;
	border-top-right-radius: 0;
	margin-rigth: -1px;
}

.del {
	display: block;
	margin-top: 80px;
	width: 48px;
	height: 48px;
	background-image: url(../img/del.png);
	cursor: pointer;
}

.clearfix:nth-child(odd) {
	background: #F0F8FF;
}

.clearfix:nth-child(even) {
	background: #FFF8DC;
}
.logout {
	position: absolute;
	right: 5px;
	top: 5px;
	z-index: 10;
}

.order {
	position: absolute;
	right: 64px;
	top: 5px;
	z-index: 10;
}

.home {
	position: absolute;
	right: 150px;
	top: 5px;
	z-index: 10;
}
.personal {
	position: absolute;
	right: 210px;
	top: 5px;
	z-index: 10;
}

p{
margin:0px;
}
</style>
<script>
	$(function() {
		loadInit();
		$("#form1").on("click", "#account", function() {
			//结算
			var flag = false;
			var checkboxs = $(":checkbox");
			for (var i = 0; i < checkboxs.length; i++) {
				if (checkboxs[i].checked == true) {
					flag = true;
				}
			}
			if (flag == true) {
				$("#form1").submit();
			} else {
				alert("请勾选要购买的商品！");
			}
		});
		$("#form1").on("click", ":checkbox", function() {
			updateTotal();
		});
		$("#form1").on(
				"click",
				".del",
				function() {
					var div = $(this).parent().parent();//div class="row clearfix"
					var id = div.children().children().children()
							.val(); //input type="checkbox" name="proId"
					//id = 3-2
					$.ajax({
						type : 'GET',
						url : 'DeleteCart?proId='+id,					
						dataType : "text",
						success : function(data) {
							if (data == "ok") {
								div.remove();//删除节点
								updateTotal();
							}
						}
					});
				});
		$("#form1").on(
				"click",
				".count_btn",
				function() {
					var count_input;
					var count;
					var proId;
					//总价格元素
					var price = $(this).parent().parent().parent().next()
							.next().children();
					if ($(this).hasClass("add")) {
						count_input = $(this).parent().prev();//count_input  “2”
						//100
						if(parseInt(count_input.val()) <100){
						count = parseInt(count_input.val()) + 1;		
						proId = $(this).parent().prev().prev().prev().val();
						}else{
							count = 100;
						}
					} else if ($(this).hasClass("dec")) {
						count_input = $(this).parent().next();
						if(parseInt(count_input.val()) == 1){
							count = 1;
						}else{
						count = parseInt(count_input.val()) - 1;
						proId = $(this).parent().prev().val();
						}						
					}
					count_input.val(count);//给数量输入框设定新值
					//更新购物车表
					$.ajax({
						type : 'POST',
						url : 'UpdateCart',
						data : 'proId=' + proId + '&count=' + count,
						dataType : "text",
						success : function(data) {
							if (data != '') {
								price.text(data);//190
								updateTotal();
							}
						}
					});
				});
	});
	
	function updateTotal() {
		var total = 0.0;
		var checkboxs = $(":checkbox");
		for (var i = 0; i < checkboxs.length; i++) {
			if (checkboxs[i].checked == true) {
				var cartId = checkboxs[i].value;
				var priceId = "price" + cartId;
				var price = $("#" + priceId).text() - 0;
				total += price;
			}
		}
		$("#total").text(total);
		$("#tot").val(total);
	}
		
	function loadInit() {
		var layer = "";
		$.ajax({
					type : "GET",
					url : "ShowCartServlet",
					dataType : "json",
					success : function(data) {
						if (data != null || data != '') {
							for (var i = 0; i < data.length; i++) {
								layer += '<div class="row clearfix"><div class="col-md-1 column"><div class="checkbox select"><input type="checkbox" name="proId" value="'+data[i].proId+'"></div></div><div class="col-md-2 column"><img class="proLogo" alt="140x140" src="img/'+
							data[i].proLogo + '" /></div><div class="col-md-8 column"><dl class="dl-horizontal"><dt style="width: 120px">商品名称：</dt><dd class="proname"><p>'
										+ data[i].proName
										+ '</p></dd><dt style="width: 120px">数量：</dt><dd><div class="input-group count"><input type="hidden" class="proId" value="'+
							data[i].proId + '"><span class="input-group-btn"><button class="btn btn-default count_btn dec" type="button">-</button></span><input type="text" class="form-control" name="proNum" value="'+
							data[i].buyCount + '"><span class="input-group-btn"><button class="btn btn-default count_btn add" type="button">+</button></span></div></dd><dt style="width: 120px">价格：</dt></dt><dd class="price">￥<span id="price'+
							data[i].proId + '">'
										+ data[i].subTotal
										+ '</span></dd></dl></div><div class="col-md-1 column"><a class="del"><img src="img/del.png"></a></div></div>';
							}
						} else {
							layer += '<div class="row clearfix"><div class="col-md-10 col-md-offset-2 column">'
									+ '<h3 class="text-left" style="color: red;">亲！您的购物车还没有宝贝，快去添加吧！'
									+ '</h3></div></div>';
						}
						layer += '<div class="row clearfix"><div class="col-md-2 col-md-offset-8 column">'
								+ '<h3 class="text-left" style="color: red;">总计：￥<span id="total">0.0</span>'
								+ '</h3></div><div class="col-md-2 column">'
								+ '<h4 class="text-center"><button type="button" class="btn btn-default" id="account">结&nbsp;算</button></h4>'
								+ '</div></div><input type="hidden" id="tot" name="total" value="0.0">';
						$("#form1").empty();
						$("#form1").append(layer);
					}
				});
	}
</script>
</head>
<body>
	<div class="container">
		<a type="button" class="btn btn-info logout" href="#">注销</a>
		<a type="button" class="btn btn-info order" href="#">我的订单</a>
		<a type="button" class="btn btn-info home" href="home.jsp">首页</a>
		<a type="button" class="btn btn-info personal"
		href="#">个人中心</a>
		<form id="form1" role="form" action="AddOrderServlet" method="post">
			
		</form>
	</div>
</body>
</html>