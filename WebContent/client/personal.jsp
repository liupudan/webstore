<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
个人中心<br/>
<img src="${requestScope.graphURL}" />
<form action="UploadImg" enctype="multipart/form-data" method="POST">
	用户名：<input type="text" name="username"><br/>
	密码：&nbsp;<input type="password" name="pwd"><br/>
	头像：&nbsp;<input type="file" name="picture">
	<input type="submit" value="提交">
</form>
<font color="red">
	${requestScope.msg}
</font>
<a href="ShowDownloadImgs">图片下载</a>
</body>
</html>