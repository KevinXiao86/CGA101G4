<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.taiwan.dao.employee.impl.*"%>
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<html>
<head>
<meta charset="UTF-8">

<title>台玩後台系統登入｜Taiwan</title>
</head>

<body>
	<div class="login-wrap">
		<div class="login-container">
			<a href="#"><img
				src="<%=request.getContextPath()%>/back_end/admin/images/MHlogo_03.svg"
				alt=""></a>
			<h2>登入您的 台玩後台管理員帳號</h2>
			<form action="<%=request.getContextPath()%>/admin/adminServlet"
				class="login-form" method="post">

				<label for="empID">員工編號</label><br> <input type="text"
					id="empID" name="empId" placeholder="請輸入您的員工編號">
				<h3 id="u-prompt" class="u-prompt">${errorMsgs.username}</h3>
				<label for="empPassword">密碼</label><br> <input type="text"
					id="empPassword" name="empPassword" maxlength="20"
					placeholder="請輸入您的密碼">
				<h3 id="p-prompt" class="p-prompt">${errorMsgs.password}</h3>
				<input type="submit" id="login" value="登入"> <input
					type="hidden" name="action" value="login" />
			</form>
		</div>
	</div>
	<script>
		if ($('#i-prompt').html() != "") {
			$('#username').css('border', '2px solid red');
		}
		if ($('#p-prompt').html() != "") {
			$('#password').css('border', '2px solid red');
		}

		//送出表單驗證
		$('#login').on('click', function() {
			if (validateId() && validatePassword()) {
				return true;
			} else {
				return false;
			}
		});

		//監聽員工編號格式是否正確
		$('#username').on('input', function() {
			$('#i-prompt').text("");
			if (validateId()) {
				$('#username').css('border', '2px solid #27da80')
			} else {
				$('#i-prompt').text("請輸入員工編號");
				$('#i-prompt').css('color', 'red');
				$('#i-prompt').css('font-size', '10px');
				$('#username').css('border', '2px solid red')
			}
		});

		//監聽密碼格式是否正確
		$('#password').on('input', function() {
			$('#p-prompt').text("");
			if (validatePassword()) {
				$('#password').css('border', '2px solid #27da80')
			} else {
				$('#p-prompt').text("密碼長度限制6-20");
				$('#p-prompt').css('color', 'red');
				$('#p-prompt').css('font-size', '10px');
				$('#password').css('border', '2px solid red')
			}
		});

		//帳號正則表達式驗證
		function validateId() {
			let empId = $('#empId').val();
			const re = /^[0-9]{5,5}$/;
			return re.test(empId);
		}

		//密碼正則表達式驗證
		function validatePassword() {
			let password = $('#password').val();
			const re = /^[0-9A-Za-z]{6,20}$/;
			return re.test(password);
		}
	</script>
</body>
</html>