<%@page import="com.taiwan.service.customer.CustomerService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員登入頁面</title>

<%@ include file="/common/head.jsp"%>
<style>
span.errorMsg {
	color: red;
	font-size: 16px;
}

span#errorMsg {
	color: orange;
	font-size: 16px;
}
</style>

<link rel="stylesheet" href="front-end/company/css/login.css">

</head>
<body>
 <div class="form">
		<div class="form-toggle"></div>

		<div class="form-panel one">

			<div class="form-header">
				<h1>會員登入</h1>
			</div>

			<div class="form-content">
				<form method="post" action="cust/CustomerLogin">
					
					<div class="form-group">
						<label for="account">帳號</label> <input type="text" placeholder="輸入帳號"
							id="account" name="account" required="required" value="${customer.account}"/>
					</div>
					<div class="form-group">
						<label for="password">密碼</label> <input type="password"
							id="password" placeholder="輸入密碼" name="password" required="required" value="${customer.password}"/>
					</div>
					<span class="errorMsg" style="">${customer.message}</span>
					<div class="form-group" style="margin-top:10px;">
						<label class="form-remember"> <input type="checkbox" />Remember
							Me
						</label><a class="form-recovery" href="#">Forgot Password?</a>
					</div>
					<div class="form-group">
						<button type="submit" id="login">登入</button>
					</div>
					<input type="hidden" name="action" value="justLogin">
				</form>
			</div>

		</div>

	</div>

	<script src='front-end/company/js/jquery.min.js'></script>
	<script src="front-end/company/js/script.js"></script>
















	<%-- <form method="post" action="cust/CustomerLogin">
		<label>帳號: </label><input type="text" placeholder="輸入帳號" name="account" value="${customer.account}"> 
		<br> 
		<label>密碼:	</label><input type="text" placeholder="輸入密碼" name="password" value="${customer.password}">
		<br>
		<input type="submit" value="登入">
		<br>
		${customer.message}
		<input type="hidden" name="action" value="justLogin">
	</form>
	<form method="post" action="cust/CustomerLogin">
		<input type="submit" value="忘記密碼"> 
		<input type="hidden" name="action" value="forgetPassword">
	</form> --%>
</body>
</html>