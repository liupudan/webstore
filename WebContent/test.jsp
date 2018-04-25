<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
</head>
<body>
	<p id="result">
		
	</p>
	<form action="TestServlet" method="GET">
		用户名：<input type="text" name="username" id="shuru" />
			<input type="button" value="登录" id="login" onclick="tijiao()"/>
	</form>
	<script type="text/javascript">
		function tijiao() {
			var value = document.getElementById("shuru").value
			$.ajax({
				type:"GET",
				url:"TestServlet?name=" + value,
				dataType:"text",
				success:function(text){
					show(text);
				}
			});
		}
		function show(text) {
			var p = document.getElementById("result");
			p.innerHTML=text;
		}
	</script>
</body>
</html>