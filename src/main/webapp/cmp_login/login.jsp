<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>廠商登陸頁面</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
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

<link rel="stylesheet" href="cmp_login/css/login.css">
<script src="cmp_login/js/login.js"></script>
</head>
<body>
	<div class="form">
		<div class="form-toggle"></div>

		<div class="form-panel one">

			<div class="form-header">
				<h1>Account Login</h1>
			</div>

			<div class="form-content">
				<form>
					<span class="errorMsg"></span>
					<div class="form-group">
						<label for="cmpAccount">Account</label> <input type="text"
							id="cmpAccount" name="cmpAccount" required="required" />
					</div>
					<div class="form-group">
						<label for="cmpPassword">Password</label> <input type="password"
							id="cmpPassword" name="cmpPassword" required="required" />
					</div>
					<div class="form-group">
						<label class="form-remember"> <input type="checkbox" />Remember
							Me
						</label><a class="form-recovery" href="#">Forgot Password?</a>
					</div>
					<div class="form-group">
						<button type="submit" id="login">Log In</button>
					</div>
				</form>
			</div>

		</div>

	</div>

	<script src='cmp_login/js/jquery.min.js'></script>
	<script src="cmp_login/js/script.js"></script>
</body>
</html>