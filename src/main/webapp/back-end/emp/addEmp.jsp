<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.taiwan.dao.employee.impl.*"%>

<%@ include file="back-end-index.jsp"%>
<%
// EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>新增一名員工</title>

<style>
#page-wrapper {
	background-color: rgb(221, 221, 241) !important;
	height: 800px;
}

label {
	font-size: 16px;
}

img {
	/* border: 1px solid black; */
	height: 200px;
	width: 200px;
}

#img_div {
	position: absolute;
	top: 35%;
	right: 10%;
}

input[type="text"] {
	width: 220px;
}

input[type="number"] {
	width: 190px;
}

#back_index {
	position: fixed;
	right: 5%;
	bottom: 10%;
}

#back_index a {
	font-size: 20px;
	color: blue;
}

#back_index a:hover {
	color: red;
	text-decoration: none;
}

#coupon_form {
	position: relative;
}

input[type="submit"] {
	margin-bottom: 10px;
}

input[type="submit"] {
	box-shadow: inset 0px 1px 0px 0px #ffffff;
	background: linear-gradient(to bottom, #ffffff 5%, #f6f6f6 100%);
	background-color: #ffffff;
	border-radius: 6px;
	border: 1px solid #dcdcdc;
	display: inline-block;
	cursor: pointer;
	color: #666666;
	font-family: Arial;
	font-size: 15px;
	font-weight: bold;
	padding: 6px 24px;
	text-decoration: none;
	text-shadow: 0px 1px 0px #ffffff;
}

input[type="submit"]:hover {
	background: linear-gradient(to bottom, #f6f6f6 5%, #ffffff 100%);
}
</style>

</head>
<body>
	<div id="page-wrapper">
		<div class="container-fluid">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">新增員工</h1>
				</div>
			</div>


			<div id="coupon_form">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/EmployeeServlet">
					<label>員工姓名:</label> <input type="text" name="empName" autofocus
						placeholder="請輸員工姓名" value="${param.empName}"><br>
					${errorMsgs.empName} 
					<label>員工密碼:</label> <input type="password"
						name="empPassword" autofocus placeholder="請輸入密碼"
						value="${param.empPassword}"><br>
					${errorMsgs.empPassword}
					<jsp:useBean id="eFSvc" scope="page" class="com.taiwan.service.employeeFuction.EmployeeFunctionService" />
						<label>員工權限</label>
					<select size="1" name="funcId">
			<c:forEach var="employeeFunctionVO" items="${eFSvc.all}">
				<option value="${employeeFunctionVO.funcId}" ${(param.funcId==employeeFunctionVO.funcId)? 'selected':'' } >${employeeFunctionVO.funcName}
			</c:forEach></select><br>
			 <input type="hidden" name="action"
						value="insert"> <input type="submit" value="送出新增">
				</FORM>
				<div id="back_index">
					<a href='back-end/emp/emp_index.jsp'>回到優惠券管理首頁</a>
				</div>
			</div>
		</div>
	</div>
</body>

</html>