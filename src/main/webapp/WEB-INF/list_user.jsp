<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>
	<h1>用户列表</h1>
	<table border="1">
		<thead>
			<tr>
				<th>编号</th>
				<th>姓名</th>
				<th>电话</th>
				<th>地址</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${users }">
				<tr>
					<td>${user.id }</td>
					<td>${user.username }</td>
					<td>${user.mobile }</td>
					<td>${user.address }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>