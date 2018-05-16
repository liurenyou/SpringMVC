<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>从控制器向JSP发送数据</title>
</head>
<body>
	<h1>显示控制器发送的数据</h1>
	<h2>${message }${count }</h2>
	<ul>
		<c:forEach items="${names }" var="n">
			<li>${n }</li>
		</c:forEach>
	</ul>
</body>
</html>