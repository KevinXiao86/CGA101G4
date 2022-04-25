<%@page import="com.taiwan.service.customer.CustomerService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="CustomerLogin">
		<label>帳號: </label><input type="text" placeholder="輸入帳號"
			name="account" value="${customer.account}"> <br> <label>密碼:
		</label><input type="text" placeholder="輸入密碼" name="password"
			value="${customer.password}"> <br> <input type="submit"
			value="登入"><br> ${customer.message}<input type="hidden"
			name="action" value="justLogin">
	</form>
	<form method="post" action="CustomerLogin">
		<input type="submit" value="忘記密碼"> <input type="hidden"
			name="action" value="forgetPassword">
	</form>
</body>
</html>