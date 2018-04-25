<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>下载文件显示页面</title>
</head>

<body>
	<%-- <!-- 遍历Map集合 -->
	<c:forEach var="me" items="${fileNameMap}">
		<c:url value="/downloadImg.do" var="downurl">
			<c:param name="filename" value="${me.key}"></c:param>
		</c:url>
         ${me.value}<a href="${downurl}">下载</a>
        <br>
         <img style="width:300px;height:200px;" src="${downurl}" alt="404"/>
		<br />
	</c:forEach> --%>
	<%
	Map<String, String> mp = (Map<String, String>)request.getAttribute("fileNameMap");
	String value = "";
	for(String key : mp.keySet()){
		value = mp.get(key);
	%>
	<%=value %><a href="DownloadImgServlet?filename=<%=key%>">下载</a>
	<br>
	<img alt="404" src="${pageContext.request.contextPath}/upload/<%=key%>"/>
	<%}
	%>
</body>
</html>