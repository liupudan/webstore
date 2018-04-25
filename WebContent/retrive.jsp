<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<font color="red">
		<%
			String retriveRemind = (String)application.getAttribute("retriveRemind");
			if(retriveRemind != null) {
				out.write(retriveRemind);
			}
		%>
	</font>
	
	<form action="RetriveServlet" method="GET">
		请输入注册时的邮箱地址：<input type="text" name="retrive">
		<input type="submit" value="找回">
	</form>
</body>
</html>