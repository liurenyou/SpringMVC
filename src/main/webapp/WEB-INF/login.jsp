<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录表单</title>
</head>
<body>
	<h1>登录-浏览器向控制器提交数据</h1>
	<div class="form">
		<!-- url标签的作用是生成URL路径 -->
		<!-- 注：注释内的jstl标签也是有作用的，切勿添加，血的教训 -->
		<h2>利用request对象接收浏览器数据</h2>
		<c:url var="url" value="/demo/login1.form" />
		<form action="${url }" method="post">
			<div>
				<label>用户名：</label> <input type="text" name="username" /> <span
					class="error"></span>
			</div>
			<div>
				<label>密码：</label> <input type="password" name="pwd" /> <span
					class="error"></span>
			</div>
			<input type="submit" value="登录" />
		</form>
	</div>
	<div>
		<h2>利用@RequestParam注解接收浏览器数据</h2>
		<c:url var="url" value="/demo/login2.form" />
		<form action="${url }" method="post">
			<div>
				<label>用户名：</label> <input type="text" name="username" /> <span
					class="error"></span>
			</div>
			<div>
				<label>密码：</label>
				<input type="password" name="for" />
				<span class="error"></span>
			</div>
			<input type="submit" value="登录" />
		</form>
	</div>
	<div>
		<h2>利用值对象打包接收浏览器数据</h2>
		<c:url var="url" value="/demo/login3.form" />
		<form action="${url }" method="post">
			<div>
				<label>用户名：</label> <input type="text" name="username" /> <span
					class="error"></span>
			</div>
			<div>
				<label>密码：</label>
				<input type="password" name="password" />
				<span class="error"></span>
			</div>
			<input type="submit" value="登录" />
		</form>
	</div>
	<div>
		<h2>利用ModelMap向浏览器传递数据</h2>
		<c:url var="url" value="/demo/login4.form" />
		<form action="${url }" method="post">
			<div>
				<label>用户名：</label> <input type="text" name="username" value="${username }"/> <span
					class="error">${nameError }</span>
			</div>
			<div>
				<label>密码：</label>
				<input type="password" name="password" />
				<span class="error">${passwordError }</span>
			</div>
			<input type="submit" value="登录" />
		</form>
	</div>
</body>
</html>