<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.taiwan.dao.employee.impl.*"%>
<%@ page import="com.taiwan.service.employee.*"%>
<%@ page import="com.taiwan.beans.*"%>
<script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>
  <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>


<html>
<head>
<meta charset="UTF-8">
<title>台玩後台系統登入｜Taiwan</title>
<%@ include file="/common/head.jsp"%>

<link rel="stylesheet" href="back-end/emp/login/style.css">
<script src='back-end/emp/login/jquery.min.js'></script>

<script src="back-end/emp/login/script.js"></script>
</head>

<body>
	<h2>登入您的 台玩後台</h2>
	<div class="form">
		<div class="form-toggle"></div>
		<div class="form-panel one">
			<div class="form-header">
				<h1>管理員登入</h1>
			</div>
			<div class="form-content">
				<form method="post"
					action="<%=request.getContextPath()%>/EmployeeServlet">

					<div class="form-group">
						<label for="empID">員工編號</label> <input type="text"
							placeholder="輸入員工編號" id="empID" name="empId"
							value="${employee.empId}" required="required" />
					</div>
					<div class="form-group">
						<label for="empPassword">密碼</label> <input type="password"
							placeholder="輸入密碼" id="empPassword" name="empPassword"
							required="required" />
					</div>


					<div class="form-group">
					
						<button type="submit">Log In</button>
						 <input type="hidden" name="empId"  value="${employeeVO.empId}">
						<input type="hidden" name="action" value="empLogin">
					</div>
				</form>
			</div>
		</div>
	</div>

			
			


	<!-- 	<form method="post" -->
	<%-- 		action="<%=request.getContextPath()%>/EmployeeServlet" --%>
	<!-- 		class=" login-form"> -->
	<!-- 		<label>帳號: </label> <label>密碼:</label> <input type="text" -->
	<%-- 			placeholder="輸入密碼" name="empPassword" value="${employee.empPassword}"> --%>
	<!-- 		<br> <input type="submit" value="登入"><br> -->
	<%-- 		${employee.message}<input type="hidden" name="action" value="empLogin"> --%>
	<!-- 	</form> -->

	<!-- 	<form method="post" action="cust/CustomerLogin"> -->
	<!-- 		<input type="submit" value="忘記密碼"> <input type="hidden" -->
	<!-- 			name="action" value="forgetPassword"> -->
	<!-- 	</form> -->


</body>
</html>