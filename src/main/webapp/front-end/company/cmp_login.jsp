<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>廠商登入頁面</title>
	<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
	<%@ include file="/common/head.jsp"%>
</head>
<body>
	<span id="error_msg">${requestScope.loginCompany.message}</span>
	<form action="company/login" method="post">
		<label for="cmpAccount">帳號:</label>
		<input type="text" name="cmpAccount" value="${requestScope.loginCompany.cmpAccount}${requestScope.cmpAccount}">${requestScope.errorInfo.cmpAccount}<br>

		<label for="cmpPassword">密碼:</label>
		<input type="password" name="cmpPassword">${requestScope.errorInfo.cmpPassword}<br>

		<button id="btn" type="submit">登入</button>
	</form>
</body>
</html>