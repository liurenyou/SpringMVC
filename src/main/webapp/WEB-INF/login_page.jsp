<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录表单</title>
</head>
<body>
	<h1>UserLogin</h1>
	<h2>${error }</h2>
	<div class="form">
		<c:url var="url" value="/login/check.form"/>
		<form action="${url }" method="post">
			<div>
				<label>用户名：</label>
				<input type="text" name="username" />
				<span class="error"></span>
			</div>
			<div>
				<label>密码：</label>
				<input type="password" name="password" />
				<span class="error"></span>
			</div>
			<input type="submit" value="登录">
		</form>
	</div>
	<h1>UserLogin2</h1>
	<h2>${error2 }</h2>
	<div class="form">
		<c:url var="url" value="/loginController/login.form"/>
		<form action="${url }" method="post">
			<div>
				<label>用户名：</label>
				<input type="text" name="username" />
				<span class="error"></span>
			</div>
			<div>
				<label>密码：</label>
				<input type="password" name="password" />
				<span class="error"></span>
			</div>
			<input type="submit" value="登录">
		</form>
	</div>
</body>
</html>