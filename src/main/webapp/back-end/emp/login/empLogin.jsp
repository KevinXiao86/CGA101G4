<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.taiwan.dao.employee.impl.*"%>
<%@ page import="com.taiwan.service.employee.*"%>
<%@ page import="com.taiwan.beans.*"%>
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<html>
<head>
<meta charset="UTF-8">

<title>台玩後台系統登入｜Taiwan</title>
</head>

<body>
			<h2>登入您的 台玩後台管理員帳號</h2>
			
	<form method="post" action="<%=request.getContextPath()%>/EmployeeServlet" class=" login-form">
		<label>帳號: </label>
		<input type="text" placeholder="輸入帳號"
			name="empId" value="${employee.empId}"> <br> 
		<label>密碼:</label>
		<input type="text" placeholder="輸入密碼" name="empPassword"
			value="${employee.empPassword}"> <br> 
			<input type="submit"
			value="登入"><br> ${employee.message}<input type="hidden"
			name="action" value="empLogin">
	</form>
	
	<form method="post" action="cust/CustomerLogin">
		<input type="submit" value="忘記密碼"> <input type="hidden"
			name="action" value="forgetPassword">
	</form>


</body>
</html>