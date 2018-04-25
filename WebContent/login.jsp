<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page errorPage="error.jsp" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<fieldset>
		<legend>
			<c:out value="用户登录界面">
			</c:out>
		</legend>
		<font color="red">
			<%
				String msg = (String)request.getSession().getAttribute("msg");
				if(msg != null) {
					out.write(msg);
				}
			%>
		</font>
		<form action="<%=request.getContextPath()%>/Login" method="POST">
		<%@ include file="infos.jsp"%>
		<%	
			String username = "";
			String pwd = "";
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for (Cookie coo : cookies) {
					String cooName = coo.getName();
					if (cooName.equals("ck_username")) {
						username = coo.getValue();
					}
					if (cooName.equals("ck_pwd")) {
						pwd = coo.getValue();
					}
				}
			}
		%>
			用户名：<input type="text" name="username" value="<%=username%>" required="required" style="margin-bottom:5px"/><br/>
			密码：&nbsp;<input type="password" name="pwd" value="<%=pwd%>"/><br/>
			验证码：<input type="text" style="line-height:25px;vertical-align:middle;" name="check_number" required="required" />
			<img style="vertical-align:middle;" src="ValidateServlet" /><br/>
				<%
				Integer count = (Integer)request.getSession().getAttribute("count");
				if(count == null) {
				%>		
				<input type="submit" value="登录" />&nbsp;&nbsp;&nbsp;
				<%
				}
				else if(count <= 2) {
				%>
				<input type="submit" value="登录" />&nbsp;&nbsp;&nbsp;	
				<% 
				}else {
				%>	
				<input type="submit" value="登录" disabled="disabled"/>&nbsp;&nbsp;&nbsp;	
				<% 
				}
				%>
			<input type="button" value="注册" onclick="register()"/><br/>
			三天免登陆<input type="checkbox" name="freelanding" />
			<a href="retrive.jsp">忘记密码？</a>
		</form>
	</fieldset>
	<script type="text/javascript">
		function register() {
			var url = "register.jsp";
			window.location.href = url;
		}
	</script>
</body>
</html>