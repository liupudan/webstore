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
			String register = (String)request.getAttribute("register");
			if(register != null) {
				out.write(register);
			}
		%>	
	</font>
		
	<form action="RegisterServlet" method="GET">
		用户名：<input type="text" name="username"><br/>
		密码：&nbsp;<input type="text" name="password" >
		<input type="submit" value="注册">
	</form>
</body>
</html>